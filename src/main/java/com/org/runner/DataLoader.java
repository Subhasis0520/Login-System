package com.org.runner;

//import java.util.Arrays;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.org.entity.CourseEntity;
import com.org.entity.EnquiryStatusEntity;
//import com.org.repository.CourseRepo;
//import com.org.repository.EnquiryStatusRepo;

@Component
public class DataLoader implements ApplicationRunner{

	/*@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private EnquiryStatusRepo statusRepo;
	*/
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
//		courseRepo.deleteAll();
		CourseEntity c1 = new CourseEntity();
		c1.setCourseName("Java");
		CourseEntity c2 = new CourseEntity();
		c2.setCourseName("AWS");
		CourseEntity c3 = new CourseEntity();
		c3.setCourseName("Python");
		CourseEntity c4 = new CourseEntity();
		c4.setCourseName("MERN");
//		courseRepo.saveAll(Arrays.asList(c1,c2,c3,c4));
		
//		statusRepo.deleteAll();
		EnquiryStatusEntity s1 = new EnquiryStatusEntity();
		s1.setStatusName("NEW");
		EnquiryStatusEntity s2 = new EnquiryStatusEntity();
		s2.setStatusName("ENROLLED");
		EnquiryStatusEntity s3 = new EnquiryStatusEntity();
		s3.setStatusName("LOST");
//		statusRepo.saveAll(Arrays.asList(s1,s2,s3));
		
	}

}
