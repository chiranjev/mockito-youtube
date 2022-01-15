package com.chiranjev.mockito.behavior.verification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    private BookService bookService;

//    @Mock
//    private BookRepository bookRepository;

    @Spy
    private BookRepository bookRepository;

    @Test
    public void testAddBook_saveInvoked() {
        Book book = new Book(null, "Mockito In Action", 600, LocalDate.now());
        bookService.addBook(book);
        verify(bookRepository).save(book);
        verify(bookRepository,times(1)).save(book);
        // we are calling verify on our mock object to verify that a certain method was called how many times.
    }

    @Test
    public void testAddBook_saveNotInvoked() {
        Book book = new Book(null, "Mockito In Action", 500, LocalDate.now());
        bookService.addBook(book);
        verify(bookRepository,times(0)).save(book);
        verify(bookRepository,never()).save(book);
    }


    @Test
    public void testSaveBookWithBookRequest_saveMethodNotCalled() {
        BookRequest bookRequest = new BookRequest("Mockito In Action", 500, LocalDate.now());
        Book book = new Book(null, "Mockito In Action", 500, LocalDate.now());
        bookService.addBook(bookRequest);
        verify(bookRepository,times(0)).save(book);
        verify(bookRepository,never()).save(book);
    }

    @Test
    public void testSaveBookWithBookRequest_saveMethodCalled() {
        BookRequest bookRequest = new BookRequest("Mockito In Action", 600, LocalDate.now());
        Book book = new Book(null, "Mockito In Action", 600, LocalDate.now());
        bookService.addBook(bookRequest);
        verify(bookRepository).save(book);
        verify(bookRepository,times(1)).save(book);
    }
    @Test

    public void testSaveBookWithBookRequest_saveMethodCalledTwoTimes() {
        BookRequest bookRequest = new BookRequest("Mockito In Action", 600, LocalDate.now());
        Book book = new Book(null, "Mockito In Action", 600, LocalDate.now());
        bookService.addBook(bookRequest);
        bookService.addBook(bookRequest);
        verify(bookRepository,times(2)).save(book);
    }

    @Test
    public void testUpdatePrice() {
        bookService.updatePrice(null,600);
        verifyNoInteractions(bookRepository); // verify no interaction with Mock object
    }

    @Test
    public void testUpdatePrice_bookPriceAndUpdatedPriceAreSame() {
        Book book = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        when(bookRepository.findBookById("1234")).thenReturn(book);
        bookService.updatePrice("1234",500);
        verify(bookRepository).findBookById("1234");
        verifyNoMoreInteractions(bookRepository); // verify no more interaction with Mock object
    }


    @Test
    public void testUpdatePrice_bookPriceAndUpdatedPriceAreNotSame() {
        Book book = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        when(bookRepository.findBookById("1234")).thenReturn(book);
        bookService.updatePrice("1234",600);

        InOrder inOrder = inOrder(bookRepository); // verify order of interactions
        // inOrder comes in handy where we want to verify that certain method was called
        // after a certain method on the mock object

        inOrder.verify(bookRepository).findBookById("1234");
        inOrder.verify(bookRepository).save(book);
        verifyNoMoreInteractions(bookRepository); // verify no more interaction with Mock object
    }

    @Test
    public void testSaveBookWithBookRequest_saveMethodCalledThreeTimes() {
        BookRequest bookRequest = new BookRequest("Mockito In Action", 600, LocalDate.now());
        Book book = new Book(null, "Mockito In Action", 600, LocalDate.now());
        bookService.addBook(bookRequest);
        bookService.addBook(bookRequest);
        bookService.addBook(bookRequest);
        verify(bookRepository,times(3)).save(book);
        // verify an interaction has occurred at least/at most certain number of times
        verify(bookRepository,atLeast(2)).save(book);
        verify(bookRepository,atLeastOnce()).save(book);
        verify(bookRepository,atMost(4)).save(book);
    }



}
