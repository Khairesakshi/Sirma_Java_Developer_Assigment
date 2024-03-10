package com.example.demo.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Post;
import com.example.demo.entity.Type;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.payloads.PostDto;

import com.example.demo.repository.PostRepository;
import com.example.demo.repository.TypeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PostService;
@Service
public class PostServiceImpl implements PostService {

	
	@Autowired
	public PostRepository postRepository;
	
	@Autowired
	public ModelMapper modelMapper;
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public TypeRepository typeRepository;
	
	
	
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId,Integer typeId) {
		
		User user=this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		Type type=this.typeRepository.findById(typeId)
				.orElseThrow(()-> new ResourceNotFoundException("Type","Type Id",typeId));
		
		
		
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setTitle(postDto.getTitle());
		post.setImagename("default.png");
		post.setType(type);
		post.setUser(user);
		
		Post newPost=this.postRepository.save(post);
		return this.modelMapper.map(newPost,PostDto.class);
		
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post=this.postRepository.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
		post.setTitle(postDto.getTitle());
		post.setImagename(postDto.getImagename());
		Post updatePost=this.postRepository.save(post);
		return this.modelMapper.map(updatePost,PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post=this.postRepository.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
		this.postRepository.delete(post);
	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post>allPost=this.postRepository.findAll();
		List<PostDto> postDto=allPost.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDto;		
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepository.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
		return this.modelMapper.map(post,PostDto.class);
	}

	@Override
	public List<PostDto> getPostByType(Integer typeId) {
		Type type=this.typeRepository.findById(typeId)
				.orElseThrow(()-> new ResourceNotFoundException("Type","Type Id",typeId));
		
		List<Post>posts=this.postRepository.findByType(type);
		List<PostDto> postDto=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDto;		
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user=this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		List<Post>posts=this.postRepository.findByUser(user);
		List<PostDto> userDto=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return userDto;		
	}

	@Override
	public List<PostDto> serachPost(String keyword) {
		List<Post>posts=this.postRepository.findByTitleContaining(keyword);
		List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;	
	
	}

}
