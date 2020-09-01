package com.ruoyi.site.mapper;

import com.ruoyi.site.domain.TbSiteManage;

import java.util.List;

/**
 * 工作任务Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-16
 */
public interface TbSiteManageMapper 
{
    /**
     * 查询工作任务
     * 
     * @param id 工作任务ID
     * @return 工作任务
     */
    public TbSiteManage selectTbSiteManageById(String id);

    /**
     * 查询工作任务列表
     * 
     * @param tbSiteManage 工作任务
     * @return 工作任务集合
     */
    public List<TbSiteManage> selectTbSiteManageList(TbSiteManage tbSiteManage);

    /**
     * 新增工作任务
     * 
     * @param tbSiteManage 工作任务
     * @return 结果
     */
    public int insertTbSiteManage(TbSiteManage tbSiteManage);

    /**
     * 修改工作任务
     * 
     * @param tbSiteManage 工作任务
     * @return 结果
     */
    public int updateTbSiteManage(TbSiteManage tbSiteManage);

    /**
     * 删除工作任务
     * 
     * @param id 工作任务ID
     * @return 结果
     */
    public int deleteTbSiteManageById(String id);

    /**
     * 批量删除工作任务
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbSiteManageByIds(String[] ids);
}
