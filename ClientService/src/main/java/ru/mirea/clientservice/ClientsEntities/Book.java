package ru.mirea.clientservice.ClientsEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
        private Long id;
        private String bookName;
        private String bookAuthor;
        private Integer bookYear;
}
