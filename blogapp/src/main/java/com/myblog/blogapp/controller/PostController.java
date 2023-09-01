package com.myblog.blogapp.controller;

import com.myblog.blogapp.payload.PostDto;
import com.myblog.blogapp.payload.PostResponse;
import com.myblog.blogapp.service.impl.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Object> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value="pageNo",defaultValue="AppConstants.DEFAULT_PAGE_NUMBER",required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "AppConstants.DEFAULT_PAGE_SIZE",required = false) int pageSize,
            @RequestParam(value="sortBy",defaultValue="AppConstants.DEFAULT_SORT_BY",required=false)String sortBy,
            @RequestParam(value="sortDir",defaultValue="AppConstants.DEFAULT_SORT_DIR",required=false)String sortDir
    ){
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
       return ResponseEntity.ok(postService.getPostById(id)) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("id")Long id){
        return new ResponseEntity<>(postService.updatePost(postDto,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id")Long id){
        postService.deletePost(id);
        return new ResponseEntity<>("PostEntity deleted Sucessfully",HttpStatus.OK);
    }
}

