package com.chiranjev.mockito.test_doubles.spy;

import com.chiranjev.mockito.test_doubles.spy.Book;
import com.chiranjev.mockito.test_doubles.spy.BookRepository;
import com.chiranjev.mockito.test_doubles.spy.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class SpyTest {
    @Test
    public void testSpyWithMockito() {
        BookRepository bookRepository = spy(BookRepository.class);
        BookService bookService = new BookService(bookRepository);

        Book book1 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        Book book2 = new Book("1235", "JUnit 5 In Action", 400, LocalDate.now());

        bookService.addBook(book1); // return
        bookService.addBook(book2); // save will be called

        /*
        Difference between mock and spy

        Both can be used to mock methods or fields. The difference is that in mock,
        you are creating a complete mock or fake object while in spy,
        there is the real object and you just spying or stubbing specific methods of it.

        While in spy objects, of course, since it is a real method,
        when you are not stubbing the method, then it will call the real method behavior.
        If you want to change and mock the method, then you need to stub it.

         */
        verify(bookRepository,times(0)).save(book1);
        verify(bookRepository,times(1)).save(book2);

    }
}
