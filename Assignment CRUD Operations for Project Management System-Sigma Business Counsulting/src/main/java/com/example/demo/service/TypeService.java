package com.example.demo.service;



	

	import java.util.List;


	import com.example.demo.payloads.TypeDto;


	public interface TypeService	 {

		TypeDto createType(TypeDto typedto);
		TypeDto updateType(TypeDto typedto,Integer typeId);
		TypeDto getType(Integer typeId);
		List<TypeDto>getTypes();
		void deleteType(Integer typeId);


	}

	


