package com.ruoyi.analysis.domain;

import lombok.Data;


/**
 * @author Administrator
 * @create 2020/8/17
 * @Description: 数据分析实体类
 * @since 1.0.0
 */
@Data
public class AnalysisInfo {

    /**
     * 时间段
     */
    private String planTime;

    /**
     * 创建姓名
     */
    private String createName;

    /**
     * 数量
     */
    private int count;

    /**
     * 总数量
     */
    private long totalCount;

    /**
     * 完成状态
     */
    private String completion;

    /**
     * 开始时间
     */
    private String timeBegin;

    /**
     * 结束时间
     */
    private String timeEnd;

    /**
     * 超时完成数量
     */
    private String percent;

    /**
     * 时间
     */
    private String createTime;


}
