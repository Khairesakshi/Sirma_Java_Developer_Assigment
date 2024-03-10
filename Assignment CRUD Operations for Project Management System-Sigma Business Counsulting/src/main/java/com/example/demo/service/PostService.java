package com.example.demo.service;

import java.util.List;



import com.example.demo.payloads.PostDto;


public interface PostService {

	PostDto createPost(PostDto postdto, Integer userId,Integer typeId);
	PostDto updatePost(PostDto postdto,Integer postId);
	void deletePost(Integer postId);
	List<PostDto>getAllPost();
	PostDto getPostById(Integer postId);
	
	List<PostDto> getPostByType(Integer typeId);
	List<PostDto> getPostByUser(Integer userId);
	List<PostDto> serachPost(String keyword);


}
