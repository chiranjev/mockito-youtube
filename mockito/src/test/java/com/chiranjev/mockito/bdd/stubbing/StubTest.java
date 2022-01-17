package com.chiranjev.mockito.bdd.stubbing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StubTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testStubbingInTraditionalMockitoStyle() {

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

    @Test
    @DisplayName("given-- New Books, when - Get New Books With Applied Discount Method is called, Then - New books with applied discount is returned")
    public void test_Given_NewBooks_When_GetNewBooksWithAppliedDiscountIsCalled_Then_NewBooksWithAppliedDiscountIsReturned() {
        // Arrange - Given
        // We initialize our ClassUnderTest and Mocks but since we are using annotations, we don't need to do it here
        Book book1 = new Book("1234", "Mockito In Action", 250, LocalDate.now());
        Book book2 = new Book("1235", "JUnit 5 In Action", 200, LocalDate.now());

        List<Book> newBooks = new ArrayList();
        newBooks.add(book1);
        newBooks.add(book2);

        given(bookRepository.findNewBooks(7)).willReturn(newBooks);

        // Act - When
        // perform the action.
        List<Book> newBooksWithAppliedDiscount = bookService.getNewBooksWithAppliedDiscount(10, 7);

        // Assert - Then
        assertEquals(2, newBooksWithAppliedDiscount.size());
        assertEquals(225, newBooksWithAppliedDiscount.get(0).getPrice());
        assertEquals(180, newBooksWithAppliedDiscount.get(1).getPrice());

        // AssertJ - BDDAssertions
        then(newBooksWithAppliedDiscount).isNotNull();
        then(newBooksWithAppliedDiscount.size()).isEqualTo(2);
        then(newBooksWithAppliedDiscount.get(0).getPrice()).isEqualTo(225);
        then(newBooksWithAppliedDiscount.get(1).getPrice()).isEqualTo(180);
    }
}
