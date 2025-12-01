package com.example.democatalog.persistence;

import com.example.democatalog.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryBookRepository implements BookRepository {
    private static final Map<String, Book> memoryMap = new ConcurrentHashMap<>();

    @Override
    public Iterable<Book> findAll() {
        return memoryMap.values();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(memoryMap.get(isbn));
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return memoryMap.containsKey(isbn);
    }

    @Override
    public Book save(Book book) {
        memoryMap.put(book.isbn(), book);
        return book;
    }

    @Override
    public void deleteByIsbn(String isbn) {
        memoryMap.remove(isbn);
    }
}
