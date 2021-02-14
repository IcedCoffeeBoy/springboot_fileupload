package com.spring.fileupload.config.audit;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditBaseEntity {
    @Version
    private Long version;

    @Column
    @CreatedBy
    private String createdBy;

    @Column
    @CreatedDate
    private LocalDateTime createdDate;

    @Column
    @LastModifiedBy
    private String lastModifiedBy;

    @Column
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
