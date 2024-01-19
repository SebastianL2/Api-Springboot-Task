package com.apiprjctport.Repositories;
import com.apiprjctport.Entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryProject extends JpaRepository<Project,Long> {





}
