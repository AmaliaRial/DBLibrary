package library.db.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import library.db.interfaces.BookManager;
import library.db.pojos.*;

public class JDBCBookManager implements BookManager {

	//Create a connection
	private Connection c;
	
	//Somebody creates a connection for us
	public JDBCBookManager(Connection c) {
		this.c=c;
	}
	
	@Override
	public void addBook(Book b) {
		// TODO Create addBook
		//Create an statement
		//Define the query
		//Run the statement
		//Close statement
		try {
			String query = "INSERT INTO books (isbn, title, publicationDate, author_id) "
							+ "VALUES (?,?,?,?);";
			PreparedStatement insert = c.prepareStatement(query);
			insert.setInt(1, b.getIsbn());
			insert.setString(2, b.getTitle());
			insert.setDate(3, b.getPublicationDate());
			insert.setInt(4, b.getAuthor().getId());
			insert.executeUpdate();
			insert.close();
		} catch (SQLException sqlE) {
			System.out.println("Error in query");
			sqlE.printStackTrace();
		}
	}



	@Override
	public List<Book> searchBookByTitle(String title) {
		List<Book> books = new ArrayList<Book>();
		try {
			String sql = "SELECT * FROM books WHERE title LIKE ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setString(1, "%" + title + "%");
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer isbn = rs.getInt("isbn");
				String bookTitle = rs.getString("title");
				Date pubDate = rs.getDate("publicationDate");
				Integer authorId = rs.getInt("author_id");
				// TODO get the author from the database
				Book newBook = new Book(isbn, bookTitle, pubDate);
				books.add(newBook);
			}
			return books;
		} catch (SQLException e) {
			System.out.println("Error looking for a book");
			e.printStackTrace();
		}
		return books;	
	}

	@Override
	public void deleteBook(Book b) {
		// TODO deleteBook

	}
	
	@Override
	public Book getBook(int id) {
		// TODO getBook
		return null;
	}

}
