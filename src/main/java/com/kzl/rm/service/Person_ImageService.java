package com.kzl.rm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kzl.rm.bean.Person_Image;
import com.kzl.rm.dao.Person_ImageMapper;

@Service
public class Person_ImageService  {
	
	@Autowired
	private Person_ImageMapper person_ImageMapper;

	public Person_Image findImageByName(String fileName) {
		Person_Image image = person_ImageMapper.findImageByName(fileName);
		return image;
	}

	public int saveImage(Person_Image image) {
		int count = person_ImageMapper.insertSelective(image);
		return count;
	}

	public Person_Image findImageById(Long personalImageId) {
		Person_Image image = person_ImageMapper.selectByPrimaryKey(personalImageId);
		return image;
	}
	
	  
	
}
