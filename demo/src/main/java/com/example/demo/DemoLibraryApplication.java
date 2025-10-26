package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoLibraryApplication.class, args);
    }

    @Bean
    CommandLineRunner init(Library library) {
        return args -> {
            Book b1 = new Book("Clean Code", "Robert C. Martin", "ISBN-001");
            Book b2 = new Book("Effective Java", "Joshua Bloch", "ISBN-002");
            library.addBook(b1);
            library.addBook(b2);

            System.out.println("All books: " + library.getAllBooks());
            System.out.println("Available before borrow: " + library.getAvailableBooks());

            library.borrowBook("ISBN-001");
            System.out.println("Available after borrow: " + library.getAvailableBooks());

            library.returnBook("ISBN-001");
            System.out.println("Available after return: " + library.getAvailableBooks());
        };
    }
}
