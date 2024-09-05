package alexgr.books.repository;

import alexgr.books.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Integer> {

}
