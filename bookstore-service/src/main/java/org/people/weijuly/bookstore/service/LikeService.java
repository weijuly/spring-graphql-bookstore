package org.people.weijuly.bookstore.service;

import org.people.weijuly.bookstore.data.LikeEntity;
import org.people.weijuly.bookstore.data.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeService {

    @Autowired
    LikeRepository likeRepository;

    public List<String> likedBooks(String customerId) {
        return likeRepository
                .findByCustomerId(customerId)
                .stream()
                .map(LikeEntity::getBookId)
                .collect(Collectors.toList());
    }

    public void likeBook(String customerId, String bookId) {
        likeRepository
                .findByCustomerIdAndBookId(customerId, bookId)
                .orElseGet(() -> likeRepository.save(likeEntity(customerId, bookId)));
    }

    private LikeEntity likeEntity(String customerId, String bookId) {
        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setCustomerId(customerId);
        likeEntity.setBookId(bookId);
        return likeEntity;
    }


    public List<String> searchCustomersByBookLiked(String bookId) {
        return likeRepository
                .findByBookId(bookId)
                .stream()
                .map(LikeEntity::getCustomerId)
                .distinct()
                .collect(Collectors.toList());
    }

    public Integer getLikeCount(String bookId) {
        return likeRepository
                .findByBookId(bookId)
                .size();
    }
}
