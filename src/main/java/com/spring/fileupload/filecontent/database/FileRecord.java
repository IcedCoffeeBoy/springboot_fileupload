package com.spring.fileupload.filecontent.database;

import com.spring.fileupload.config.audit.AuditBaseEntity;
import com.spring.fileupload.filecontent.model.FileStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;


/**
 * File record for saving all information for all files uploaded
 */
@Entity
@SequenceGenerator(name = "fileRecordSeqId", sequenceName = "file_record_seq_id", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileRecord extends AuditBaseEntity {
    @Id
    @GeneratedValue(generator = "fileContentId", strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Size(max = 64)
    @Column(name = "SHA_256", nullable = false, unique = true)
    private String sha256;

    @Column
    @Enumerated(EnumType.STRING)
    private FileStatus fileStatus;

    @Column
    private String dataId;

}
