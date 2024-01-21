package com.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.entity.StudentEnquriesEntity;

public interface StudentEnquriesRepo extends JpaRepository<StudentEnquriesEntity, Integer>{

}
