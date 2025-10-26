package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class Library {
    private final List<Book> books;

    public Library() {
        this.books = new CopyOnWriteArrayList<>();
    }

    public Library(List<Book> books) {
        this.books = new CopyOnWriteArrayList<>(books);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Book findBookByIsbn(String isbn) {
        if (isbn == null) return null;
        return books.stream()
                .filter(b -> isbn.equals(b.getIsbn()))
                .findFirst()
                .orElse(null);
    }

    public List<Book> getAvailableBooks() {
        return books.stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }

    public void borrowBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book != null && book.isAvailable()) {
            book.borrow();
        }
    }

    public void returnBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book != null && !book.isAvailable()) {
            book.returnBook();
        }
    }

    public List<Book> getAllBooks() {
        return List.copyOf(books);
    }
}
