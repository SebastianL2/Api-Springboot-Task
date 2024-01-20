package com.apiprjctport.Repositories;

import com.apiprjctport.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryEmployee extends JpaRepository<Employee,Long> {





}
