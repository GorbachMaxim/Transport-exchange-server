package com.example.transportexchangeserver.repository;

import com.example.transportexchangeserver.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByOffer_id(long offer_id);


    void  deleteAllCommentsByOffer_id(long offer_id);
    List<Comment> findCommentsByOffer_idAndUser_Id(long offer_id, long user_id);
}
