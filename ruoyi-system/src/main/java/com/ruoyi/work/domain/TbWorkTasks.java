package com.ruoyi.work.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工作任务对象 tb_work_tasks
 *
 * @author ruoyi
 * @date 2020-08-14
 */
@Data
public class TbWorkTasks
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 管理类型 */
    @Excel(name = "管理类型")
    private String manageType;

    /** 工作板块 */
    @Excel(name = "工作板块")
    private String workSection;

    /** 工作线条 */
    @Excel(name = "工作线条")
    private String workLine;

    /** 工作任务 */
    @Excel(name = "工作任务")
    private String workTasks;

    /** 工作任务标题 */
    @Excel(name = "工作任务标题")
    private String workTitle;

    /** 完成情况 */
    @Excel(name = "完成情况")
    private String completion;

    /** 重要程度 */
    @Excel(name = "重要程度")
    private String importance;

    /** 开始时间 */
    @Excel(name = "开始时间")
    private String timeBegin;

    /** 结束时间 */
    @Excel(name = "结束时间")
    private String timeEnd;

    private String createBy;

    @Excel(name = "创建时间")
    private String createTime;

    @Excel(name = "任务完成时间")
    private String completeTime;

    @Excel(name = "责任人")
    private String createName;
}
