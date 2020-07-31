package com.nraut.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nraut.excel.helper.ExcelHelper;
import com.nraut.model.UserDetails;
import com.nraut.repository.UserDetailsRepository;

@Service
public class ExcelService {
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	 public void save(MultipartFile file) {
		    try {
		      List<UserDetails> userDetails = ExcelHelper.excelToTutorials(file.getInputStream());
		      userDetailsRepository.saveAll(userDetails);
		    } catch (IOException e) {
		      throw new RuntimeException("fail to store excel data: " + e.getMessage());
		    }
    }
}