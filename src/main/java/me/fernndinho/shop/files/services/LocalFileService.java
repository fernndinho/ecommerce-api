package me.fernndinho.shop.files.services;

import com.google.common.collect.Lists;
import me.fernndinho.shop.files.repo.FileRepository;
import me.fernndinho.shop.files.models.FileEntity;
import me.fernndinho.shop.files.payload.FileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LocalFileService implements FileService {
    public final static Path BASE_PATH = Paths.get("files");

    @Autowired
    private FileRepository fileRepo;


    @Override
    public FileResponse uploadFile(MultipartFile file, String folder)  {
        if(file.getOriginalFilename() == null) throw new RuntimeException();

        String randomName = generateName(file.getOriginalFilename());

        Path folderPath = BASE_PATH.resolve(folder);
        Path filePath = folderPath.resolve(randomName);

        try {
            if(Files.notExists(folderPath)) {
                Files.createDirectories(folderPath);
            }

            Files.write(filePath, file.getBytes());

            FileEntity entity = new FileEntity(null, "/" + folder + "/" + randomName);

            fileRepo.save(entity);

            return new FileResponse(entity.getPath());
        } catch (IOException e) {
            throw new RuntimeException("Error saving the file");
        }
    }

    @Override
        public List<FileResponse> uploadFiles(List<MultipartFile> files, String folder) {
            try {
                Path folderPath = BASE_PATH.resolve(folder);
                List<FileEntity> entities = Lists.newArrayList();

                if(Files.notExists(folderPath)) {
                    Files.createDirectories(folderPath);
                }

                for(MultipartFile file : files) {//TODO: averiguar si se puede hacer mejor
                    if(file.getOriginalFilename() == null) throw new RuntimeException();
                    String randomName = generateName(file.getOriginalFilename());

                    Path filePath = folderPath.resolve(randomName);
                    Files.write(filePath, file.getBytes());

                    FileEntity entity = new FileEntity(null, "/" + folder + "/" + randomName);
                    entities.add(entity);
                }

                return entities.stream()
                        .map(fe -> new FileResponse(fe.getPath()))
                        .collect(Collectors.toList());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    @Override
    public boolean deleteFile(String path) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public boolean deleteFiles(List<String> paths) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public FileResponse getFile(String path) {
        Path p = BASE_PATH.resolve(path);
        return new FileResponse(p.toString());
    }

    private String generateName(String filename) {
        String extension = filename.split("\\.")[1];

        return UUID.randomUUID().toString() + "." + extension;
    }
}