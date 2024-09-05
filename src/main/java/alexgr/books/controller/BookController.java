package alexgr.books.controller;

import alexgr.books.model.Book;
import alexgr.books.repository.BookRepo;
import alexgr.books.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Book>> getAllBooks(Pageable pageable) {
        Page<Book> books = bookService.getAllBooks(pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        Book book = bookService.findBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody Book updatedBook) {
        try {
            Book book = bookService.updateBook(id, updatedBook);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
