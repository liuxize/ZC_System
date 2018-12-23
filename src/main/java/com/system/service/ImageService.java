package com.system.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;

public interface ImageService {
     String uploadImage(CommonsMultipartFile file, String realUploadPath) throws IOException;
}
