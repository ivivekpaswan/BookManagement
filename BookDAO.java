import java.util.List;

public interface BookDAO {
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(Long bookId);
    Book getBookById(Long bookId);
    List<Book> getAllBooks();
}