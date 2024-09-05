package alexgr.books.repository;

import alexgr.books.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepo extends JpaRepository<Book,Integer> {
    Optional<Book> findBookById(Integer id);

}
