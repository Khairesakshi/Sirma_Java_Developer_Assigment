package com.example.demo.service.Impl;

	import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

	import com.example.demo.entity.Type;

	import com.example.demo.exception.ResourceNotFoundException;
	import com.example.demo.payloads.TypeDto;
	import com.example.demo.repository.TypeRepository;
	import com.example.demo.service.TypeService;
	@Service
	public class TypeServiceImpl implements TypeService{
		
		@Autowired
		private TypeRepository typeRepository;
		@Autowired
		private ModelMapper modelMapper;
	
		
		@Override
		public TypeDto createType(TypeDto typeDto) {
			Type type=this.modelMapper.map(typeDto, Type.class);
			Type savedType=this.typeRepository.save(type);
			
			return this.modelMapper.map(savedType, TypeDto.class);
		}
		@Override
		public TypeDto updateType(TypeDto typeDto, Integer typeId) {
			Type type=this.typeRepository.findById(typeId)
					.orElseThrow(()-> new ResourceNotFoundException("Type","Type Id",typeId));
		
			type.setTypename(typeDto.getTypename());
			Type updatedtype =this.typeRepository.save(type);
			
			return this.modelMapper.map(updatedtype, TypeDto.class);
			
			
			
		}
		@Override
		public TypeDto getType(Integer typeId) {
			Type type=this.typeRepository.findById(typeId).orElseThrow(()-> new ResourceNotFoundException("Type","Type id",typeId));
			return this.modelMapper.map(type, TypeDto.class);
		}
		@Override
		public List<TypeDto> getTypes() {
			List<Type>types=this.typeRepository.findAll();
			List<TypeDto>typeDtos=types.stream().map((type)->this.modelMapper.map(type, TypeDto.class)).collect(Collectors.toList());
			return typeDtos;
			
		}
		@Override
		public void deleteType(Integer typeId) {
			Type type=this.typeRepository.findById(typeId)
					.orElseThrow(()-> new ResourceNotFoundException("Type","Type Id",typeId));
			this.typeRepository.delete(type);
		}
	

}
