package com.adminportal.controller;


import com.adminportal.service.BookServiceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class ResourceController {

	@Autowired
	private BookServiceAdmin bookServiceAdmin;
	
	@PostMapping(value="/book/removeList")
	public String removeList(
			@RequestBody ArrayList<String> bookIdList, Model model
			){
		
		for (String id : bookIdList) {
			String bookId =id.substring(8);
			bookServiceAdmin.removeOne(Long.parseLong(bookId));
		}
		
		return "delete success";
	}
}
