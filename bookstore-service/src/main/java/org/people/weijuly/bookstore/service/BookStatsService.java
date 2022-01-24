package org.people.weijuly.bookstore.service;

import org.people.weijuly.bookstore.data.BookEntity;
import org.people.weijuly.bookstore.data.BookRepository;
import org.people.weijuly.bookstore.data.LendingRepository;
import org.people.weijuly.bookstore.data.LikeRepository;
import org.people.weijuly.bookstore.data.PurchaseRepository;
import org.people.weijuly.bookstore.model.BookStatsModel;
import org.people.weijuly.bookstore.util.LendingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookStatsService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    LendingRepository lendingRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    public BookStatsModel build(String bookId) {
        BookStatsModel bookStatsModel = new BookStatsModel();
        bookStatsModel.setCopies(bookRepository
                .findById(bookId)
                .map(BookEntity::getCopies)
                .orElse(0));
        bookStatsModel.setPurchases(purchaseRepository
                .findByBookId(bookId)
                .size());
        bookStatsModel.setLikes(likeRepository
                .findByBookId(bookId)
                .size());
        bookStatsModel.setLends(lendingRepository
                .findByBookIdAndStatus(bookId, LendingStatus.ACTIVE)
                .size());
        return bookStatsModel;
    }

}
