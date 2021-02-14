package com.spring.fileupload.filecontent.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRecordRepository extends JpaRepository<FileRecord, Long> {
    Optional<FileRecord> findBySha256(String sha256);

    Optional<FileRecord> findByDataId(String dataId);
}
