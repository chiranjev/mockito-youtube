package com.chiranjev.mockito.exception_handling;


import java.sql.SQLException;
import java.util.List;

public class BookService {
	
	private BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public int getTotalPriceOfBooks() {
		List<Book> books = null;
		try {
			books = bookRepository.findAllBooks();
		} catch (SQLException e) {
			// log exception
			System.out.println("[Error] exception is "+e.getMessage());
			// throw back customized exception
			throw new DatabaseReadException("Unable to read from database due to - " + e.getMessage());
		}
		int totalPrice = 0;
		for(Book book : books) {
			totalPrice+=book.getPrice();
		}
		return totalPrice;
	}

	public void addBook(Book book) {
		try {
			bookRepository.save(book);
		} catch (SQLException e) {
			// log the exception
			System.out.println("[Error] exception is "+e.getMessage());
			// throw back customized exception
			throw new DatabaseWriteException("Unable to write in database due to - " + e.getMessage());
		}
	}
}
