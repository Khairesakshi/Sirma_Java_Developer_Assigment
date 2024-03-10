package com.example.demo.controller;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.payloads.ApiResponse;
import com.example.demo.payloads.PostDto;
import com.example.demo.service.FileService;
import com.example.demo.service.PostService;





@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/user/{userId}/type/{typeId}/post")
	public ResponseEntity<PostDto>createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer typeId)
	{
		PostDto createPost=this.postService.createPost(postDto,userId, typeId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
}

	//get by user
	@GetMapping("/user/{userId}/post")
	public ResponseEntity<List<PostDto>>getPostByUser(@PathVariable Integer userId){
		List<PostDto> post=this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(post,HttpStatus.OK);
	}

	//get by type
	@GetMapping("/type/{typeId}/post")
	public ResponseEntity<List<PostDto>>getPostByType(@PathVariable Integer typeId){
		List<PostDto> post=this.postService.getPostByUser(typeId);
		return new ResponseEntity<List<PostDto>>(post,HttpStatus.OK);
	}
	//get all post
	@GetMapping("/post")
	public ResponseEntity<List<PostDto>> getAllPost(){
		List<PostDto>allPost=this.postService.getAllPost();
		return new ResponseEntity<List<PostDto>>(allPost,HttpStatus.OK);
	}

	//get post by id
	@GetMapping("post/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		PostDto postDto=this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
}
	//delete 
	@DeleteMapping("post/{postId}")
	public ApiResponse deletePost(@PathVariable("typeId") Integer postid){
	this.postService.deletePost(postid);
	return new ApiResponse("Post deleted successfully",true);
}
	@PutMapping("post/{postId}")
	public ResponseEntity<PostDto>updateType(@RequestBody PostDto postDto, @PathVariable Integer postid){
		PostDto updatePost=this.postService.updatePost(postDto, postid);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	//search
	@GetMapping("post/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword") String keyword){
		List<PostDto>result=this.postService.serachPost(keyword);
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);

}
	//post image upload
	@PostMapping("post/image/upload/{postId}")
	public ResponseEntity<PostDto>uploadPostImage(@RequestParam("image")MultipartFile image,
			@PathVariable Integer postId) throws IOException{
		PostDto postDto=this.postService.getPostById(postId);
	String fileName=this.fileService.uploadImage(path, image);
	
	postDto.setImagename(fileName);
	PostDto updatePost=this.postService.updatePost(postDto, postId);
	return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	
}
}