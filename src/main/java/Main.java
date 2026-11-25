import org.apache.commons.lang3.StringUtils;

import model.Book;
import model.Reader;
import repository.BookRepository;
import repository.InMemoryBookRepository;
import service.Library;

public class Main {
    public static void main(String[] args) {
        BookRepository repository = new InMemoryBookRepository();
        Library library = new Library(repository);

        Book book1 = new Book(1, "Война и мир", "Л. Толстой");
        library.addBook(book1);

        Reader reader = new Reader(1, "Иван");

System.out.println("Вывод: Название книги: " + StringUtils.capitalize(book1.getTitle()));
        library.issueBook(1, reader); 
        
        try {
            library.issueBook(1, reader); 
        } catch (IllegalStateException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        
        library.returnBook(1);
    }
}