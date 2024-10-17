package com.firstproject.cosmetic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firstproject.cosmetic.entity.Makeup;

@Repository
public interface MakeupRepository extends JpaRepository<Makeup, Integer> {

}
