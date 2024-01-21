package com.org.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Enquiry_Status")
public class EnquiryStatusEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer statusId;
	
	private String statusName;
}
