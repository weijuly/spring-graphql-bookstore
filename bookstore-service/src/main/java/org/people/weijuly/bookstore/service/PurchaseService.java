package org.people.weijuly.bookstore.service;

import org.people.weijuly.bookstore.data.PurchaseEntity;
import org.people.weijuly.bookstore.data.PurchaseRepository;
import org.people.weijuly.bookstore.model.PurchaseModel;
import org.people.weijuly.bookstore.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    BookService bookService;

    public List<PurchaseModel> build(String customerId) {
        return purchaseRepository
                .findByCustomerId(customerId)
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public boolean didPurchase(String customerId, String bookId) {
        return !purchaseRepository
                .findByCustomerIdAndBookId(customerId, bookId)
                .isEmpty();
    }

    private PurchaseModel convert(PurchaseEntity purchaseEntity) {
        PurchaseModel purchaseModel = new PurchaseModel();
        purchaseModel.setId(purchaseEntity.getId());
        purchaseModel.setBook(bookService.build(purchaseEntity.getBookId()));
        purchaseModel.setPurchasedOn(DateUtil.format(purchaseEntity.getPurchasedOn()));
        return purchaseModel;
    }

    public List<String> searchCustomersByBookPurchased(String bookId) {
        return purchaseRepository
                .findByBookId(bookId)
                .stream()
                .map(PurchaseEntity::getCustomerId)
                .distinct()
                .collect(Collectors.toList());
    }

    public Integer getPurchaseCount(String bookId) {
        return purchaseRepository
                .findByBookId(bookId)
                .size();
    }
}
