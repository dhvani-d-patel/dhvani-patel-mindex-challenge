package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure read(String id){

        LOG.debug("Fetching reporting structure with id [{}]", id);
        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        ReportingStructure reportingStructure = new ReportingStructure();

        reportingStructure.setEmployee(employee);

        Set<String> accountedReports = new HashSet<>();
        reportingStructure.setNumberOfReports(countNumberOfReports(employee, accountedReports));

        return reportingStructure;
    }

    private int countNumberOfReports(Employee employee, Set<String> accountedReports){

        if (accountedReports.contains(employee.getEmployeeId())){
            return -1;
        }

        accountedReports.add(employee.getEmployeeId());

        if (employee.getDirectReports() == null){
            return 0;
        }

        int totalNumberOfReports = 0;

        for (Employee eachReport:employee.getDirectReports()){

            Employee currentEmployee = employeeRepository.findByEmployeeId(eachReport.getEmployeeId());
            if (currentEmployee == null){
                throw new RuntimeException("Invalid employeeId: " + currentEmployee.getEmployeeId());
            }

            totalNumberOfReports += 1 + countNumberOfReports(currentEmployee,accountedReports);

        }
        return totalNumberOfReports;
    }

}
