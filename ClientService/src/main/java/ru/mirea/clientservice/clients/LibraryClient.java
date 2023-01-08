package ru.mirea.clientservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.mirea.clientservice.ClientsEntities.Book;

import java.util.List;

@FeignClient("library-service")
public interface LibraryClient {

    @RequestMapping(method = RequestMethod.GET, value = "/message")
    String getMessage();

    @GetMapping("/get/{id}")
    Book getBookById(@PathVariable Long id);

    @GetMapping("/get/all")
    List<Book> getAllBooks();

    @PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
    String saveBook(@RequestBody Book book);

    @DeleteMapping("/delete/{id}")
    String deleteBookById(@PathVariable Long id);
}
