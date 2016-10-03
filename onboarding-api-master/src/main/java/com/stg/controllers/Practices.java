package com.stg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stg.daos.PracticeDao;
import com.stg.models.Practice;

@RestController
@RequestMapping(value = "/practices", produces = "application/json")
public class Practices {

    @Autowired
    private PracticeDao practiceDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public HttpEntity<Iterable<Practice>> getAllPractices() {
	return new ResponseEntity<Iterable<Practice>>(practiceDao.findAll(),
		HttpStatus.OK);
    }

}
