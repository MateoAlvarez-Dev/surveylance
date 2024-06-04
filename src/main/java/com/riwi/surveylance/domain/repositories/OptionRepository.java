package com.riwi.surveylance.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.surveylance.domain.entities.OptionQuestion;

@Repository
public interface OptionRepository extends JpaRepository<OptionQuestion, Integer>{
    
}
