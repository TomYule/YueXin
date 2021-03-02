package com.yuexin.domain;

import org.nutz.dao.entity.annotation.*;
import org.nutz.plugin.spring.boot.service.entity.DataBaseEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 表 jhi_user
 *
 * @author haiming
 * @date 2021-02-07
 */
@Table("jhi_user")
public class User extends DataBaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    @Comment("id")
    private Long id;

    /** login */
    @Column("login")
    @Comment("login")
    @Name
    private String login;

    /** password_hash */
    @Column("password_hash")
    @Comment("password_hash")
    private String passwordHash;

    /** first_name */
    @Column("first_name")
    @Comment("first_name")
    private String firstName;

    /** last_name */
    @Column("last_name")
    @Comment("last_name")
    private String lastName;

    /** email */
    @Column("email")
    @Comment("email")
    private String email;

    /** image_url */
    @Column("image_url")
    @Comment("image_url")
    private String imageUrl;

    /** activated */
    @Column("activated")
    @Comment("activated")
    private Boolean activated;

    /** lang_key */
    @Column("lang_key")
    @Comment("lang_key")
    private String langKey;

    /** activation_key */
    @Column("activation_key")
    @Comment("activation_key")
    private String activationKey;

    /** reset_key */
    @Column("reset_key")
    @Comment("reset_key")
    private String resetKey;

    /** created_by */
    @Column("created_by")
    @Comment("created_by")
    private String createdBy;

    /** created_date */
    @Column("created_date")
    @Comment("created_date")
    private Date createdDate;

    /** reset_date */
    @Column("reset_date")
    @Comment("reset_date")
    private Date resetDate;

    /** last_modified_by */
    @Column("last_modified_by")
    @Comment("last_modified_by")
    private String lastModifiedBy;

    /** last_modified_date */
    @Column("last_modified_date")
    @Comment("last_modified_date")
    private Date lastModifiedDate;

    @ManyMany(from = "user_id", relation = "jhi_user_authority", to = "authority_name")
    private List<Authority> authorities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getResetDate() {
        return resetDate;
    }

    public void setResetDate(Date resetDate) {
        this.resetDate = resetDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}
