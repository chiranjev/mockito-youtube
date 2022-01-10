package com.chiranjev.mockito.behavior.verification;

public interface BookRepository {

	void save(Book book);

    Book findBookById(String bookId);
}
