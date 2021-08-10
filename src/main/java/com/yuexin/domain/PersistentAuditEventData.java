package com.yuexin.domain;


import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;
import java.time.Instant;

/**
 * Persist AuditEvent managed by the Spring Boot actuator.
 *
 * @see org.springframework.boot.actuate.audit.AuditEvent
 */
@Table("jhi_persistent_audit_evt_data")
public class PersistentAuditEventData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("event_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PersistentAuditEventData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", value='" + value + '\'' +
            '}';
    }
}
