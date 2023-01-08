package ru.mirea.fileservice;


import org.springframework.data.repository.CrudRepository;

public interface FileRepo extends CrudRepository<BookFile, Long> {
    BookFile getBookFileById(Long id);
}
