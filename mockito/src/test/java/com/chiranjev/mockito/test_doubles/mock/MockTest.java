package com.chiranjev.mockito.test_doubles.mock;

import com.chiranjev.mockito.test_doubles.mock.Book;
import com.chiranjev.mockito.test_doubles.mock.BookRepository;
import com.chiranjev.mockito.test_doubles.mock.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class MockTest {
    @Test
    public void testMockWithMockito() {
        BookRepository bookRepository = mock(BookRepository.class);
        BookService bookService = new BookService(bookRepository);

        Book book1 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        Book book2 = new Book("1235", "JUnit 5 In Action", 400, LocalDate.now());

        bookService.addBook(book1); // return
        bookService.addBook(book2); // save will be called

        // Mockito can ensure whether a mock method is being called with required
        // arguments or not. It is done using the verify() method.
        verify(bookRepository,times(0)).save(book1);
        verify(bookRepository,times(1)).save(book2);

    }
}
