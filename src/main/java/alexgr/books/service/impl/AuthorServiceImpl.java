package alexgr.books.service.impl;

import alexgr.books.model.Author;
import alexgr.books.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorServiceImpl implements AuthorService {
    @Override
    public List<Author> getAllAuthors() {
        return List.of();
    }

    @Override
    public Author createAuthor(String name) {
        return null;
    }
}
