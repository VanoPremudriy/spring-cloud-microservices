package ru.mirea.clientservice.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mirea.clientservice.ClientsEntities.Book;
import ru.mirea.clientservice.clients.FileClient;
import ru.mirea.clientservice.clients.LibraryClient;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
public class DefaultController {

    String path = "/home/filesbuffer/";

    @Autowired
    LibraryClient libraryClient;

    @Autowired
    FileClient fileClient;

    @GetMapping("/mess")
    public String getMess(){
        return libraryClient.getMessage();
    }

    @GetMapping("/get/book/{id}")
    public Book getBookById(@PathVariable Long id){
        return libraryClient.getBookById(id);
    }

    @GetMapping("/get/book/all")
    public List<Book> getAllBooks(){
        return libraryClient.getAllBooks();
    }

    @PostMapping(value = "/save/book", consumes = "application/json", produces = "application/json")
    public String saveBook(@RequestBody Book book){
        return libraryClient.saveBook(book);
    }

    @DeleteMapping("/delete/book/{id}")
    String deleteBookById(@PathVariable Long id){
        fileClient.deleteFile(id);
        return libraryClient.deleteBookById(id);
    }

    @PostMapping("/save/file/{id}")
    String saveFile(@RequestBody MultipartFile file, @PathVariable Long id) throws IOException {
        try {
            Book b = libraryClient.getBookById(id);
            String type = file.getContentType().split("/")[1];
            String name = b.getBookName().replaceAll(" ", "_") + "." + type;
            return fileClient.saveFile(file.getBytes(), id, name);
        } catch (Throwable e ){
            return e.getMessage();
        }
    }

    @GetMapping("/get/file/{id}")
    ResponseEntity<Resource> getFileById(@PathVariable Long id, HttpServletRequest request) throws IOException {
        FileOutputStream fos = new FileOutputStream(path + fileClient.getFileNameById(id));
        fos.write(fileClient.getFile(id));
        File file = new File(path + fileClient.getFileNameById(id));
        fos.close();
        Resource resource = new UrlResource(file.toURI());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(request.getServletContext().getMimeType(resource.getFile().getAbsolutePath())))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete/file/{id}")
    String deleteFileById(@PathVariable Long id){
        return fileClient.deleteFile(id);
    }

    @DeleteMapping("/clear/buffer")
    String clearBuffer(){
        for (File myFile : new File(path).listFiles())
            if (myFile.isFile()) myFile.delete();
        return "success";
    }


}
