package net.croz.owasp.goodexample.service.impl;

import net.croz.owasp.goodexample.entity.ProductImage;
import net.croz.owasp.goodexample.exception.StorageException;
import net.croz.owasp.goodexample.exception.StorageFileNotFoundException;
import net.croz.owasp.goodexample.repository.ProductImageRepository;
import net.croz.owasp.goodexample.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class DatabaseStorageService implements StorageService {

    private final ProductImageRepository productImageRepository;

    @Autowired
    public DatabaseStorageService(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }

    @Override
    public ProductImage store(MultipartFile file) {
        try {
            final byte[] fileBytes = file.getBytes();
            final UUID fileUUID = UUID.nameUUIDFromBytes(fileBytes);
            final String fileExtension = getFileExtension(file);
            final String fileName = fileUUID + "." + fileExtension;

            final ProductImage productImage = new ProductImage();
            productImage.setData(fileBytes);
            productImage.setFilename(fileName);

            return productImageRepository.save(productImage);
        } catch (IOException e) {
            throw new StorageException("Can not store file");
        }
    }

    private String getFileExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        if (originalFilename != null && originalFilename.contains(".")) {
            return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        }

        throw new StorageException("Could not resolve extension");
    }

    @Override
    public Resource loadAsResource(String filename) {
        final ProductImage productImage = productImageRepository.findFirstByFilename(filename)
            .orElseThrow(() -> new StorageFileNotFoundException("Could not read file: " + filename));

        return new ByteArrayResource(productImage.getData());
    }

}
