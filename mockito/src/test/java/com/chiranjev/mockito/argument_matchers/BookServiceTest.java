package com.chiranjev.mockito.argument_matchers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

//    @Spy
//    private BookRepository bookRepository;

    @Test
    public void testUpdatePrice() {
        Book book1 = new Book("1234", "Mockito In Action", 600, LocalDate.now());
        Book book2 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
//        when(bookRepository.findBookById(any())).thenReturn(book1);
        when(bookRepository.findBookById(any(String.class))).thenReturn(book1);
//        bookService.updatePrice("xyz",500);
        bookService.updatePrice("1234",500);
        verify(bookRepository).save(book2);
    }

    @Test
    public void testInvalidUseOfArgumentMatchers() {

        // Argument Matchers can't be used outside stubbing/behavior verification

        Book book = new Book("1234", "Mockito In Action", 600, LocalDate.now());
        // Argument Matchers should be provided for all arguments when it is used in atleast one argument during stubbing method
//        when(bookRepository.findBookByTitleAndPublishedDate("Mockito In Action", any())).thenReturn(book);
//        when(bookRepository.findBookByTitleAndPublishedDate(any(), any())).thenReturn(book);
        when(bookRepository.findBookByTitleAndPublishedDate(eq("Mockito In Action"), any())).thenReturn(book);
        Book actualBook = bookService.getBookByTitleAndPublishedDate("Mockito In Action", LocalDate.now());
        assertEquals("Mockito In Action", actualBook.getTitle());
        assertEquals(LocalDate.now(), actualBook.getPublishedDate());
    }

    @Test
    public void testSpecificTypeOfArgumentMatchers() {
        Book book = new Book("1234", "Mockito In Action", 600, LocalDate.now(), true);
        when(bookRepository.findBookByTitleAndPriceAndIsDigital(anyString(), anyInt(), anyBoolean())).thenReturn(book);
        Book actualBook = bookService.getBookByTitleAndPriceAndIsDigital("Mockito In Action", 600, true);
        assertEquals("Mockito In Action", actualBook.getTitle());
        assertEquals(LocalDate.now(), actualBook.getPublishedDate());
        assertEquals(600, actualBook.getPrice());
        assertEquals(true, actualBook.isDigital());
    }


    @Test
    public void testCollectionTypeArgumentMatchers() {
        List<Book> books = new ArrayList();
        Book book1 = new Book("1234", "Mockito In Action", 600, LocalDate.now());
        Book book2 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        books.add(book1);
        books.add(book2);
        bookService.addBooks(books);
        verify(bookRepository).saveAll(anyList());
    }


    @Test
    public void testStringTypeArgumentMatchers() {
        Book book = new Book("1234", "Mockito In Action", 600, LocalDate.now(), true);
        when(bookRepository.findBookByTitleAndPriceAndIsDigital(contains("Action"), anyInt(), anyBoolean())).thenReturn(book);
        Book actualBook = bookService.getBookByTitleAndPriceAndIsDigital("JUnit5 In Action", 600, true);
        assertEquals("Mockito In Action", actualBook.getTitle());
        assertEquals(LocalDate.now(), actualBook.getPublishedDate());
        assertEquals(600, actualBook.getPrice());
        assertEquals(true, actualBook.isDigital());
    }


}
