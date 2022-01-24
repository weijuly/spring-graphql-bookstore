package org.people.weijuly.bookstore.converter;

import org.people.weijuly.bookstore.data.CustomerEntity;
import org.people.weijuly.bookstore.data.PurchaseEntity;
import org.people.weijuly.bookstore.model.CustomerInModel;
import org.people.weijuly.bookstore.model.CustomerModel;

import java.util.Collections;
import java.util.List;

public class CustomerConverter {

    public static CustomerEntity convert(CustomerInModel customerIn) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFirstName(customerIn.getFirstName());
        customerEntity.setLastName(customerIn.getLastName());
        return customerEntity;
    }

    public static CustomerModel convert(CustomerEntity customerEntity) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(customerEntity.getId());
        customerModel.setFirstName(customerEntity.getFirstName());
        customerModel.setLastName(customerEntity.getLastName());
        customerModel.setLendings(Collections.emptyList());
        customerModel.setPurchases(Collections.emptyList());
        customerModel.setLikes(Collections.emptyList());
        return customerModel;
    }

    public static CustomerModel convert(CustomerEntity customerEntity, List<PurchaseEntity> purchaseEntities) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(customerEntity.getId());
        customerModel.setFirstName(customerEntity.getFirstName());
        customerModel.setLastName(customerEntity.getLastName());
        customerModel.setLendings(Collections.emptyList());
        customerModel.setPurchases(Collections.emptyList());
        customerModel.setLikes(Collections.emptyList());
        return customerModel;
    }
}
