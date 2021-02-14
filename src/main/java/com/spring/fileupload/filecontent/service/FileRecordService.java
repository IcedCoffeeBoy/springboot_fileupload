package com.spring.fileupload.filecontent.service;

import com.spring.fileupload.common.exception.BusinessException;
import com.spring.fileupload.common.exception.NotFoundException;
import com.spring.fileupload.common.utils.RandomGenerator;
import com.spring.fileupload.filecontent.database.FileRecord;
import com.spring.fileupload.filecontent.database.FileRecordRepository;
import com.spring.fileupload.filecontent.model.FileStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Handles all incoming file
 * 1) File information are recorded
 * 2) Validate the incoming file
 */
@Service
@Transactional
@RequiredArgsConstructor
public class FileRecordService {
    private final FileRecordRepository fileRecordRepository;

    public FileRecord validateAndSave(MultipartFile file) {
        String sha256 = FileContentUtils.getSha256(file);
        notEmpty(file);
        checkForExisting(sha256);
        return fileRecordRepository.save(FileRecord
                .builder()
                .name(file.getOriginalFilename())
                .sha256(sha256)
                .dataId(RandomGenerator.generate())
                .fileStatus(FileStatus.PROCESSING)
                .build());
    }

    public FileRecord findByDataId(String dataId) {
        return fileRecordRepository.findByDataId(dataId).orElseThrow(() -> new NotFoundException(FileRecord.class));
    }

    public void update(Long id, FileStatus status) {
        FileRecord record = fileRecordRepository.getOne(id);
        record.setFileStatus(status);
        fileRecordRepository.save(record);
    }


    /*-------------------------------- AUXILIARY FUNCTIONS -------------------------------- */
    private void notEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new BusinessException("File is empty!");
        }
    }

    private void checkForExisting(String sha256) {
        Optional<FileRecord> record = fileRecordRepository.findBySha256(sha256);
        if (record.isPresent()) {
            if (!record.get().getFileStatus().equals(FileStatus.ERROR)) {
                throw new BusinessException("The file have been uploaded before. Please check your file");
            } else {
                fileRecordRepository.delete(record.get());
            }
        }
    }

}
