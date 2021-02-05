package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mindex.challenge.dao.CompensationRespository;

/**
 * The Service to manage the {@link Compensation} entitites
 */
@Service
public class CompensationServiceImpl implements CompensationService {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRespository compensationRespository;

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Create the {@linkplain Compensation compensation} entity
     * @param compensation {@linkplain Compensation compensation} entity to be created
     * @return Generated {@linkplain Compensation compensation} entity
     */
    @Override
    public Compensation create(Compensation compensation){
        LOG.debug("Creating compensation [{}]", compensation);

        String id = compensation.getEmployee().getEmployeeId();
        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new RuntimeException("Employee not found.");
        }
        compensation.setEmployee(employee);
        compensationRespository.insert(compensation);

        return compensation;
    }

    /**
     * Fetch the {@linkplain Compensation compensation} object for the given id.
     * @param id Employee id for {@linkplain com.mindex.challenge.data.Employee}
     * @return Fetched {@linkplain Compensation compensation} object
     */
    @Override
    public Compensation read(String id){
        LOG.debug("Fetching compensation for id [{}]", id);

        Compensation compensation = compensationRespository.findCompensationByEmployeeId(id);
        if (compensation == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        return compensation;

    }
}
