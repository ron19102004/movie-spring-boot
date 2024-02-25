package com.movie.app.aws;

import org.springframework.web.multipart.MultipartFile;

public interface AwsS3Service {
    String upload(MultipartFile multipartFile,String folderName);

    byte[] download(String fileName,String folder);

    String delete(String fileName);
}
