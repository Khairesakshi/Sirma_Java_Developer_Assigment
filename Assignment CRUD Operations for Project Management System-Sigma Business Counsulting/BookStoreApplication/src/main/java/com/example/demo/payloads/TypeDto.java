package com.example.demo.payloads;
	import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

	import lombok.Setter;

	@Getter
	@Setter
	@NoArgsConstructor
	public class TypeDto{
		@NotNull
		private int typeid;
		@NotEmpty
		private String typename;



	}

	



