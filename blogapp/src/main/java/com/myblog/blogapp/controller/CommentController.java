package com.myblog.blogapp.controller;

import com.myblog.blogapp.payload.CommentDto;
import com.myblog.blogapp.service.impl.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId")long postId, @RequestBody CommentDto commentDto){
        CommentDto comment = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> findAllCommentsOfId(@PathVariable("postId")long postId){
        List<CommentDto> dto = commentService.allCommentsOfPostId(postId);
        return dto;
    }

    @RequestMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("postId")long postId, @PathVariable("id")long id, @RequestBody CommentDto dto){
        CommentDto commentDto = commentService.updateComment(postId, id, dto);
        return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("postId")long postId, @PathVariable("id")long id){
        commentService.deleteComment(postId,id);
        return new ResponseEntity<>("Comment Deleted",HttpStatus.OK);
    }
}
