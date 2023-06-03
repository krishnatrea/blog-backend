package com.harshit.blog.api.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostDto {
    private Integer id;
    private String title;
    private String contents;
    private String image;
    private String timestamp;
    private CategoryDto category;
    private UserDto user;
}
