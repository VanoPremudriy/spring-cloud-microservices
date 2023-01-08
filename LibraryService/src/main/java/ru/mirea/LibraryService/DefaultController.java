package ru.mirea.LibraryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DefaultController {

    @Autowired
    BookRepo bookRepo;

    @Value("${eureka.instance.instance-id}")
    String uid;

    @GetMapping("/message")
    public String getMessage(){
        return "Message from resourses from " + uid;
    }

    @GetMapping("/get/{id}")
    public Book getBookById(@PathVariable Long id){
        return bookRepo.getBookById(id);
    }

    @GetMapping("get/all")
    public List<Book> getAllBooks(){
        return (ArrayList<Book>) bookRepo.findAll();
    }

    @PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
    public String saveBook(@RequestBody Book newBook){
        try {
            bookRepo.save(newBook);
        } catch (Throwable e){
            return e.getMessage();
        }
        return "Success";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBookById(@PathVariable Long id){
        try {
            bookRepo.deleteById(id);
        } catch (Throwable e){
            return e.getMessage();
        }
        return "Success";
    }

}
