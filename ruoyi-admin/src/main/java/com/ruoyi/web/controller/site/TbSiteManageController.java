package com.ruoyi.web.controller.site;

import java.util.List;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.site.domain.TbSiteManage;
import com.ruoyi.site.service.ITbSiteManageService;
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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工作任务Controller
 * 
 * @author ruoyi
 * @date 2020-08-16
 */
@RestController
@RequestMapping("/system/manage")
public class TbSiteManageController extends BaseController
{
    @Autowired
    private ITbSiteManageService tbSiteManageService;

    /**
     * 查询列表
     */
    @PreAuthorize("@ss.hasPermi('system:manage:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbSiteManage tbSiteManage)
    {
        startPage();
        List<TbSiteManage> list = tbSiteManageService.selectTbSiteManageList(tbSiteManage);
        return getDataTable(list);
    }

    /**
     * 导出列表
     */
    @PreAuthorize("@ss.hasPermi('system:manage:export')")
    @Log(title = "导出excel", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TbSiteManage tbSiteManage)
    {
        List<TbSiteManage> list = tbSiteManageService.selectTbSiteManageList(tbSiteManage);
        ExcelUtil<TbSiteManage> util = new ExcelUtil<TbSiteManage>(TbSiteManage.class);
        return util.exportExcel(list, "manage");
    }

    /**
     * 获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:manage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(tbSiteManageService.selectTbSiteManageById(id));
    }

    /**
     * 新增
     */
    @PreAuthorize("@ss.hasPermi('system:manage:add')")
    @Log(title = "新增", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbSiteManage tbSiteManage)
    {
        tbSiteManage.setId(IdUtil.simpleUUID());
        //创建时间
        tbSiteManage.setCreateTime(DateUtil.now());
        //创建人
        tbSiteManage.setCreateBy(SecurityUtils.getUsername());
        return toAjax(tbSiteManageService.insertTbSiteManage(tbSiteManage));
    }

    /**
     * 修改
     */
    @PreAuthorize("@ss.hasPermi('system:manage:edit')")
    @Log(title = "修改", businessType = BusinessType.UPDATE)
    @RequestMapping("edit")
    public AjaxResult edit(@RequestBody TbSiteManage tbSiteManage)
    {
        return toAjax(tbSiteManageService.updateTbSiteManage(tbSiteManage));
    }

    /**
     * 删除
     */
    @PreAuthorize("@ss.hasPermi('system:manage:remove')")
    @Log(title = "删除", businessType = BusinessType.DELETE)
    @RequestMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tbSiteManageService.deleteTbSiteManageByIds(ids));
    }
}
