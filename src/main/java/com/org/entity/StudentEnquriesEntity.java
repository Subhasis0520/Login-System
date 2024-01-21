package com.org.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Student_Enquries")
public class StudentEnquriesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "Enquiry_Id")
    private Integer enquiryId;
	
	private String name;
	
    private Long phoneNumber;
    
	private String classMode;
	
	private String courseName;
	
	private String enquiryStatus;
	
	@CreationTimestamp
	@Column(updatable = false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(insertable = false)
	private Date lastUpdated;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserDetailsEntity user;
	
}
