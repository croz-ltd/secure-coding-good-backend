package net.croz.owasp.goodexample.service;

import org.springframework.web.multipart.MultipartFile;

public interface FilePreconditionService {

    boolean canUpload(MultipartFile file);

}
