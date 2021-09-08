package com.yuexin.common.base;

import org.nutz.lang.random.R;
import org.nutz.plugin.spring.boot.service.entity.DataBaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : haiming
 * @date : 2020-02-27
 */
public abstract class BaseBean extends DataBaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public String uuid() {
        return R.UU32().toLowerCase();
    }

    public String uid() {
        try {

            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public Date now() {
        return new Date();
    }
}
