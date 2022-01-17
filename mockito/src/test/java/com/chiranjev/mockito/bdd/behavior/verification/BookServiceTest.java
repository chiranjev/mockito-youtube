package com.chiranjev.mockito.bdd.behavior.verification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
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
        Book book = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        when(bookRepository.findBookById("1234")).thenReturn(book);
        bookService.updatePrice("1234",600);
        verify(bookRepository).findBookById("1234");
        verify(bookRepository).save(book);
        verifyNoMoreInteractions(bookRepository); // verify no more interaction with Mock object
    }

    @Test
    public void test_Given_ABook_When_UpdatedPriceIsCalledWithNewPrice_Then_BookPriceIsUpdated() {
        // Given - Arrange
        Book book = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        given(bookRepository.findBookById("1234")).willReturn(book);
        // When - Act
        bookService.updatePrice("1234",600);

        // Then - Assert/Verify
        then(bookRepository).should().findBookById("1234");
        then(bookRepository).should().save(book);
        then(bookRepository).shouldHaveNoMoreInteractions();
    }
}
