package com.spring.fileupload.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ProductService {
    private final ProductSpecificationsBuilder specificationsBuilder = new ProductSpecificationsBuilder();
    private final ProductRepository productRepository;

    public Page<Product> find(ProductFindRequest request) {
        return productRepository.findAll(specificationsBuilder.toSpecification(request), specificationsBuilder.toPageable(request));
    }

    @Async
    public CompletableFuture<Boolean> saveAll(List<Product> products) {
        try {
            long start = System.currentTimeMillis();
            List<Product> saved = productRepository.saveAll(products);
            log.info("Elapsed time: {}", (System.currentTimeMillis() - start));
            return CompletableFuture.completedFuture(true);
        } catch (Exception e) {
            log.error(e);
            return CompletableFuture.completedFuture(false);
        }
    }

}
