package ru.mirea.LibraryService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@SpringBootTest
class LibraryServiceApplicationTests {

//	RestTemplate restTemplate = new RestTemplate();
//
//	@Test
//	void first(){
//		Book book = restTemplate.getForObject("http://localhost:8082/get/1", Book.class);
//		assert book != null;
//		System.out.println(book.getBookName());
//	}
//
//	@Test
//	void second(){
//		ResponseEntity<ArrayList> books = restTemplate.getForEntity("http://localhost:8082/get/all", ArrayList.class);
//		assert books != null;
//		System.out.println(books.getBody().get(0));
//	}

}
