package com.spring.fileupload.filecontent.service;

import com.google.common.hash.Hashing;
import com.google.common.io.ByteSource;
import com.spring.fileupload.common.utils.RandomGenerator;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Log4j2
public class FileContentUtils {
    public static String getSha256(MultipartFile file) {
        try {
            ByteSource byteSource = new ByteSource() {
                @Override
                public InputStream openStream() throws IOException {
                    return file.getInputStream();
                }
            };
            String sha256 = byteSource.hash(Hashing.sha256()).toString();
            return sha256;
        } catch (IOException e) {
            log.error("Something went wrong when extracting SHA-256");
            log.error(e.getMessage());
            throw new RuntimeException("Something went wrong when uploading file");
        }
    }

    public static String getSha256(Resource file) {
        try {
            ByteSource byteSource = new ByteSource() {
                @Override
                public InputStream openStream() throws IOException {
                    return file.getInputStream();
                }
            };
            String sha256 = byteSource.hash(Hashing.sha256()).toString();
            return sha256;
        } catch (IOException e) {
            log.error("Something went wrong when extracting SHA-256");
            log.error(e.getMessage());

            throw new RuntimeException("Something went wrong when uploading file");
        }
    }


    public static byte[] convertMultipartToBytes(MultipartFile file) {
        try {
            ByteSource byteSource = new ByteSource() {
                @Override
                public InputStream openStream() throws IOException {
                    return file.getInputStream();
                }
            };
            return byteSource.read();
        } catch (IOException e) {
            log.error("An error occurred while converting file to bytes");
            log.error(e.getMessage());

            throw new RuntimeException("Something went wrong when uploading file");
        }
    }
}
