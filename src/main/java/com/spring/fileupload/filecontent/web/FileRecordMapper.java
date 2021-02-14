package com.spring.fileupload.filecontent.web;

import com.spring.fileupload.config.mapstruct.AppMapperConfig;
import com.spring.fileupload.filecontent.database.FileRecord;
import com.spring.fileupload.filecontent.model.FileRecordDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        config = AppMapperConfig.class)
public abstract class FileRecordMapper {
    public abstract FileRecordDto toDto(FileRecord record);
}
