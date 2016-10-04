package com.stg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stg.daos.PositionDao;
import com.stg.models.Position;

@RestController
@CrossOrigin
@RequestMapping(value = "/positions", produces = "application/json")
public class Positions {

    @Autowired
    private PositionDao positionDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public HttpEntity<Iterable<Position>> getAllPositions() {
	return new ResponseEntity<Iterable<Position>>(positionDao.findAll(),
		HttpStatus.OK);
    }

}
