import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create SessionFactory
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Create HibernateBookDAO instance
        BookDAO bookDAO = new HibernateBookDAO(sessionFactory);

        // Add a book
        Book newBook = new Book("The Great Gatsby", "F. Scott Fitzgerald", 10);
        bookDAO.addBook(newBook);

        // Update a book
        Book bookToUpdate = bookDAO.getBookById(1L);
        if (bookToUpdate != null) {
            bookToUpdate.setQuantity(5);
            bookDAO.updateBook(bookToUpdate);
        }

        // Delete a book
        bookDAO.deleteBook(2L); // Assuming book with ID 2 exists

        // Get a book by ID
        Book retrievedBook = bookDAO.getBookById(3L); // Assuming book with ID 3 exists
        System.out.println("Retrieved Book: " + retrievedBook);

        // Get all books
        List<Book> allBooks = bookDAO.getAllBooks();
        System.out.println("All Books:");
        for (Book book : allBooks) {
            System.out.println(book);
        }

        // Close SessionFactory
        sessionFactory.close();
    }
}
