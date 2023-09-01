package com.myblog.blogapp.service.impl;

import com.myblog.blogapp.entities.Comment;
import com.myblog.blogapp.entities.Post;
import com.myblog.blogapp.exception.ResourceNotFoundException;
import com.myblog.blogapp.payload.CommentDto;
import com.myblog.blogapp.repository.CommentRepository;
import com.myblog.blogapp.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{
    private CommentRepository commentRepo;

    private PostRepository postRepo;
    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepo, PostRepository postRepo, ModelMapper mapper) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.mapper = mapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = mapToComment(commentDto);
        comment.setPost(post);
        Comment newComment = commentRepo.save(comment);
        return mapToCommentDto(newComment);
    }

    @Override
    public List<CommentDto> allCommentsOfPostId(long postId) {
        List<Comment> dto = commentRepo.findByPostId(postId);
        return dto.stream().map(q->(mapToCommentDto(q))).collect(Collectors.toList());
    }

    @Override
    public CommentDto updateComment(long postId, long id, CommentDto dto) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        Comment comment = commentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", id));
        comment.setName(dto.getName());
        comment.setEmail(dto.getEmail());
        comment.setBody(dto.getBody());
        Comment upComment = commentRepo.save(comment);
        return mapToCommentDto(upComment);
    }

    @Override
    public void deleteComment(long postId, long id) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        System.out.println(post.getComments());
        Comment comment = commentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", id));
        commentRepo.deleteById(id);
    }

    public Comment mapToComment(CommentDto commentDto){
        Comment comment=mapper.map(commentDto,Comment.class);
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
        return comment;
    }

    public CommentDto mapToCommentDto(Comment comment){
        CommentDto commentDto=mapper.map(comment,CommentDto.class);
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
        return commentDto;
    }
}
