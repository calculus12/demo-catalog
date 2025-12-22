package com.example.democatalog.presentation.controller.book;

import com.example.democatalog.domain.BookNotFoundException;
import com.example.democatalog.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    void bookNotFoundShouldReturn404() throws Exception {
        String isbn = "1234567890";
        Mockito.when(bookService.viewBookDetails(Mockito.eq(isbn))).thenThrow(BookNotFoundException.class);

        mockMvc
                .perform(MockMvcRequestBuilders.get("/books/{isbn}", isbn))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}