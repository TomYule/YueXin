package com.yuexin.common.base;

//import io.nutz.nutzsite.module.sys.models.User;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.subject.Subject;
import org.nutz.dao.entity.annotation.*;
import org.nutz.lang.random.R;
import org.nutz.plugin.spring.boot.service.entity.DataBaseEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Hamming_Yu on 2018/12/29.
 */
public abstract class BaseModel extends DataBaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column("create_by")
    @Comment("创建者")
    @Prev(els = @EL("$me.uid()"))
    @ColDefine(type = ColType.VARCHAR, width = 32)
    protected String createBy;

    @Column("create_time")
    @Prev(els = {@EL("$me.now()")})
    protected LocalDateTime createTime;

    @Column("update_by")
    @Comment("更新者")
    @Prev(els = @EL("$me.uid()"))
    @ColDefine(type = ColType.VARCHAR, width = 32)
    protected String updateBy;

    @Prev(els=@EL("$me.now()"))
    @Column("update_time")
    protected LocalDateTime updateTime;

    /** 备注 */
    @Column("remark")
    @Comment("备注")
    protected String remark;

    public String uuid() {
        return R.UU32().toLowerCase();
    }

    public String uid() {
        try {
//            Subject subject = SecurityUtils.getSubject();
//            User user = (User) subject.getPrincipal();
//            return user == null ? "" : user.getId();
        } catch (Exception e) {
            return "";
        }
        return "";
    }

    public LocalDateTime now() {
        return LocalDateTime.now();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
