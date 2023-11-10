package net.croz.owasp.goodexample.service;

import net.croz.owasp.goodexample.entity.ProductImage;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    ProductImage store(MultipartFile file);

    Resource loadAsResource(String filename);

}
