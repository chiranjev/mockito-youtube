package com.chiranjev.mockito.exception_handling;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    // Mockito is useful to test negative scenarios using when().thenThrow();
    // Here we are configuring Mockito to test our negative scenario by
    // throwing exception on the mock object so that when an exception occurs
    // on the mock object we can test its effect on the CLASS UNDER TEST.
    //  We use when().thenThrow() to throw an exception

    //Case 1 - exception handling with non-void methods.when().thenThrow()  is used in this case
    @Test
    public void testTotalPriceOfBooks_NegativeScenario() throws SQLException {
        when(bookRepository.findAllBooks()).thenThrow(SQLException.class);
        assertThrows(DatabaseReadException.class, () -> bookService.getTotalPriceOfBooks());
    }

    // using new operator with when().thenThrow()
    @Test
    public void testTotalPriceOfBooks_NegativeScenario1() throws SQLException {
        when(bookRepository.findAllBooks()).thenThrow(new SQLException("Database not available"));
        assertThrows(DatabaseReadException.class, () -> bookService.getTotalPriceOfBooks());
    }

    //Case 2 - exception handling with void methods. doThrow().when()  is used in this case
    @Test
    public void testAddBook_NegativeScenario() throws SQLException {
        Book book = new Book(null, "Mockito In Action", 600, LocalDate.now());
        doThrow(SQLException.class).when(bookRepository).save(book);
        assertThrows(DatabaseWriteException.class, () -> bookService.addBook(book));
    }

    // using new operator with doThrow().when()
    @Test
    public void testAddBook_NegativeScenario1() throws SQLException {
        Book book = new Book(null, "Mockito In Action", 600, LocalDate.now());
        doThrow(new SQLException("Database not available")).when(bookRepository).save(book);
        assertThrows(DatabaseWriteException.class, () -> bookService.addBook(book));
    }

    // exception handling in BDDMockito
    @Test
    public void testTotalPriceOfBooks_NegativeScenario_bddMockito() throws SQLException {
//        when(bookRepository.findAllBooks()).thenThrow(SQLException.class);
        given(bookRepository.findAllBooks()).willThrow(SQLException.class);
        assertThrows(DatabaseReadException.class, () -> bookService.getTotalPriceOfBooks());
    }

}
