package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Book;

public class InMemoryBookRepository implements BookRepository {
    private final Map<Integer, Book> storage = new HashMap<>();

    @Override
    public void save(Book book) {
        storage.put(book.getId(), book);
    }

    @Override
    public Book findById(int id) {
        return storage.get(id);
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(storage.values());
    }
}