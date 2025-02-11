package com.esliceu.movies.repos;

import com.esliceu.movies.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.SequencedCollection;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {
    List<Department> findByDepartmentNameContainingIgnoreCase(String departmentName);
}
