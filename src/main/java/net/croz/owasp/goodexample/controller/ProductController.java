package net.croz.owasp.goodexample.controller;

import net.croz.owasp.goodexample.controller.response.ProductCommentResponse;
import net.croz.owasp.goodexample.controller.response.ProductResponse;
import net.croz.owasp.goodexample.entity.Product;
import net.croz.owasp.goodexample.entity.ProductComment;
import net.croz.owasp.goodexample.mapper.CreateMapper;
import net.croz.owasp.goodexample.service.ProductService;
import net.croz.owasp.goodexample.service.StorageService;
import net.croz.owasp.goodexample.service.command.CreateProductCommand;
import net.croz.owasp.goodexample.service.command.CreateProductCommentCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final StorageService storageService;

    private final CreateMapper<Product, ProductResponse> productProductResponseCreateMapper;

    private final CreateMapper<ProductComment, ProductCommentResponse> productCommentProductCommentResponseCreateMapper;

    @Autowired
    public ProductController(ProductService productService, StorageService storageService,
        CreateMapper<Product, ProductResponse> productProductResponseCreateMapper,
        CreateMapper<ProductComment, ProductCommentResponse> productCommentProductCommentResponseCreateMapper) {
        this.productService = productService;
        this.storageService = storageService;
        this.productProductResponseCreateMapper = productProductResponseCreateMapper;
        this.productCommentProductCommentResponseCreateMapper = productCommentProductCommentResponseCreateMapper;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductResponse createProduct(@ModelAttribute CreateProductCommand createProductCommand) {
        return productProductResponseCreateMapper.map(productService.create(createProductCommand));
    }

    @GetMapping("/{id}")
    public ProductResponse findOne(@PathVariable Long id) {
        return productProductResponseCreateMapper.map(productService.findById(id));
    }

    @GetMapping
    public List<ProductResponse> findAll() {
        return productProductResponseCreateMapper.mapToList(productService.findAll());
    }

    @PostMapping("/{id}/comment")
    public ProductCommentResponse createComment(@PathVariable Long id,
        @RequestBody CreateProductCommentCommand createProductCommentCommand) {
        return productCommentProductCommentResponseCreateMapper.map(
            productService.createComment(id, createProductCommentCommand));
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);

        if (file == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
