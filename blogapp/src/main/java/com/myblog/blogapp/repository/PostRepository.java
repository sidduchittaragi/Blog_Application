package com.myblog.blogapp.repository;


import com.myblog.blogapp.entities.Post;
import com.myblog.blogapp.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>{
}
