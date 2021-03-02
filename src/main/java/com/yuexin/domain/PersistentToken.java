package com.yuexin.domain;

import com.yuexin.common.base.BaseBean;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
/**
 * 表 jhi_persistent_token
 *
 * @author haiming
 * @date 2021-02-07
 */
@Table("jhi_persistent_token")
public class PersistentToken extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Name
    @Column("series")
    @Comment("series")
    @ColDefine(type = ColType.VARCHAR, width = 64)
    @Prev(els = {@EL("uuid()")})
    private String series;

    /** user_id */
    @Column("user_id")
    @Comment("user_id")
    private Long userId;

    /** token_value */
    @Column("token_value")
    @Comment("token_value")
    private String tokenValue;

    /** token_date */
    @Column("token_date")
    @Comment("token_date")
    private LocalDate tokenDate;

    /** ip_address */
    @Column("ip_address")
    @Comment("ip_address")
    private String ipAddress;

    /** user_agent */
    @Column("user_agent")
    @Comment("user_agent")
    private String userAgent;


    @One(target = User.class, field = "userId")
    private User user;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public LocalDate getTokenDate() {
        return tokenDate;
    }

    public void setTokenDate(LocalDate tokenDate) {
        this.tokenDate = tokenDate;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
