package com.spring.fileupload.filecontent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileRecordDto {
    private String dataId;
    private String name;
    private FileStatus fileStatus;
}
