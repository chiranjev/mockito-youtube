package com.chiranjev.mockito.annotations.support;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // needed to let mockito know that we are using annotations
public class AnnotationsTest {

    @Mock
    private BookRepository bookRepository; // mock object

    @InjectMocks
    private BookService bookService; // this is the class under test

    @Test
    public void testCreateMocksUsingAnnotations() {
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
