package com.chiranjev.mockito.spies;

import java.util.List;

public class BookService {


    public Book findBook(String bookId) {
        // code to bring book from database
        throw new RuntimeException("Method not implemented");
//        return null;
    }

    public int getAppliedDiscount(Book book, int discountRate) {
        int price = book.getPrice();
        int newPrice = price-(price*discountRate/100);
        return newPrice;
    }
}
