package com.dalc.one.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dalc.one.domain.CoursePlace;

public interface CoursePlaceRepository extends CrudRepository<CoursePlace, Long>{
	List<CoursePlace> findByCourseIdOrderByPriority(int cid);
	int deleteByCourseIdAndPlaceId(int cid, int pid);
	int deleteByCourseId(int cid);
}
