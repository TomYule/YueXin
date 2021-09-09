package com.yuexin.domain;

import com.yuexin.common.base.BaseModel;
import io.swagger.annotations.ApiModel;
import org.nutz.dao.entity.annotation.*;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.plugin.spring.boot.service.entity.DataBaseEntity;
import org.nutz.plugins.validation.annotation.Validations;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色信息表 entity.\n@author haiming
 */
@ApiModel(description = "角色信息表 entity.\n@author haiming")
@Table("sys_role")
public class SysRole extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @org.nutz.dao.entity.annotation.Column
    @Id
    @Comment("ID")
    private Long id;

    /**
     * 角色名称
     */
    @org.nutz.dao.entity.annotation.Column("role_name")
    @Comment("角色名称 ")
    @Validations(required = true, errorMsg = "角色名称不能为空")
    private String roleName;

    /**
     * 角色权限
     */
    @org.nutz.dao.entity.annotation.Column("role_key")
    @Comment("角色权限")
    @Validations(required = true, errorMsg = "角色权限不能为空")
    private String roleKey;

    /**
     * 角色排序
     */
    @org.nutz.dao.entity.annotation.Column("role_sort")
    @Comment("角色排序")
    @Validations(required = true, errorMsg = "角色排序不能为空")
    private String roleSort;

    /**
     * 数据范围（1：所有数据权限；2：自定义数据权限）
     */
    @org.nutz.dao.entity.annotation.Column("data_scope")
    @Comment("数据范围 ")
    private String dataScope;

    /**
     * 角色状态（0正常 1停用）
     */
    @org.nutz.dao.entity.annotation.Column("status")
    @Comment("角色状态（0正常 1停用） ")
    private boolean status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @org.nutz.dao.entity.annotation.Column("del_flag")
    @Comment("删除标记")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean delFlag;

    /**
     * 用户是否存在此角色标识 默认不存在
     */
    private boolean flag = false;

    @Column
    @Comment("备注")
    private String remark;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "role_name")
//    private String roleName;
//
//    @Column(name = "role_key")
//    private String roleKey;
//
//    @Column(name = "role_sort")
//    private Integer roleSort;
//
//    @Column(name = "data_scope")
//    private String dataScope;
//
//    @Column(name = "menu_check_strictly")
//    private Integer menuCheckStrictly;
//
//    @Column(name = "dept_check_strictly")
//    private Integer deptCheckStrictly;
//
//    @Column(name = "status")
//    private String status;
//
//    @Column(name = "del_flag")
//    private String delFlag;
//
//    @Column(name = "create_by")
//    private String createBy;
//
//    @Column(name = "create_time")
//    private LocalDate createTime;
//
//    @Column(name = "update_by")
//    private String updateBy;
//
//    @Column(name = "up_local_date")
//    private LocalDate upLocalDate;
//
//    @Lob
//    @Column(name = "remark")
//    private String remark;

//    @ManyToMany
//    @JoinTable(name = "sys_role_sys_menu",
//               joinColumns = @JoinColumn(name = "sys_role_id", referencedColumnName = "id"),
//               inverseJoinColumns = @JoinColumn(name = "sys_menu_id", referencedColumnName = "id"))
    private Set<SysMenu> sysMenus = new HashSet<>();

//    @ManyToMany
//    @JoinTable(name = "sys_role_sys_user",
//               joinColumns = @JoinColumn(name = "sys_role_id", referencedColumnName = "id"),
//               inverseJoinColumns = @JoinColumn(name = "sys_user_id", referencedColumnName = "id"))
    private Set<SysUser> sysUsers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(String roleSort) {
        this.roleSort = roleSort;
    }

    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isDelFlag() {
        return delFlag;
    }

    public void setDelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<SysMenu> getSysMenus() {
        return sysMenus;
    }

    public void setSysMenus(Set<SysMenu> sysMenus) {
        this.sysMenus = sysMenus;
    }

    public Set<SysUser> getSysUsers() {
        return sysUsers;
    }

    public void setSysUsers(Set<SysUser> sysUsers) {
        this.sysUsers = sysUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysRole)) {
            return false;
        }
        return id != null && id.equals(((SysRole) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysRole{" +
            "id=" + getId() +
            ", roleName='" + getRoleName() + "'" +
            ", roleKey='" + getRoleKey() + "'" +
            ", roleSort=" + getRoleSort() +
            ", dataScope='" + getDataScope() + "'" +
            ", createBy='" + getCreateBy() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateBy='" + getUpdateBy() + "'" +
            ", remark='" + getRemark() + "'" +
            "}";
    }
}
