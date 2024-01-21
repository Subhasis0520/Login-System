package com.org.binding;

import lombok.Data;

@Data
public class UpdateForm {

	private Integer enquiryId;

	private String name;

	private Long phoneNumber;

	private String classMode;

	private String courseName;

	private String enquiryStatus;
}
