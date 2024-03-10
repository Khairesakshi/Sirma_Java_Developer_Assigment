package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Post;
import com.example.demo.entity.Type;
import com.example.demo.entity.User;

public interface PostRepository extends JpaRepository<Post, Integer>{

	List<Post>findByUser(User user);
	List<Post>findByType(Type type);

	List<Post>findByTitleContaining(String title);
}
