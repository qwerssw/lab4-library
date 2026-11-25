import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Book;
import model.Reader;
import repository.BookRepository;
import repository.InMemoryBookRepository;
import service.Library;

public class LibraryServiceTest {

    private Library library;
    private Book book;

    @BeforeEach
    public void setUp() {
        BookRepository repository = new InMemoryBookRepository();
        library = new Library(repository);
        book = new Book(1, "Война и мир", "Л. Н. Толстой");
        library.addBook(book);
    }

    @Test
    public void testIssueBookSuccess() {
        Reader reader = new Reader(1, "Иван");
        library.issueBook(1, reader);
        assertTrue(book.isTaken());
    }

    @Test
    public void testCannotIssueAlreadyTakenBook() {
        Reader reader = new Reader(1, "Иван");
        library.issueBook(1, reader);
        
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> library.issueBook(1, reader));
        assertEquals("Книга уже на руках: Война и мир", exception.getMessage());
    }

    @Test
    public void testAnotherReaderCannotTakeTakenBook() {
        Reader reader1 = new Reader(1, "Иван");
        Reader reader2 = new Reader(2, "Пётр");
        library.issueBook(1, reader1);
        
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> library.issueBook(1, reader2));
                
        assertEquals("Книга уже на руках: Война и мир", exception.getMessage());
    }

   
    @Test
    public void testReturnBookSuccess() {
        Reader reader = new Reader(1, "Полина");
        library.issueBook(1, reader); 
        library.returnBook(1);        
        assertFalse(book.isTaken());  
    }
}