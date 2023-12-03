package net.croz.owasp.goodexample.service.impl;

import org.apache.tika.Tika;
import net.croz.owasp.goodexample.service.FilePreconditionService;
import org.apache.tika.mime.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

@Service
public class FilePreconditionServiceImpl implements FilePreconditionService {

    private static final Tika tika = new Tika();
    private static final Set<MediaType> mediaTypes;

    static {
        final MediaType jpeg = MediaType.image("jpeg");
        final MediaType jpg = MediaType.image("jpg");
        final MediaType tiff = MediaType.image("tiff");
        final MediaType png = MediaType.image("png");

        mediaTypes = Set.of(jpeg, tiff, jpg, png);
    }

    @Override
    public boolean canUpload(MultipartFile file) {
        try {
            final String mimeDetectionMimeType = tika.detect(file.getInputStream());
            final String nameDetectionMimeType = tika.detect(file.getOriginalFilename());

            if (!Objects.equals(nameDetectionMimeType, mimeDetectionMimeType)) {
                return false;
            }

            return mediaTypes.stream()
                .anyMatch(mediaType -> mediaType.equals(MediaType.parse(mimeDetectionMimeType)));
        } catch (IOException e) {
            return false;
        }
    }

}
