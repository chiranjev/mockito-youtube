package com.chiranjev.mockito.test_doubles.stub;

import com.chiranjev.mockito.test_doubles.stub.Book;
import com.chiranjev.mockito.test_doubles.stub.BookRepository;
import com.chiranjev.mockito.test_doubles.stub.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

public class StubTest {
    @Test
    public void testStubWithMockito() {
        BookRepository bookRepository = mock(BookRepository.class);
        BookService bookService = new BookService(bookRepository);

        Book book1 = new Book("1234", "Mockito In Action", 250, LocalDate.now());
        Book book2 = new Book("1235", "JUnit 5 In Action", 200, LocalDate.now());

        List<Book> newBooks = new ArrayList();
        newBooks.add(book1);
        newBooks.add(book2);

        when(bookRepository.findNewBooks(anyInt())).thenReturn(newBooks);

        List<Book> newBooksWithAppliedDiscount = bookService.getNewBooksWithAppliedDiscount(10, 5);

        assertEquals(2, newBooksWithAppliedDiscount.size());
        assertEquals(225, newBooksWithAppliedDiscount.get(0).getPrice());
        assertEquals(180, newBooksWithAppliedDiscount.get(1).getPrice());

    }
}
