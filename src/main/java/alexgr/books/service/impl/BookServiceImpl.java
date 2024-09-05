package alexgr.books.service.impl;

import alexgr.books.model.Book;
import alexgr.books.repository.BookRepo;
import alexgr.books.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;

    @Override
    public Book createBook(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepo.findAll(pageable);
    }

    @Override
    public Book findBookById(Integer id) {
        return bookRepo.findBookById(id).orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public Book updateBook(Integer id, Book updatedBook) {
        return bookRepo.findById(id).map(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            return bookRepo.save(book);
        }).orElseThrow(() -> new EntityNotFoundException("Book not found"));
    }

    @Override
    public void deleteBook(Integer id) {
        bookRepo.deleteById(id);
    }
}
