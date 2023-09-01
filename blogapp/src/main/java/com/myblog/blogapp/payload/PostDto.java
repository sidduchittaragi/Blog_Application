package com.myblog.blogapp.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {
    private long id;
    @NotNull
    @Size(min=2,message="Post title should have at least 2 characters")
    private String title;
    @NotNull
    @Size(min = 10,message = "Post Description should have atleast 10 characters")
    private String description;
    @NotNull
    private String content;
}
