package com.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.entity.EnquiryStatusEntity;

public interface EnquiryStatusRepo extends JpaRepository<EnquiryStatusEntity, Integer>{

}
