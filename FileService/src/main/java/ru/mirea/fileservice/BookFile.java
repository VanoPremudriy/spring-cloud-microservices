package ru.mirea.fileservice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "files")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookFile {

    @Id
    private Long id;

    @Column(name = "file_name")
    private String name;

}
