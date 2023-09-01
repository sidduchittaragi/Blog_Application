package com.myblog.blogapp.service.impl;

import com.myblog.blogapp.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> allCommentsOfPostId(long postId);

    CommentDto updateComment(long postId, long id, CommentDto dto);

    void deleteComment(long postId, long id);
}
