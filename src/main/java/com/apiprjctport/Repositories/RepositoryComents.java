package com.apiprjctport.Repositories;

import com.apiprjctport.Entities.Coments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryComents extends JpaRepository<Coments,Long> {





}
