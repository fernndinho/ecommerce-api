package me.fernndinho.shop.files.services;

import me.fernndinho.shop.files.payload.FileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    FileResponse uploadFile(MultipartFile file, String folder);

    List<FileResponse> uploadFiles(List<MultipartFile> files, String folder);

    boolean deleteFile(String path);

    boolean deleteFiles(List<String> paths);

    FileResponse getFile(String path);
}
