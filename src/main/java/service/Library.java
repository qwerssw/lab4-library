package service;

import model.Book;
import model.Reader;
import repository.BookRepository;

public class Library {
    private final BookRepository repository;

    // Dependency Injection
    public Library(BookRepository repository) {
        this.repository = repository;
    }

    public void addBook(Book book) {
        repository.save(book);
    }

    public void issueBook(int bookId, Reader reader) {
        Book book = repository.findById(bookId);
        
        if (book == null) {
            throw new IllegalArgumentException("Книга не найдена: " + bookId);
        }
        

        if (book.isTaken()) {
            throw new IllegalStateException("Книга уже на руках: " + book.getTitle());
        }
        
        book.setTaken(true);
        repository.save(book);
        System.out.println("Книга \"" + book.getTitle() + "\" выдана читателю " + reader.getName());
    }

    public void returnBook(int bookId) {
        Book book = repository.findById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("Книга не найдена: " + bookId);
        }
        if (!book.isTaken()) {
            throw new IllegalStateException("Книга и так в библиотеке");
        }
        book.setTaken(false);
        repository.save(book);
        System.out.println("Книга \"" + book.getTitle() + "\" возвращена");
    }
}