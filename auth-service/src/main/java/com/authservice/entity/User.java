package com.authservice.entity;
 

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data; 
import lombok.NoArgsConstructor; 
 

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="users")
public class User {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private String userName;
	    private String password;

}