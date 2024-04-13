package library.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import library.db.interfaces.AuthorManager;
import library.db.interfaces.BookManager;
import library.db.interfaces.BorrowerManager;
import library.db.pojos.Author;
import library.db.pojos.Book;
import library.db.pojos.Borrower;

public class Methods {
	
	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	private static BookManager bookMan;
	private static AuthorManager authorMan;
	private static BorrowerManager borrowMan;
	
	// Get the book data from the user, create a book object, and call the addBook
	// method of the DB manager
	public static void addBook() throws NumberFormatException, IOException {
		System.out.println("Please, write the book info:");
		System.out.println("ISBN (without dashes):");
		Integer isbn = Integer.parseInt(r.readLine());
		System.out.println("Title:");
		String title = r.readLine();
		System.out.println("Publication date (DD-MM-YYYY format):");
		LocalDate localDate = LocalDate.parse(r.readLine(), formatter);
		Date date = Date.valueOf(localDate);
		// Show and add authors
		listAuthors();
		Integer authorId = Integer.parseInt(r.readLine());
		Author author = authorMan.getAuthor(authorId);
		Book book = new Book(isbn, title, date, author);
		bookMan.addBook(book);
	}
	
	public static void listAuthors() throws IOException {
		System.out.println("Author name (press enter to search all): ");
		String name = r.readLine();
		System.out.println("Author surname (press enter to search all): ");
		String surname = r.readLine();
		System.out.println("These are the available authors, choose one by typing their id:");
		List<Author> authors = authorMan.getAuthorByNameSurname(name, surname);
		System.out.println(authors);
	}
	
	public static void searchBooksByTitle() throws IOException {
		System.out.println("Please, type the book title");
		String title = r.readLine();
		List<Book> books = bookMan.searchBookByTitle(title);
		for (Book book : books) {
			System.out.println(book);
		}
	}
	
	public static void addAuthor() throws NumberFormatException, IOException {
		System.out.println("Please, write the author info:");
		System.out.println("Name:");
		String name = r.readLine();
		System.out.println("Surname:");
		String surname = r.readLine();
		Author author = new Author(name, surname);
		authorMan.addAuthor(author);
	}
	
	public static void addBorrower() throws NumberFormatException, IOException {
		System.out.println("Please, write the borrower info:");
		System.out.println("Name:");
		String name = r.readLine();
		System.out.println("Surname:");
		String surname = r.readLine();
		Borrower b = new Borrower(name, surname);
		borrowMan.addBorrower(b);
	}


}
