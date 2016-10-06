package com.stg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stg.daos.TaskLabelDao;
import com.stg.models.TaskLabel;

@RestController
@RequestMapping(value = "/taskLabels", produces = "application/json")
public class TaskLabels {

    @Autowired
    private TaskLabelDao taskLabelDao;

    /**
     * Retrieves all of the different task labels
     *
     * @return Iterable<TaskLabel>
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public HttpEntity<Iterable<TaskLabel>> getAllTaskLabels() {
	return new ResponseEntity<Iterable<TaskLabel>>(taskLabelDao.findAll(), HttpStatus.OK);
    }

}
