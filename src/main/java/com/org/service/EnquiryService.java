package com.org.service;

import java.util.List;

import com.org.binding.DashboardResponse;
import com.org.binding.EnquiryForm;
import com.org.binding.EnquirySearchCriteria;
import com.org.binding.UpdateForm;
import com.org.entity.StudentEnquriesEntity;

public interface EnquiryService {
	
	public List<String> getCourseName();
	
	public List<String> getEnquiryStatus();

	public DashboardResponse getDashBoard(Integer id);
	
	public boolean addEnquiry(EnquiryForm form);
	
	public List<StudentEnquriesEntity> getEnquires(Integer id, EnquirySearchCriteria criteria); 
	
	public List<StudentEnquriesEntity> getData();
	
	public StudentEnquriesEntity getStudentData(Integer id);
	
	public boolean updateEnquiry(UpdateForm form);
}
