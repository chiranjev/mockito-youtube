package com.chiranjev.mockito.stubbing;

import java.util.List;

public interface BookRepository {

	List<Book> findNewBooks(int days);
	Book findBookByBookId(String bookId);
	void save(Book book);
}
