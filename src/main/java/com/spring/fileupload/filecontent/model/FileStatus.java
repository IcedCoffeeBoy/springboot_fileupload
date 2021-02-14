package com.spring.fileupload.filecontent.model;

public enum FileStatus {
    PROCESSING("processing"),
    ERROR("error"),
    COMPLETED("completed");

    public final String label;

    FileStatus(String label) {
        this.label = label;
    }
}
