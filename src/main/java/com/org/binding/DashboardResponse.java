package com.org.binding;

import lombok.Data;

@Data
public class DashboardResponse {

	private Integer totalEnquiryCount;
	
	private Integer enrolledEnquiryCount;
	
	private Integer lostEnquiryCount;
}
