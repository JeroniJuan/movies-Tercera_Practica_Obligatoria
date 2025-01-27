package com.esliceu.movies.services;

import com.esliceu.movies.models.Department;
import com.esliceu.movies.repos.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;

    public List<Department> findAll() {
        return departmentRepo.findAll();
    }

    public Department findById(int id) {
        return departmentRepo.findById(id).orElse(null);
    }

    public Department save(Department department) {
        return departmentRepo.save(department);
    }

    public void deleteById(int id) {
        departmentRepo.deleteById(id);
    }
}
