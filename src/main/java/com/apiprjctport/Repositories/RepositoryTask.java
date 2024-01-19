package com.apiprjctport.Repositories;


import com.apiprjctport.Entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryTask extends JpaRepository<Task,Long> {





}
