package com.harshit.blog.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harshit.blog.api.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
