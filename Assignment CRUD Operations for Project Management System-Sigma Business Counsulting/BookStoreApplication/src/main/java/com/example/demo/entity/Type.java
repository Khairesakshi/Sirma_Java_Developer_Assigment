package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
	
	@Entity
	
	@Table(name="type")

	public class Type{

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int typeid;
		
		public int getTypeid() {
			return typeid;
		}

		public void setTypeid(int typeid) {
			this.typeid = typeid;
		}

		public String getTypename() {
			return typename;
		}

		public void setTypename(String typename) {
			this.typename = typename;
		}

		private String typename;
		
			public Type() {
			}
			
		
			@OneToMany(mappedBy="type", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
			@JsonIgnore
			private List<Post> post=new ArrayList<>();
			
			
	}
