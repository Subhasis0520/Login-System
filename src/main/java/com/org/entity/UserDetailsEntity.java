package com.org.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "User_Details")
@Data
public class UserDetailsEntity {

	 @Id
	 @Column(name ="User_Id")	
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer userId;
	 
	 @Column(name ="User_Name")
	 private String userName;
	 
	 private String email;
	 
	 private Long phone;

	 private String password;
	 
	 @Column(name = "Account_Status")
	 private String accoutStatus;
	 
	 @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	 private List<StudentEnquriesEntity> enquries;
}
