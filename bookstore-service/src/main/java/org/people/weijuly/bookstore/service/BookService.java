package org.people.weijuly.bookstore.service;

import org.people.weijuly.bookstore.data.BookEntity;
import org.people.weijuly.bookstore.data.BookRepository;
import org.people.weijuly.bookstore.model.AuthorModel;
import org.people.weijuly.bookstore.model.BookInModel;
import org.people.weijuly.bookstore.model.BookModel;
import org.people.weijuly.bookstore.model.BooksModel;
import org.people.weijuly.bookstore.model.BooksResultModel;
import org.people.weijuly.bookstore.model.TagModel;
import org.people.weijuly.bookstore.util.BookStoreException;
import org.people.weijuly.bookstore.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private TagsService tagsService;

    @Autowired
    private BookStatsService bookStatsService;

    public BookModel build(String bookId) {
        return bookRepository
                .findById(bookId)
                .map(this::convert)
                .orElseThrow(() -> new BookStoreException(
                        String.format("Book[id:%s] not found", bookId),
                        NOT_FOUND
                ));
    }

    public BooksModel searchBooksByAuthorId(String authorId) {
        BooksModel booksModel = new BooksModel();
        booksModel.setBooks(bookRepository
                .findByAuthorId(authorId)
                .stream()
                .map(this::convert)
                .collect(Collectors.toList()));
        return booksModel;
    }

    public BooksModel searchBooksByAuthorName(String name) {
        BooksModel booksModel = new BooksModel();
        booksModel.setBooks(authorService
                .findByNameContains(name)
                .getAuthors()
                .stream()
                .map(AuthorModel::getId)
                .flatMap(authorId -> bookRepository.findByAuthorId(authorId).stream())
                .map(this::convert)
                .collect(Collectors.toList()));
        return booksModel;
    }

    public BooksModel searchBooksByName(String name) {
        BooksModel booksModel = new BooksModel();
        booksModel.setBooks(bookRepository
                .findByNameContaining(name)
                .stream()
                .map(this::convert)
                .collect(Collectors.toList()));
        return booksModel;
    }

    public BooksModel searchBooksByTag(TagModel tag) {
        BooksModel booksModel = new BooksModel();
        booksModel.setBooks(tagsService
                .searchBooksByTag(tag)
                .stream()
                .map(this::build)
                .collect(Collectors.toList()));
        return booksModel;
    }

    public BooksResultModel searchBooksByYear(Integer year) {
        BooksModel booksModel = new BooksModel();
        booksModel.setBooks(bookRepository
                .findByYear(year)
                .stream()
                .map(this::convert)
                .collect(Collectors.toList()));
        return booksModel;
    }

    public boolean exists(String bookId) {
        return bookRepository.existsById(bookId);
    }

    public Stream<BookModel> allBooks() {
        return StreamSupport
                .stream(bookRepository.findAll().spliterator(), false)
                .map(this::convert);
    }

    private BookModel convert(BookEntity bookEntity) {
        BookModel bookModel = new BookModel();
        bookModel.setId(bookEntity.getId());
        bookModel.setIsbn(bookEntity.getIsbn());
        bookModel.setName(bookEntity.getName());
        bookModel.setYear(bookEntity.getYear());
        bookModel.setPrice(bookEntity.getPrice());
        bookModel.setPages(bookEntity.getPages());
        bookModel.setTags(tagsService.build(bookEntity.getId()));
        bookModel.setUpdatedOn(DateUtil.format(bookEntity.getUpdatedOn()));
        bookModel.setStats(bookStatsService.build(bookEntity.getId()));
        bookModel.setAuthor(authorService.build(bookEntity.getAuthorId()));
        return bookModel;
    }

    public BookModel save(BookInModel bookIn) {
        AuthorModel authorModel = authorService.save(bookIn.getAuthor());
        String bookId = bookRepository
                .findByName(bookIn.getName())
                .map(bookEntity -> updateCopies(bookEntity, bookIn.getCopies()))
                .orElseGet(() -> bookRepository.save(convert(bookIn, authorModel.getId())))
                .getId();
        tagsService.save(bookId, bookIn.getTags());
        return build(bookId);
    }

    private BookEntity convert(BookInModel bookIn, String authorId) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setIsbn(bookIn.getIsbn());
        bookEntity.setName(bookIn.getName());
        bookEntity.setYear(bookIn.getYear());
        bookEntity.setPages(bookIn.getPages());
        bookEntity.setPrice(bookIn.getPrice());
        bookEntity.setCopies(bookIn.getCopies());
        bookEntity.setAuthorId(authorId);
        return bookEntity;
    }

    private BookEntity updateCopies(BookEntity bookEntity, Integer copies) {
        bookEntity.setCopies(bookEntity.getCopies() + copies);
        return bookRepository.save(bookEntity);
    }
}
