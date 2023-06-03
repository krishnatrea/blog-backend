package com.harshit.blog.api.repositories;

import com.harshit.blog.api.entities.Category;
import com.harshit.blog.api.entities.Post;
import com.harshit.blog.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);
}
