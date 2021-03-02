package com.yuexin.domain;

import com.yuexin.common.base.BaseBean;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;


/**
 * 表 jhi_authority
 *
 * @author haiming
 * @date 2021-02-10
 */
@Table("jhi_authority")
public class Authority extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;


    @Name
    @Column("name")
    @Comment("name")
    @ColDefine(type = ColType.VARCHAR, width = 64)
    @Prev(els = {@EL("uuid()")})
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
