package org.people.weijuly.bookstore.util;

public interface BookStoreConstants {
    // types
    String QueryType = "Query";
    String MutationType = "Mutation";
    String BookType = "Book";
    String CustomerType = "Customer";
    String AddCustomerResultType = "AddCustomerResult";
    String AddBookResultType = "AddBookResult";
    // queries
    String searchBookByIdQuery = "searchBookById";
    String searchBookByAuthorQuery = "searchBookByAuthor";
    // mutations
    String addBookMutation = "addBook";
    String addCustomerMutation = "addCustomer";
    // arguments
    String customerArg = "customer";
    String bookArg = "book";
    String nameArg = "name";
    // errors
    String BookStoreErrorType = "BookStoreError";
}
