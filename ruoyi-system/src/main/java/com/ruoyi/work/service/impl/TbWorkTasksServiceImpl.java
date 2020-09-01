package com.ruoyi.work.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.analysis.domain.AnalysisInfo;
import com.ruoyi.work.domain.TbWorkTasks;
import com.ruoyi.work.mapper.TbWorkTasksMapper;
import com.ruoyi.work.service.ITbWorkTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 工作任务Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-14
 */
@Service
public class TbWorkTasksServiceImpl implements ITbWorkTasksService
{
    @Autowired
    private TbWorkTasksMapper tbWorkTasksMapper;

    /**
     * 查询工作任务
     * 
     * @param id 工作任务ID
     * @return 工作任务
     */
    @Override
    public TbWorkTasks selectTbWorkTasksById(String id)
    {
        return tbWorkTasksMapper.selectTbWorkTasksById(id);
    }

    /**
     * 查询工作任务列表
     * 
     * @param tbWorkTasks 工作任务
     * @return 工作任务
     */
    @Override
    public List<TbWorkTasks> selectTbWorkTasksList(TbWorkTasks tbWorkTasks)
    {
        return tbWorkTasksMapper.selectTbWorkTasksList(tbWorkTasks);
    }

    @Override
    public List<TbWorkTasks> exportExcel(TbWorkTasks tbWorkTasks) {
        return tbWorkTasksMapper.exportExcel(tbWorkTasks);
    }

    @Override
    public List<TbWorkTasks> selectTbWorkTasksUndoList(TbWorkTasks tbWorkTasks) {
        return tbWorkTasksMapper.selectTbWorkTasksUndoList(tbWorkTasks);
    }

    /**
     * 新增工作任务
     * 
     * @param tbWorkTasks 工作任务
     * @return 结果
     */
    @Override
    public int insertTbWorkTasks(TbWorkTasks tbWorkTasks)
    {
        return tbWorkTasksMapper.insertTbWorkTasks(tbWorkTasks);
    }

    /**
     * 修改工作任务
     * 
     * @param tbWorkTasks 工作任务
     * @return 结果
     */
    @Override
    public int updateTbWorkTasks(TbWorkTasks tbWorkTasks)
    {
        return tbWorkTasksMapper.updateTbWorkTasks(tbWorkTasks);
    }

    /**
     * 批量删除工作任务
     * 
     * @param ids 需要删除的工作任务ID
     * @return 结果
     */
    @Override
    public int deleteTbWorkTasksByIds(String[] ids)
    {
        return tbWorkTasksMapper.deleteTbWorkTasksByIds(ids);
    }

    /**
     * 删除工作任务信息
     * 
     * @param id 工作任务ID
     * @return 结果
     */
    @Override
    public int deleteTbWorkTasksById(String id)
    {
        return tbWorkTasksMapper.deleteTbWorkTasksById(id);
    }

    @Override
    public List<AnalysisInfo> getAnalysisData(AnalysisInfo analysisInfo) {
        return tbWorkTasksMapper.getAnalysisData(analysisInfo);
    }

    @Override
    public List<Map<String,Object>> getAnalysisCount(AnalysisInfo analysisInfo) {
        return tbWorkTasksMapper.getAnalysisCount(analysisInfo);
    }

    @Override
    public List<Map<String, Object>> getUndoCount(String createBy) {
        return tbWorkTasksMapper.getUndoCount(createBy);
    }

}
