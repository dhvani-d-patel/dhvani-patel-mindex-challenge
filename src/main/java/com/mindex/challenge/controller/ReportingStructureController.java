package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The RESTful service for {@link ReportingStructure} type.
 */
@RestController
public class ReportingStructureController {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private ReportingStructureService reportingStructureService;

    /**
     * Fetch the {@linkplain ReportingStructure reportingStructure} object for the given id.
     * @param id Employee id of {@linkplain ReportingStructure reportingStructure}
     * @return Fetched {@linkplain ReportingStructure reportingStructure} object
     */
    @GetMapping("/reporting-structure/{id}")
    public ReportingStructure read(@PathVariable String id){
        LOG.debug("Received reporting structure read request for id [{}]",id);

        return reportingStructureService.read(id);
    }

}
