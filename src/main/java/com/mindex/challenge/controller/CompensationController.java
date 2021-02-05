package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The RESTful service for {@link Compensation} type.
 */
@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;

    /**
     * Create a new {@linkplain Compensation compensation} entity and save it to the database.
     * @param compensation {@linkplain Compensation compensation} to be created.
     * @return Generated {@linkplain Compensation compensation} entity.
     */
    @PostMapping("/compensation")
    public Compensation create(@RequestBody Compensation compensation){
        LOG.debug("Received compensation create request for [{}]", compensation);

        return compensationService.create(compensation);
    }

    /**
     * Fetch the {@linkplain Compensation compensation} object for the given id.
     * @param id Employee id of the {@linkplain com.mindex.challenge.data.Employee employee}
     * @return The fetched {@linkplain Compensation compensation}
     */
    @GetMapping("/compensation/{id}")
    public Compensation read(@PathVariable String id){
        LOG.debug("Received compensation read request for id [{}]", id);

        return compensationService.read(id);
    }
}
