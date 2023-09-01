package com.myblog.blogapp.service.impl;

import com.myblog.blogapp.payload.PostDto;
import com.myblog.blogapp.payload.PostResponse;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);

    PostDto getPostById(long id);


    PostDto updatePost(PostDto postDto, Long id);

    void deletePost(Long id);
}
