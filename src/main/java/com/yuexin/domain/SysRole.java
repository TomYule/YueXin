package com.yuexin.domain;

import com.yuexin.common.base.BaseModel;
import io.swagger.annotations.ApiModel;
import org.nutz.dao.entity.annotation.*;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色信息表 entity.\n@author haiming
 */
@ApiModel(description = "角色信息表 entity.\n@author haiming")
@Table("sys_role")
public class SysRole extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    @Comment("角色ID")
    private Long id;

    /** 角色名称 */
    @Column("role_name")
    @Comment("角色名称")
    private String roleName;

    /** 角色权限字符串 */
    @Column("role_key")
    @Comment("角色权限字符串")
    private String roleKey;

    /** 显示顺序 */
    @Column("role_sort")
    @Comment("显示顺序")
    private Integer roleSort;

    /** 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限） */
    @Column("data_scope")
    @Comment("数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）")
    private String dataScope;

    /** 菜单树选择项是否关联显示 */
    @Column("menu_check_strictly")
    @Comment("菜单树选择项是否关联显示")
    private Integer menuCheckStrictly;

    /** 部门树选择项是否关联显示 */
    @Column("dept_check_strictly")
    @Comment("部门树选择项是否关联显示")
    private Integer deptCheckStrictly;

    /** 角色状态（0正常 1停用） */
    @Column("status")
    @Comment("角色状态（0正常 1停用）")
    private Boolean status;

    /** 删除标志（0代表存在 2代表删除） */
    @Column("del_flag")
    @Comment("删除标志（0代表存在 2代表删除）")
    private Boolean delFlag;

    /** 创建者 */
    @Column("create_by")
    @Comment("创建者")
    private String createBy;

    /** 创建时间 */
    @Column("create_time")
    @Comment("创建时间")
    private Date createTime;

    /** 更新者 */
    @Column("update_by")
    @Comment("更新者")
    private String updateBy;

    /** 更新时间 */
    @Column("update_time")
    @Comment("更新时间")
    private Date updateTime;

    /** 备注 */
    @Column("remark")
    @Comment("备注")
    private String remark;

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

    public Integer getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(Integer roleSort) {
        this.roleSort = roleSort;
    }

    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public Integer getMenuCheckStrictly() {
        return menuCheckStrictly;
    }

    public void setMenuCheckStrictly(Integer menuCheckStrictly) {
        this.menuCheckStrictly = menuCheckStrictly;
    }

    public Integer getDeptCheckStrictly() {
        return deptCheckStrictly;
    }

    public void setDeptCheckStrictly(Integer deptCheckStrictly) {
        this.deptCheckStrictly = deptCheckStrictly;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
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

    @Override
    public String toString() {
        return "SysRole{" +
            "id=" + id +
            ", roleName='" + roleName + '\'' +
            ", roleKey='" + roleKey + '\'' +
            ", roleSort=" + roleSort +
            ", dataScope='" + dataScope + '\'' +
            ", menuCheckStrictly=" + menuCheckStrictly +
            ", deptCheckStrictly=" + deptCheckStrictly +
            ", status=" + status +
            ", delFlag=" + delFlag +
            ", createBy='" + createBy + '\'' +
            ", createTime=" + createTime +
            ", updateBy='" + updateBy + '\'' +
            ", updateTime=" + updateTime +
            ", remark='" + remark + '\'' +
            ", sysMenus=" + sysMenus +
            ", sysUsers=" + sysUsers +
            '}';
    }
}
