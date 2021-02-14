package com.spring.fileupload.filecontent;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.io.FileInputStream;

import static com.spring.fileupload.filecontent.TestFileData.DATA_PATH_1;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FileContentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void postFileShouldReturnCreated() throws Exception {
        File f = new File(DATA_PATH_1);
        FileInputStream fi = new FileInputStream(f);
        MockMultipartFile file = new MockMultipartFile("file", f.getName(), "multipart/form-data", fi);
        this.mockMvc.perform(MockMvcRequestBuilders
                .multipart("/api/v1/file")
                .file(file)
        ).andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void postDuplicateFileShouldReturnFailed() throws Exception {
        File f = new File(DATA_PATH_1);
        FileInputStream fi = new FileInputStream(f);
        MockMultipartFile file = new MockMultipartFile("file", f.getName(), "multipart/form-data", fi);
        this.mockMvc.perform(MockMvcRequestBuilders
                .multipart("/api/v1/file")
                .file(file)
        ).andExpect(status().is4xxClientError());
    }
}
