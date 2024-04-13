package library.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import library.db.interfaces.AuthorManager;
import library.db.interfaces.BookManager;
import library.db.interfaces.BorrowerManager;
import library.db.jdbc.ConnectionManager;
import library.db.jdbc.JDBCAuthorManager;
import library.db.jdbc.JDBCBookManager;
import library.db.jdbc.JDBCBorrowerManager;

public class Menu {

	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

	private static BookManager bookMan;
	private static AuthorManager authorMan;
	private static BorrowerManager borrowMan;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.out.println("Welcome to the library!");
		// Manager setup
		ConnectionManager conMan = new ConnectionManager();
		bookMan = new JDBCBookManager(conMan.getConnection());
		authorMan = new JDBCAuthorManager(conMan.getConnection());
		borrowMan = new JDBCBorrowerManager(conMan.getConnection());
		// TODO delete book (delete by id)
		// TODO change author (change by example)
		// TODO borrow book (n-to-n insertion)
		// TODO return book (n-to-n removal)
		// TODO Go back to the menu after executing an option
		// 		Instead of quitting
		System.out.println("Choose your desired option");
		System.out.println("1. Add a new book");
		System.out.println("2. Search a book by its title");
		System.out.println("3. Add a new author");
		System.out.println("4. Add a new borrower");
		System.out.println("0. Exit");
		int choice = Integer.parseInt(r.readLine());
		switch (choice) {
			case 1: {
				Methods.addBook();
				break;
			}
			case 2: {
				Methods.searchBooksByTitle();
				break;
			}
			case 3: {
				Methods.addAuthor();
				break;
			}
			case 4: {
				Methods.addBorrower();
				break;
			}
			case 0: {
				conMan.close();
				return;
			}
		}		
	}


}
