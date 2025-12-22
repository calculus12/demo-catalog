package com.example.democatalog.demo;

import com.example.democatalog.domain.Book;
import com.example.democatalog.persistence.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

@Component
@Profile("testdata")
@RequiredArgsConstructor
@Slf4j
public class BookDataLoader {
    private final BookRepository bookRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        try {
            var book1 = Book.of(
                    "1234567891",
                    "Northern Lights",
                    "Lyra Silverstar", 9.90
            );
            var book2 = Book.of(
                    "1234567892",
                    "Polar Journey",
                    "Iorek Polarson", 12.90
            );
            bookRepository.save(book1);
            bookRepository.save(book2);
        } catch (DuplicateKeyException e) {
            log.info("test data already exists");
        }
    }
}
