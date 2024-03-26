import java.util.List;

public interface BorrowerDAO {
    void borrowBook(Long borrowerId, Long bookId);
    void returnBook(Long borrowerId);
    List<Borrower> getAllBorrowers();
}