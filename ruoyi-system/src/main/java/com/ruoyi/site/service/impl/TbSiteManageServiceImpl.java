package com.ruoyi.site.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.site.domain.TbSiteManage;
import com.ruoyi.site.mapper.TbSiteManageMapper;
import com.ruoyi.site.service.ITbSiteManageService;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 工作任务Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-16
 */
@Service
public class TbSiteManageServiceImpl implements ITbSiteManageService
{
    @Autowired
    private TbSiteManageMapper tbSiteManageMapper;

    /**
     * 查询工作任务
     * 
     * @param id 工作任务ID
     * @return 工作任务
     */
    @Override
    public TbSiteManage selectTbSiteManageById(String id)
    {
        return tbSiteManageMapper.selectTbSiteManageById(id);
    }

    /**
     * 查询工作任务列表
     * 
     * @param tbSiteManage 工作任务
     * @return 工作任务
     */
    @Override
    public List<TbSiteManage> selectTbSiteManageList(TbSiteManage tbSiteManage)
    {
        return tbSiteManageMapper.selectTbSiteManageList(tbSiteManage);
    }

    /**
     * 新增工作任务
     * 
     * @param tbSiteManage 工作任务
     * @return 结果
     */
    @Override
    public int insertTbSiteManage(TbSiteManage tbSiteManage)
    {
        return tbSiteManageMapper.insertTbSiteManage(tbSiteManage);
    }

    /**
     * 修改工作任务
     * 
     * @param tbSiteManage 工作任务
     * @return 结果
     */
    @Override
    public int updateTbSiteManage(TbSiteManage tbSiteManage)
    {
        return tbSiteManageMapper.updateTbSiteManage(tbSiteManage);
    }

    /**
     * 批量删除工作任务
     * 
     * @param ids 需要删除的工作任务ID
     * @return 结果
     */
    @Override
    public int deleteTbSiteManageByIds(String[] ids)
    {
        return tbSiteManageMapper.deleteTbSiteManageByIds(ids);
    }

    /**
     * 删除工作任务信息
     * 
     * @param id 工作任务ID
     * @return 结果
     */
    @Override
    public int deleteTbSiteManageById(String id)
    {
        return tbSiteManageMapper.deleteTbSiteManageById(id);
    }
}
