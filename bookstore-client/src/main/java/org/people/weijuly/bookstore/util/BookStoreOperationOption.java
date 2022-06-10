package org.people.weijuly.bookstore.util;


public enum BookStoreOperationOption {
    searchAuthorById("a"),
    searchAuthorsByName("b"),
    searchBookById("c"),
    searchBooksByName("d"),
    searchBooksByTag("e"),
    searchBooksByYear("f"),
    searchBooksByAuthorId("g"),
    searchBooksByAuthorName("h"),
    searchCustomerById("i"),
    searchCustomersByName("j"),
    searchCustomersByBookPurchased("k"),
    searchCustomersByBookLend("l"),
    searchCustomersByBookLiked("m");

    public final String optionCode;

    private BookStoreOperationOption(String optionCode) {
        this.optionCode = optionCode;
    }
}
