package com.harshit.blog.api.services.impl;

import com.harshit.blog.api.entities.Category;
import com.harshit.blog.api.entities.Post;
import com.harshit.blog.api.entities.User;
import com.harshit.blog.api.exceptions.ResourceNotFoundException;
import com.harshit.blog.api.payloads.PostDto;
import com.harshit.blog.api.repositories.CategoryRepository;
import com.harshit.blog.api.repositories.PostRepository;
import com.harshit.blog.api.repositories.UserRepository;
import com.harshit.blog.api.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto postDto, Integer categoryId, Integer userId) {
        Post post = this.modelMapper.map(postDto, Post.class);
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category is not found ", "id", categoryId.toString()));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User is not found ", "id", userId.toString()));
        post.setTimestamp(new Date());
        post.setImage("default.png");
        post.setUser(user);
        post.setCategory(category);
        Post savedPost = postRepository.save(post);
        return this.modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post newPost = this.modelMapper.map(postDto, Post.class);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post is not found ", "id", postId.toString()));
        post.setTitle(newPost.getTitle());
        post.setContents(newPost.getContents());
        post.setImage(newPost.getImage());
        Post updatedPost = postRepository.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post is not found ", "id", postId.toString()));
        postRepository.delete(post);
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts = this.postRepository.findAll();
        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post is not found ", "id", postId.toString()));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getAllPostByUser(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User is not found ", "id", userId.toString()));
        List<Post> posts = this.postRepository.findByUser(user);
        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getAllPostByCategory(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category is not found ", "id", categoryId.toString()));
        List<Post> posts = this.postRepository.findByCategory(category);
        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
