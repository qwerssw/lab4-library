package repository;

import java.util.List;

import model.Book;

public interface BookRepository {
    void save(Book book);
    Book findById(int id);
    List<Book> findAll();
}