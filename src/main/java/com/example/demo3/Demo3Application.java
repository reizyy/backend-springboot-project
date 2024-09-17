package com.example.demo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class Demo3Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo3Application.class, args);
	}

	@RestController
	public class BookController {
		
		@GetMapping("/books")
		public String getBook() {
			return "Book1,Book2,Book3";
		}
	}

}
