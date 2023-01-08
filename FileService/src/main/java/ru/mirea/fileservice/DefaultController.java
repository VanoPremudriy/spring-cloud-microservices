package ru.mirea.fileservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

@RestController
public class DefaultController {

    //String path = "D:\\IdeaProjects\\RKSP\\PR8\\Files\\";
    String path = "/home/files/";

    @Autowired
    FileRepo fileRepo;

    @GetMapping("/get/{id}")
    private byte[] getFile(@PathVariable Long id) throws IOException {
        File file = new File(path + fileRepo.getBookFileById(id).getName());
        return Files.readAllBytes(file.toPath());
    }

    @PostMapping("/save/{id}/{filename}")
    private String saveFile(@RequestBody byte[] bytes,@PathVariable Long id, @PathVariable String filename) throws IOException {

        FileOutputStream fos = new FileOutputStream(path + filename);
        fos.write(bytes);

        fileRepo.save(new BookFile(id, filename));

        return "success " + id;
    }

    @DeleteMapping("delete/{id}")
    private String deleteFile(@PathVariable Long id){
        File file = new File(path + fileRepo.getBookFileById(id).getName());
        if (file.delete()) {
            fileRepo.deleteById(id);
            return "Success";
        }
        else return "Failed";
    }

    @GetMapping("/name/{id}")
    private String getFileName(@PathVariable Long id){
        return fileRepo.getBookFileById(id).getName();
    }

}
