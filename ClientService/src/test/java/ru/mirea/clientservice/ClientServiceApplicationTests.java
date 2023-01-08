package ru.mirea.clientservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import ru.mirea.clientservice.ClientsEntities.Book;

@SpringBootTest
class ClientServiceApplicationTests {

	RestTemplate restTemplate = new RestTemplate();

	@Test
	void first(){
		Book book = restTemplate.getForObject("http://localhost:8081/get/book/1", Book.class);
		assert book != null;
		System.out.println(book.getBookName());
	}

}
