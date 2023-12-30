package me.fernndinho.shop.files;


import me.fernndinho.shop.files.payload.FileResponse;
import me.fernndinho.shop.files.services.LocalFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/files/")
public class FilesController {
    @Autowired
    private LocalFileService localFileService;

    @PostMapping("/upload")
    public List<FileResponse> upload(@RequestParam("file") List<MultipartFile> files) {
        return localFileService.uploadFiles(files, "files");
    }


    @GetMapping("/{folder}/{fileName:.+}")
    public ResponseEntity<InputStreamResource> getFile(@PathVariable("folder") String folder, @PathVariable("fileName") String fileName) throws IOException {
        String url = localFileService.getFile(folder + '/' + fileName).getUrl();
        Path path = Paths.get(url);

        return ResponseEntity.ok()
                .contentLength(Files.size(path))
                .contentType(MediaType.IMAGE_JPEG) //TODO: add support for multiple file types
                .body(new InputStreamResource(Files.newInputStream(path)));
    }
}
