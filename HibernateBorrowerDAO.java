import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateBorrowerDAO implements BorrowerDAO {
    private final SessionFactory sessionFactory;

    public HibernateBorrowerDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void borrowBook(Long borrowerId, Long bookId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Borrower borrower = session.get(Borrower.class, borrowerId);
            Book book = session.get(Book.class, bookId);
            if (borrower != null && book != null) {
                borrower.setBorrowedBook(book);
                borrower.setBorrowedDate(new Date());
                session.update(borrower);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void returnBook(Long borrowerId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Borrower borrower = session.get(Borrower.class, borrowerId);
            if (borrower != null) {
                borrower.setBorrowedBook(null);
                borrower.setBorrowedDate(null);
                session.update(borrower);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Borrower> getAllBorrowers() {
        try (Session session = sessionFactory.openSession()) {
            Query<Borrower> query = session.createQuery("FROM Borrower", Borrower.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
