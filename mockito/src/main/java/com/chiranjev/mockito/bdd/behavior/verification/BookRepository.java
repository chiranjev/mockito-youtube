package com.chiranjev.mockito.bdd.behavior.verification;

public interface BookRepository {

	void save(Book book);

    Book findBookById(String bookId);
}
