package com.example.democatalog.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
class BookJsonTests {

    @Autowired
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws Exception {
        var book = new Book("1234567890", "Title", "Author", 9.2);
        var jsonContent = json.write(book);

        Assertions.assertThat(jsonContent).extractingJsonPathStringValue("@.isbn")
                .isEqualTo("1234567890");
        Assertions.assertThat(jsonContent).extractingJsonPathStringValue("@.title")
                .isEqualToIgnoringCase("Title");
        Assertions.assertThat(jsonContent).extractingJsonPathStringValue("@.author")
                .isEqualToIgnoringCase("Author");
        Assertions.assertThat(jsonContent).extractingJsonPathNumberValue("@.price")
                .isEqualTo(9.2);
    }

    @Test
    void testDeserialize() throws Exception {
        var content = """
                {
                "isbn": "1234567890",
                "title": "Title",
                "author": "Author",
                "price": 9.2
                }
                """;
        var jsonContent = json.parse(content);
        Assertions.assertThat(jsonContent).isNotNull();
        Assertions.assertThat(jsonContent)
                .usingRecursiveComparison()
                .isEqualTo(new Book("1234567890", "Title", "Author", 9.2));
    }
}