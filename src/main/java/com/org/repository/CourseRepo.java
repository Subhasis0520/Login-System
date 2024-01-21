package com.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.entity.CourseEntity;

public interface CourseRepo extends JpaRepository<CourseEntity, Integer>{

}
