package com.ruoyi.site.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 现场管理对象 tb_site_manage
 * 
 * @author ruoyi
 * @date 2020-08-16
 */
@Data
public class TbSiteManage
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 数量 */
    @Excel(name = "数量")
    private String num;

    /** 单价 */
    @Excel(name = "单价")
    private String unitPrice;

    /** 合价 */
    @Excel(name = "合价")
    private String totalPrice;

    @Excel(name = "创建时间")
    private String createTime;

    @Excel(name = "创建人")
    private String createBy;

    @Excel(name = "备注")
    private String remark;

}
