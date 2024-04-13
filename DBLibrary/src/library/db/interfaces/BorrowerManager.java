package library.db.interfaces;

import java.util.List;

import library.db.pojos.Book;
import library.db.pojos.Borrower;

public interface BorrowerManager {
	
	public void addBorrower(Borrower b);
	public void borrowBook(int borrowerId, int bookId);
	public void returnBook(int borrowerId, int bookId);
	public List<Book> getBorrowedBooks(int borrowerId);
	public Borrower getBorrower(int id);

}
