package org.people.weijuly.bookstore.service;

import org.people.weijuly.bookstore.data.LendingEntity;
import org.people.weijuly.bookstore.data.LendingRepository;
import org.people.weijuly.bookstore.model.LendingModel;
import org.people.weijuly.bookstore.util.DateUtil;
import org.people.weijuly.bookstore.util.LendingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LendingService {

    @Autowired
    private LendingRepository lendingRepository;

    @Autowired
    private BookService bookService;

    public List<LendingModel> build(String customerId) {
        return lendingRepository
                .findByCustomerId(customerId)
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public boolean didLend(String customerId, String bookId) {
        return !lendingRepository
                .findByCustomerIdAndBookId(customerId, bookId)
                .isEmpty();
    }

    private LendingModel convert(LendingEntity lendingEntity) {
        LendingModel lendingModel = new LendingModel();
        lendingModel.setId(lendingEntity.getId());
        lendingModel.setBook(bookService.build(lendingEntity.getBookId()));
        lendingModel.setDueOn(DateUtil.format(lendingEntity.getDueOn()));
        return lendingModel;
    }

    public List<String> searchCustomersByBookLend(String bookId) {
        return lendingRepository
                .findByBookIdAndStatus(bookId, LendingStatus.ACTIVE)
                .stream()
                .map(LendingEntity::getCustomerId)
                .distinct()
                .collect(Collectors.toList());
    }

    public Integer getLendingCount(String bookId) {
        return lendingRepository
                .findByBookIdAndStatus(bookId, LendingStatus.ACTIVE)
                .size();
    }
}
