package com.example.democatalog.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@JsonTest
class BookJsonTests {

    @Autowired
    private JacksonTester<Book> json;

    private Instant createdDate;
    private Instant lastModifiedDate;

    @BeforeEach
    void setUp() {
        LocalDateTime createdLocalDateTime = LocalDateTime.of(2025, 12, 14, 14, 0, 0);
        LocalDateTime lastModifiedLocalDateTime = LocalDateTime.of(2025, 12, 14, 14, 0, 1);

        createdDate = createdLocalDateTime.toInstant(ZoneOffset.UTC);
        lastModifiedDate = lastModifiedLocalDateTime.toInstant(ZoneOffset.UTC);
    }

    @Test
    void testSerialize() throws Exception {
        var book = new Book(0L,"1234567890", "Title", "Author", 9.2,
                "Publisher", createdDate, lastModifiedDate, 0);
        var jsonContent = json.write(book);

        Assertions.assertThat(jsonContent).extractingJsonPathNumberValue("@.id")
                .isEqualTo(0);
        Assertions.assertThat(jsonContent).extractingJsonPathStringValue("@.isbn")
                .isEqualTo("1234567890");
        Assertions.assertThat(jsonContent).extractingJsonPathStringValue("@.title")
                .isEqualToIgnoringCase("Title");
        Assertions.assertThat(jsonContent).extractingJsonPathStringValue("@.author")
                .isEqualToIgnoringCase("Author");
        Assertions.assertThat(jsonContent).extractingJsonPathStringValue("@.publisher")
                .isEqualToIgnoringCase("Publisher");
        Assertions.assertThat(jsonContent).extractingJsonPathNumberValue("@.price")
                .isEqualTo(9.2);
    }

    /*
    @Disabled
    @Test
    void testDeserialize() throws Exception {
        var content = """
                {
                "id": 0,
                "isbn": "1234567890",
                "title": "Title",
                "author": "Author",
                "price": 9.2,
                "createdDate": "2025-12-14T14:00:00Z",
                "lastModifiedDate": "2025-12-14T14:00:01Z",
                "version": 0
                }
                """;
        var jsonContent = json.parse(content);
        Assertions.assertThat(jsonContent).isNotNull();
        Assertions.assertThat(jsonContent)
                .usingRecursiveComparison()
                .isEqualTo(new Book(0L, "1234567890", "Title", "Author", 9.2, "", createdDate, lastModifiedDate, 0));
    }
    */
}