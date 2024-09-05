package alexgr.books.controller;

import alexgr.books.model.Book;
import alexgr.books.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBooks() throws Exception {
        Book book1 = new Book();
        book1.setId(1);
        book1.setTitle("Book One");

        Book book2 = new Book();
        book2.setId(2);
        book2.setTitle("Book Two");

        List<Book> books = Arrays.asList(book1, book2);
        Pageable pageable = PageRequest.of(0, 2);
        Page<Book> page = new PageImpl<>(books, pageable, books.size());

        given(bookService.getAllBooks(pageable)).willReturn(page);


        mockMvc.perform(get("/api/books?page=0&size=2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].title").value("Book Two"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value(2));
    }


}