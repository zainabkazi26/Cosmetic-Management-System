package com.firstproject.cosmetic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstproject.cosmetic.entity.Makeup;
import com.firstproject.cosmetic.repository.MakeupRepository;

@Service
public class MakeupService {
	@Autowired
	MakeupRepository makeupRepo;
	
	public List<Makeup> getAllMakeup() {
		
		return makeupRepo.findAll();
	}

	public Makeup addMakeup(Makeup m1) {
		return makeupRepo.save(m1);		
	}

	public void deleteById(int midd) {
		makeupRepo.deleteById(midd);;
		
	}

	public Makeup getMakeupsById(int midd) {
		
			return makeupRepo.findById(midd).get();
		}

	}

