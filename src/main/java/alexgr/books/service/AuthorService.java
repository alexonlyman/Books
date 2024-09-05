package alexgr.books.service;

import alexgr.books.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAllAuthors();

    Author createAuthor(String name);
}
