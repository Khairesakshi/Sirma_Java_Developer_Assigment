package com.example.demo.controller;




	import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

	import com.example.demo.payloads.ApiResponse;
	import com.example.demo.payloads.TypeDto;

	import com.example.demo.service.TypeService;

	import jakarta.validation.Valid;
	@RestController
	@RequestMapping("/api/type")
	
	public class TypeController {
		@Autowired
		private TypeService typeService;
		
		
		@PostMapping("/")
		public ResponseEntity<TypeDto>createType(@Valid @RequestBody TypeDto typeDto){
			TypeDto createTypeDto=this.typeService.createType(typeDto);
			return new ResponseEntity<>(createTypeDto,HttpStatus.CREATED);

	}
		@PutMapping("/{typeId}")
		public ResponseEntity<TypeDto>updateType(@Valid @RequestBody TypeDto typeDto, @PathVariable("typeId") Integer tid){
			TypeDto updateType=this.typeService.updateType(typeDto, tid);
			return ResponseEntity.ok(updateType);
		}
		
		@DeleteMapping("/{typeId}")
		public ResponseEntity<?>deleteType(@PathVariable("typeId") Integer tid){
		this.typeService.deleteType(tid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Type deleted successfully",true),HttpStatus.OK);
	}
		
		@GetMapping("/")
		public ResponseEntity<List<TypeDto>> getTypes(){
			return ResponseEntity.ok(this.typeService.getTypes());
		}
		
		@GetMapping("/{typeId}")
		public ResponseEntity<TypeDto> getSingleType(@PathVariable Integer typeId){
			return ResponseEntity.ok(this.typeService.getType(typeId));
	}
		
		}



