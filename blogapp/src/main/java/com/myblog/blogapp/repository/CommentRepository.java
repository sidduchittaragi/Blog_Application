package com.myblog.blogapp.repository;

import com.myblog.blogapp.entities.Comment;
import com.myblog.blogapp.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByPostId(long postId);
}
