package org.people.weijuly.bookstore.converter;

import org.people.weijuly.bookstore.data.BookEntity;
import org.people.weijuly.bookstore.data.PurchaseEntity;
import org.people.weijuly.bookstore.model.PurchaseModel;

public class PurchaseConverter {

    public static PurchaseModel convert(PurchaseEntity purchaseEntity, BookEntity bookEntity) {
        PurchaseModel purchaseModel = new PurchaseModel();
        purchaseModel.setId(purchaseEntity.getId());
        purchaseModel.setBook(null);
        purchaseModel.setPurchasedOn(purchaseEntity.getPurchasedOn().toString());
        return purchaseModel;
    }
}
