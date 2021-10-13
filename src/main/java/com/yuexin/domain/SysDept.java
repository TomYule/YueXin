package com.yuexin.domain;

import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.nutz.dao.entity.annotation.*;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import com.yuexin.common.base.BaseModel;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 部门表对象 sys_dept
 *
 * @author yuexin
 * @date 2021-10-13T11:22:01.147
 */
@ApiModel(description = "部门表 entity.\n@author yuexin")
@Table("sys_dept")
public class SysDept extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 部门id
     */
    @Id
    @Column("id")
    @Comment("部门id")
    private Long id;

    /**
     * 父部门id
     */
//    @Excel(name = "父部门id")
    @Column("parent_id")
    @Comment("父部门id")
    private Long parentId;

    /**
     * 祖级列表
     */
//    @Excel(name = "祖级列表")
    @Column("ancestors")
    @Comment("祖级列表")
    private String ancestors;

    /**
     * 部门名称
     */

//    @Excel(name = "部门名称")
    @Column("dept_name")
    @Comment("部门名称")
    private String deptName;

    /**
     * 显示顺序
     */
//    @Excel(name = "显示顺序")
    @Column("order_num")
    @Comment("显示顺序")
    private Integer orderNum;

    /**
     * 负责人
     */
//    @Excel(name = "负责人")
    @Column("leader")
    @Comment("负责人")
    private String leader;

    /**
     * 联系电话
     */
//    @Excel(name = "联系电话")
    @Column("phone")
    @Comment("联系电话")
    private String phone;

    /**
     * 邮箱
     */
//    @Excel(name = "邮箱")
    @Column("email")
    @Comment("邮箱")
    private String email;

    /**
     * 部门状态（0正常 1停用）
     */
//    @Excel(name = "部门状态", readConverterExp = "$column.readConverterExp()")
    @Column("status")
    @Comment("部门状态（0正常 1停用）")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @Column("del_flag")
    @Comment("删除标志（0代表存在 2代表删除）")
    private String delFlag;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getLeader() {
        return leader;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parentId", getParentId())
            .append("ancestors", getAncestors())
            .append("deptName", getDeptName())
            .append("orderNum", getOrderNum())
            .append("leader", getLeader())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
