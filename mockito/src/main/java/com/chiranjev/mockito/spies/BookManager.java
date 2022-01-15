package com.chiranjev.mockito.spies;

// BookManager - ClassUnderTest  BookService - External dependency

public class BookManager {
    private BookService bookService; // think of BookService as an external dependency to BookManager

    public BookManager(BookService bookService) {
        this.bookService = bookService;
    }

    public int applyDiscountOnBook(String bookId, int discountRate) {
        Book book = bookService.findBook(bookId); // We need to mock
        int discountedPrice = bookService.getAppliedDiscount(book,discountRate); // We need to actually call
        return discountedPrice;
    }


}
