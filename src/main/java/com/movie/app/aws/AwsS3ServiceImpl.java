package com.movie.app.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class AwsS3ServiceImpl implements AwsS3Service {
    @Value("${cloud.aws.bucket.name}")
    private String bucketName;
    @Value("${cloud.endpointUrl}")
    private String endpointUrl;

    @Autowired
    private AmazonS3 s3Client;

    @Override
    public String upload(MultipartFile multipartFile, String folderName) {
        File file = this.convertMultipartFileToFile(multipartFile);
        String fileName = folderName + "/" + System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        String fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
        PutObjectRequest putRequest = new PutObjectRequest(this.bucketName, fileName, file);
        putRequest.withCannedAcl(CannedAccessControlList.PublicRead);
        this.s3Client.putObject(putRequest);
        this.scheduleFileDeletion(file);
        return fileUrl;
    }

    private void scheduleFileDeletion(File file) {
        // You can use a scheduled task executor or a timer to delete the file after some time
        // For example, using a ScheduledExecutorService
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> {
            System.out.println("delete");
            file.delete();
            executorService.shutdown(); // Shut down the executor after deleting the file
        }, 5, TimeUnit.MINUTES); // Delete the file after 10 minutes (you can adjust this time as needed)
    }

    @Override

    public byte[] download(String fileName) {
        S3Object object = this.s3Client.getObject(this.bucketName, fileName);
        S3ObjectInputStream s3ObjectInputStream = object.getObjectContent();
        try {
            return IOUtils.toByteArray(s3ObjectInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String delete(String fileName) {
        this.s3Client.deleteObject(this.bucketName, fileName);
        return "Deleted!";
    }

    private File convertMultipartFileToFile(MultipartFile multipartFile) {
        File fileConvert = new File(multipartFile.getOriginalFilename());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileConvert);
            fileOutputStream.write(multipartFile.getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return fileConvert;
    }

}
