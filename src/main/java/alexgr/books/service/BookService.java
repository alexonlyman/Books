package alexgr.books.service;

import alexgr.books.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    Book createBook(Book book);

    Page<Book> getAllBooks(Pageable pageable);

    Book findBookById(Integer id);

    Book updateBook(Integer id, Book updatedBook);

    public void deleteBook(Integer id);
}
