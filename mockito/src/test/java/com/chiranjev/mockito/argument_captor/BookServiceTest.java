package com.chiranjev.mockito.argument_captor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Captor
    private ArgumentCaptor<Book> bookArgumentCaptor;

    @Test
    public void testSaveBook() {
        BookRequest bookRequest = new BookRequest("Mockito In Action", 500, LocalDate.now());
        // https://www.baeldung.com/mockito-argumentcaptor

        // We will use ArgumentCaptor to capture the arguments of our mock method
        // when it is called on the mock object so that we can perform assertions on the captured arguments

        // 1. We add a new ArgumentCaptor field of type Book to store our captured argument:
//        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);

        bookService.addBook(bookRequest);
        // 2. We use Mockito.verify() with the ArgumentCaptor to capture the Book
        verify(bookRepository).save(bookArgumentCaptor.capture());
        // 3. We can then get the captured value and store it as a new Book object
        Book book = bookArgumentCaptor.getValue();
        // 4. Perform assertions on the captured arguments
        assertEquals(bookRequest.getTitle(),book.getTitle());
        assertEquals(bookRequest.getPrice(),book.getPrice());
        assertEquals(bookRequest.getPublishedDate(),book.getPublishedDate());
    }
}
