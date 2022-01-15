package com.chiranjev.mockito.spies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookManagerTest {


    @Spy
    private BookService bookService;

    @InjectMocks
    private BookManager bookManager;

    @Test
    public void testMockitoSpy() {
//        BookService bookService = Mockito.spy(BookService.class);
//        BookManager bookManager = new BookManager(bookService);

        // We need to mock findBook method because it is communicating to data or currently not implemented
        // We need to call getAppliedDiscount to calculate the discountedPrice

        String bookId = "1234";
        Book book = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        // for spies it is recommended to always use doReturn|Answer|Throw()|CallRealMethod family of methods for stubbing.
        doReturn(book).when(bookService).findBook(bookId);
//        when(bookService.findBook(bookId)).thenReturn(book); // when().thenReturn() will call the actual method in some cases and not return the stubbed value
        int actualDiscount = bookManager.applyDiscountOnBook("1234",10);
        int expectedDiscount = 450;
        assertEquals(expectedDiscount,actualDiscount);
    }
}
