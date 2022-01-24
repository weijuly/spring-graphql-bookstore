package org.people.weijuly.bookstore.util;

public interface BookStoreConstants {

    // graphql types
    String QueryType = "Query";
    String MutationType = "Mutation";

    // application types
    String AuthorResultType = "AuthorResult";
    String AuthorsResultType = "AuthorsResult";
    String AuthorType = "Author";
    String AuthorsType = "Authors";
    String BookResultType = "BookResult";
    String BooksResultType = "BooksResult";
    String BookType = "Book";
    String BooksType = "Books";
    String CustomerResultType = "CustomerResult";
    String CustomersResultType = "CustomersResult";
    String CustomerType = "Customer";
    String CustomersType = "Customers";

    // queries
    String searchAuthorByIdQuery = "searchAuthorById";
    String searchAuthorsByNameQuery = "searchAuthorsByName";
    String searchBookByIdQuery = "searchBookById";
    String searchBooksByAuthorIdQuery = "searchBooksByAuthorId";
    String searchBooksByAuthorNameQuery = "searchBooksByAuthorName";
    String searchBooksByNameQuery = "searchBooksByName";
    String searchBooksByTagQuery = "searchBooksByTag";
    String searchBooksByYearQuery = "searchBooksByYear";
    String searchCustomerByIdQuery = "searchCustomerById";
    String searchCustomersByBookLendQuery = "searchCustomersByBookLend";
    String searchCustomersByBookLikedQuery = "searchCustomersByBookLiked";
    String searchCustomersByBookPurchasedQuery = "searchCustomersByBookPurchased";
    String searchCustomersByNameQuery = "searchCustomersByName";

    // mutations
    String addBookMutation = "addBook";
    String addCustomerMutation = "addCustomer";
    String purchaseBooksMutation = "purchaseBooks";
    String lendBooksMutation = "lendBooks";
    String returnBooksMutation = "returnBooks";
    String likeBookMutation = "likeBook";

    // arguments
    String customerArg = "customer";
    String bookArg = "book";
    String nameArg = "name";
    String cartArg = "cart";
    String likeArg = "like";
    String bookIdArg = "bookId";
    String authorIdArg = "authorId";
    String tagArg = "tag";
    String yearArg = "year";
    String customerIdArg = "customerId";

    // errors
    String BookStoreErrorType = "BookStoreError";
}
