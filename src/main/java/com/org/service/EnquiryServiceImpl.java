package com.org.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.binding.DashboardResponse;
import com.org.binding.EnquiryForm;
import com.org.binding.EnquirySearchCriteria;
import com.org.binding.UpdateForm;
import com.org.constant.AppConstant;
import com.org.entity.CourseEntity;
import com.org.entity.EnquiryStatusEntity;
import com.org.entity.StudentEnquriesEntity;
import com.org.entity.UserDetailsEntity;
import com.org.repository.CourseRepo;
import com.org.repository.EnquiryStatusRepo;
import com.org.repository.StudentEnquriesRepo;
import com.org.repository.UserDetailsRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private HttpSession session;

	@Autowired
	private UserDetailsRepo userRepo;

	@Autowired
	private CourseRepo courseRepo;

	@Autowired
	private EnquiryStatusRepo statusRepo;

	@Autowired
	private StudentEnquriesRepo enquriesRepo;

	@Override
	public List<StudentEnquriesEntity> getEnquires(Integer id, EnquirySearchCriteria c) {
		Optional<UserDetailsEntity> findById = userRepo.findById(id);
		if (findById.isPresent()) {
			UserDetailsEntity userDetailsEntity = findById.get();
			List<StudentEnquriesEntity> enquries = userDetailsEntity.getEnquries();
			
			// filter the data 
			if(c.getClassMode()!=null && !AppConstant.EMPTY.equals(c.getClassMode())) {
				enquries = enquries.stream().filter(e -> e.getClassMode()
						           .equals(c.getClassMode())).collect(Collectors.toList());
			}
			if(c.getCourseName()!=null && !AppConstant.EMPTY.equals(c.getCourseName())) {
				enquries = enquries.stream().filter(e -> e.getCourseName()
						           .equals(c.getCourseName())).collect(Collectors.toList());
			}
			if(c.getEnquiryStatus()!=null && !AppConstant.EMPTY.equals(c.getEnquiryStatus())) {
				enquries = enquries.stream().filter(e -> e.getEnquiryStatus()
						           .equals(c.getEnquiryStatus())).collect(Collectors.toList());
			}
				
			return enquries;
		}
		return null;
	}

	@Override
	public List<StudentEnquriesEntity> getData() {
		Integer userId = (Integer) session.getAttribute(AppConstant.USER_ID);
		Optional<UserDetailsEntity> findById = userRepo.findById(userId);
		if (findById.isPresent()) {
			UserDetailsEntity userDetailsEntity = findById.get();
			List<StudentEnquriesEntity> enquries = userDetailsEntity.getEnquries();
			return enquries;
		}
		return null;
	}
	
	@Override
	public StudentEnquriesEntity getStudentData(Integer id) {
		Optional<StudentEnquriesEntity> findById = enquriesRepo.findById(id);
		
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}
	
	@Override
	public boolean addEnquiry(EnquiryForm form) {
		StudentEnquriesEntity enqEntity = new StudentEnquriesEntity();
		BeanUtils.copyProperties(form, enqEntity);

		Integer userId = (Integer) session.getAttribute(AppConstant.USER_ID);
		UserDetailsEntity userEntity = userRepo.findById(userId).get();
          
		enqEntity.setUser(userEntity);

		enquriesRepo.save(enqEntity);	
		
		return true;
	}
	
	@Override
	public boolean updateEnquiry(UpdateForm form) {
		StudentEnquriesEntity enqEntity = new StudentEnquriesEntity();
		BeanUtils.copyProperties(form, enqEntity);

		Integer userId = (Integer) session.getAttribute(AppConstant.USER_ID);
		UserDetailsEntity userEntity = userRepo.findById(userId).get();
          
		enqEntity.setUser(userEntity);

		enquriesRepo.save(enqEntity);	
		
		return true;
	}

	@Override
	public List<String> getCourseName() {
		List<CourseEntity> all = courseRepo.findAll();

		List<String> names = new ArrayList<>();
		for (CourseEntity en : all) {
			names.add(en.getCourseName());
		}
		return names;
	}

	@Override
	public List<String> getEnquiryStatus() {
		List<EnquiryStatusEntity> all = statusRepo.findAll();

		List<String> status = new ArrayList<>();
		for (EnquiryStatusEntity en : all) {
			status.add(en.getStatusName());
		}
		return status;
	}

	@Override
	public DashboardResponse getDashBoard(Integer id) {
		Optional<UserDetailsEntity> optional = userRepo.findById(id);
		DashboardResponse dashboard = new DashboardResponse();
		if (optional.isPresent()) {
			UserDetailsEntity userEntity = optional.get();
			List<StudentEnquriesEntity> enquries = userEntity.getEnquries();
			Integer totalENq = enquries.size();

			Integer enrollEnq = enquries.stream().filter(e -> e.getEnquiryStatus().equalsIgnoreCase(AppConstant.ENROLLED))
					.collect(Collectors.toList()).size();
			Integer lostEnq = enquries.stream().filter(e -> e.getEnquiryStatus().equalsIgnoreCase(AppConstant.LOST))
					.collect(Collectors.toList()).size();

			dashboard.setTotalEnquiryCount(totalENq);
			dashboard.setEnrolledEnquiryCount(enrollEnq);
			dashboard.setLostEnquiryCount(lostEnq);
		}
		return dashboard;
	}

}
