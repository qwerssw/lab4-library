package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Book;
import model.Reader;
import repository.BookRepository;

public class Library {
    // Создаем логгер для этого класса (ЛР №5)
    private static final Logger logger = LoggerFactory.getLogger(Library.class);
    
    private final BookRepository repository;

    public Library(BookRepository repository) {
        this.repository = repository;
    }

    public void addBook(Book book) {
        repository.save(book);
    }

    public void issueBook(int bookId, Reader reader) {
        Book book = repository.findById(bookId);
        
        if (book == null) {
            logger.error("Ошибка: Книга с ID {} не найдена", bookId); // ERROR
            throw new IllegalArgumentException("Книга не найдена: " + bookId);
        }
        
        if (book.isTaken()) {
            // WARN: книга уже занята, операция не выполнена, но это не ошибка программы
            logger.warn("Предупреждение: Попытка выдать книгу \"{}\", но она уже на руках", book.getTitle()); 
            throw new IllegalStateException("Книга уже на руках: " + book.getTitle());
        }
        
        book.setTaken(true);
        repository.save(book);
        // INFO: успешная операция
        logger.info("Успех: Книга \"{}\" выдана читателю {}", book.getTitle(), reader.getName());
    }

    public void returnBook(int bookId) {
        Book book = repository.findById(bookId);
        if (book == null) {
            logger.error("Ошибка: Книга с ID {} не найдена для возврата", bookId);
            throw new IllegalArgumentException("Книга не найдена: " + bookId);
        }
        if (!book.isTaken()) {
            logger.warn("Предупреждение: Попытка вернуть книгу \"{}\", которая и так в библиотеке", book.getTitle());
            throw new IllegalStateException("Книга и так в библиотеке");
        }
        book.setTaken(false);
        repository.save(book);
        // INFO: успешная операция
        logger.info("Успех: Книга \"{}\" возвращена в библиотеку", book.getTitle());
    }
}








