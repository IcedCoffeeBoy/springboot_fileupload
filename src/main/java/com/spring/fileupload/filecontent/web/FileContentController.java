package com.spring.fileupload.filecontent.web;

import com.spring.fileupload.filecontent.database.FileRecord;
import com.spring.fileupload.filecontent.model.FileRecordDto;
import com.spring.fileupload.filecontent.model.FileStatus;
import com.spring.fileupload.filecontent.service.FileContentService;
import com.spring.fileupload.filecontent.service.FileRecordService;
import com.spring.fileupload.product.Product;
import com.spring.fileupload.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "File Api")
@RestController
@RequestMapping(value = "/api/v1/file", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class FileContentController {
    private final FileRecordService fileRecordService;
    private final FileContentService fileContentService;
    private final FileRecordMapper recordMapper;
    private final ProductService productService;

    @GetMapping("/:dataId")
    public ResponseEntity<FileRecordDto> findByDataId(@RequestParam("dataId") String dataId) {
        return ResponseEntity.ok(recordMapper.toDto(fileRecordService.findByDataId(dataId)));

    }

    @Operation(summary = "Upload a file containing products")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadProductFile(@RequestParam("file") MultipartFile file) {
        FileRecord record = fileRecordService.validateAndSave(file);
        Long recordId = record.getId();
        List<Product> products = fileContentService.convertToProductList(file, record);
        productService.saveAll(products).thenApply(outcome -> {
            if (outcome) {
                fileRecordService.update(recordId, FileStatus.COMPLETED);
            } else {
                fileRecordService.update(recordId, FileStatus.ERROR);
            }
            return null;
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(recordMapper.toDto(record));
    }

}
