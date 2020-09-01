package com.ruoyi.work.service;


import com.ruoyi.analysis.domain.AnalysisInfo;
import com.ruoyi.work.domain.TbWorkTasks;

import java.util.List;
import java.util.Map;

/**
 * 工作任务Service接口
 * 
 * @author ruoyi
 * @date 2020-08-14
 */
public interface ITbWorkTasksService 
{
    /**
     * 查询工作任务
     * 
     * @param id 工作任务ID
     * @return 工作任务
     */
    public TbWorkTasks selectTbWorkTasksById(String id);

    /**
     * 查询工作任务列表
     * 
     * @param tbWorkTasks 工作任务
     * @return 工作任务集合
     */
    public List<TbWorkTasks> selectTbWorkTasksList(TbWorkTasks tbWorkTasks);

    /**
     * 导出excel
     * @param tbWorkTasks
     * @return
     */
    public List<TbWorkTasks> exportExcel(TbWorkTasks tbWorkTasks);

    /**
     * 查询工作任务列表
     *
     * @param tbWorkTasks 工作任务
     * @return 工作任务集合
     */
    public List<TbWorkTasks> selectTbWorkTasksUndoList(TbWorkTasks tbWorkTasks);

    /**
     * 新增工作任务
     * 
     * @param tbWorkTasks 工作任务
     * @return 结果
     */
    public int insertTbWorkTasks(TbWorkTasks tbWorkTasks);

    /**
     * 修改工作任务
     * 
     * @param tbWorkTasks 工作任务
     * @return 结果
     */
    public int updateTbWorkTasks(TbWorkTasks tbWorkTasks);

    /**
     * 批量删除工作任务
     * 
     * @param ids 需要删除的工作任务ID
     * @return 结果
     */
    public int deleteTbWorkTasksByIds(String[] ids);

    /**
     * 删除工作任务信息
     * 
     * @param id 工作任务ID
     * @return 结果
     */
    public int deleteTbWorkTasksById(String id);

    /**
     * 获取数据分析数据
     * @param analysisInfo
     * @return
     */
    public List<AnalysisInfo> getAnalysisData(AnalysisInfo analysisInfo);

    /**
     * 获取数据分析对应的总数
     * @param analysisInfo
     * @return
     */
    public List<Map<String,Object>> getAnalysisCount(AnalysisInfo analysisInfo);

    /**
     * 获取未完成数量
     * @param createBy
     * @return
     */
    public List<Map<String,Object>> getUndoCount(String createBy);
}
