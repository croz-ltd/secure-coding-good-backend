package net.croz.owasp.goodexample.controller;

import net.croz.owasp.goodexample.controller.response.ProductResponse;
import net.croz.owasp.goodexample.entity.Product;
import net.croz.owasp.goodexample.mapper.CreateMapper;
import net.croz.owasp.goodexample.service.ProductService;
import net.croz.owasp.goodexample.service.StorageService;
import net.croz.owasp.goodexample.service.command.CreateProductCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final StorageService storageService;

    private final CreateMapper<Product, ProductResponse> productProductResponseCreateMapper;

    @Autowired
    public ProductController(ProductService productService, StorageService storageService,
        CreateMapper<Product, ProductResponse> productProductResponseCreateMapper) {
        this.productService = productService;
        this.storageService = storageService;
        this.productProductResponseCreateMapper = productProductResponseCreateMapper;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductResponse createProduct(@ModelAttribute CreateProductCommand createProductCommand) {
        return productProductResponseCreateMapper.map(productService.create(createProductCommand));
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);

        if (file == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
