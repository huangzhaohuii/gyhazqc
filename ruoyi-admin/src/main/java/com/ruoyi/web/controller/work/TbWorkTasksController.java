package com.ruoyi.web.controller.work;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.ruoyi.analysis.domain.AnalysisInfo;
import com.ruoyi.common.annotation.DictValue;
import com.ruoyi.common.utils.PercentUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.work.domain.TbWorkTasks;
import com.ruoyi.work.service.ITbWorkTasksService;
import com.ruoyi.work.service.impl.TbWorkTasksServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工作计划Controller
 *
 * @author huang
 * @date 2020-08-14
 */
@RestController
@RequestMapping("/system/tasks")
public class TbWorkTasksController extends BaseController
{
    @Autowired
    private ITbWorkTasksService tbWorkTasksService;

    /**
     * 查询列表
     */
    @DictValue("manageType,workLine,workSection,importance,completion,createBy")
    @PreAuthorize("@ss.hasPermi('system:tasks:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbWorkTasks tbWorkTasks)
    {
        startPage();
        List<TbWorkTasks> list = tbWorkTasksService.selectTbWorkTasksList(tbWorkTasks);
        return getDataTable(list);
    }

    /**
     * 导出excel
     */
    @PreAuthorize("@ss.hasPermi('system:tasks:export')")
    @Log(title = "导出excel", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TbWorkTasks tbWorkTasks)
    {
        List<TbWorkTasks> list = tbWorkTasksService.exportExcel(tbWorkTasks);
        ExcelUtil<TbWorkTasks> util = new ExcelUtil<TbWorkTasks>(TbWorkTasks.class);
        return util.exportExcel(list, "tasks");
    }

    /**
     * 获取详情
     */
    @PreAuthorize("@ss.hasPermi('system:tasks:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        AjaxResult ajaxResult = new AjaxResult();
        return AjaxResult.success(tbWorkTasksService.selectTbWorkTasksById(id));
    }

    /**
     * 新增
     */
    @PreAuthorize("@ss.hasPermi('system:tasks:add')")
    @Log(title = "新增", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbWorkTasks tbWorkTasks)
    {
        tbWorkTasks.setId(IdUtils.simpleUUID());
        //创建时间
        tbWorkTasks.setCreateTime(DateUtil.format(DateUtil.date(),"yyyyMMddHHmmss"));
        //创建工号
        tbWorkTasks.setCreateBy(SecurityUtils.getUsername());
        //责任人名称
        tbWorkTasks.setCreateName(SecurityUtils.getLoginUser().getUser().getNickName());
        return toAjax(tbWorkTasksService.insertTbWorkTasks(tbWorkTasks));
    }

    /**
     * 修改
     */
    @PreAuthorize("@ss.hasPermi('system:tasks:edit')")
    @Log(title = "修改", businessType = BusinessType.UPDATE)
    @RequestMapping("edit")
    public AjaxResult edit(@RequestBody TbWorkTasks tbWorkTasks)
    {
        return toAjax(tbWorkTasksService.updateTbWorkTasks(tbWorkTasks));
    }

    /**
     * 删除
     */
    @PreAuthorize("@ss.hasPermi('system:tasks:remove')")
    @Log(title = "删除", businessType = BusinessType.DELETE)
    @RequestMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tbWorkTasksService.deleteTbWorkTasksByIds(ids));
    }

    /**
     * 确认完成
     */
    @Log(title = "确认完成", businessType = BusinessType.UPDATE)
    @RequestMapping("complete")
    public AjaxResult complete(@RequestBody TbWorkTasks tbWorkTasks)
    {
        String timeBegin = tbWorkTasks.getTimeBegin();
        String timeEnd = tbWorkTasks.getTimeEnd();
        String now = DateUtil.format(DateUtil.date(),"yyy-MM-dd");
        if(now.compareTo(timeBegin) < 0){
            return AjaxResult.error("任务未开始！");
        }
        if(now.compareTo(timeEnd) < 0){
            tbWorkTasks.setCompletion("000701");
        }else{
            tbWorkTasks.setCompletion("000703");
        }
        tbWorkTasks.setCompleteTime(DateUtil.now());
        return toAjax(tbWorkTasksService.updateTbWorkTasks(tbWorkTasks));
    }

    /**
     * 数据分析统计
     * @return
     */
    @RequestMapping("analysis")
    @DictValue("completion")
    public TableDataInfo analysis(@RequestBody AnalysisInfo analysisInfo){
      List<AnalysisInfo> analysisInfos = tbWorkTasksService.getAnalysisData(analysisInfo);
      List<Map<String,Object>> analysisCount = tbWorkTasksService.getAnalysisCount(analysisInfo);
        if(analysisInfos.size() != 0){
          for (AnalysisInfo analysisData: analysisInfos) {
              //时间段
              analysisData.setPlanTime(analysisInfo.getTimeBegin() + " 至 " + analysisInfo.getTimeEnd());
              //总记录数
              for (Map<String,Object> map: analysisCount) {
                  if(StrUtil.equals((String)map.get("countName"),analysisData.getCreateName())){
                      analysisData.setTotalCount((Long)map.get("count"));
                  }
              }
              String percent = PercentUtils.myPercent(analysisData.getCount(), new Long(analysisData.getTotalCount()).intValue());
              analysisData.setPercent(percent);

          }
      }

      return getDataTable(analysisInfos);
    }

    /**
     * 获取超时未完成的任务
     * @return
     */
    @RequestMapping("unDoTask")
    public AjaxResult unDoTask(){

        String userName = SecurityUtils.getUsername();
        startPage();
        List<Map<String, Object>> list = tbWorkTasksService.getUndoCount(userName);
        if(list.size() == 0){
            return AjaxResult.success();
        }
        long count = (long) list.get(0).get("count");
        return AjaxResult.success(count);
    }

    @DictValue("manageType,workLine,workSection,importance,completion,createBy")
    @GetMapping("/undoList")
    public TableDataInfo undoList(TbWorkTasks tbWorkTasks)
    {
        tbWorkTasks.setCreateBy(SecurityUtils.getUsername());
        startPage();
        List<TbWorkTasks> list = tbWorkTasksService.selectTbWorkTasksUndoList(tbWorkTasks);
        return getDataTable(list);
    }
}
