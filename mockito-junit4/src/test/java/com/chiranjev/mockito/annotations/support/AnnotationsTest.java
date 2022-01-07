package com.chiranjev.mockito.annotations.support;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/*

There is a limitation with JUnit4 that we can use only one runner on our test class.
It could happen that we may need other runners such as runner associated with Spring
and we can't use both runner at same time.
To solve this issue, instead of using @RunWith(MockitoJUnitRunner.class),
 we can use @Before lifecycle method. @Before runs before each test
 Add MockitoAnnotations.initMocks(this); in @Before lifecycle method

 We can also use Mockito Rule to enable annotation support.
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

 */

//@RunWith(MockitoJUnitRunner.class) // needed to let mockito know that we are using annotations
public class AnnotationsTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule(); // @Rule must be public

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService; // this is the class under test

//    @Before
//    public void beforeEach() {
//        MockitoAnnotations.initMocks(this);
//    }


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
