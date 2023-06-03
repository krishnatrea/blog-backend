package com.harshit.blog.api.services;

import com.harshit.blog.api.payloads.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    List<PostDto> getAllPost();

    PostDto getPostById(Integer postId);

    List<PostDto> getAllPostByUser(Integer userId);

    List<PostDto> getAllPostByCategory(Integer categoryId);
}
