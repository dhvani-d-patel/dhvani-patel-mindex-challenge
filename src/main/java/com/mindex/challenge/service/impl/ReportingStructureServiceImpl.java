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

/**
 * The Service to manage {@link ReportingStructure} entities.
 */
@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Fetch the {@linkplain ReportingStructure reportingStructure} object with given id.
     * @param id Employee id of the {@linkplain com.mindex.challenge.data.Employee}
     * @return Fetched {@linkplain ReportingStructure reportingStructure} object
     */
    @Override
    public ReportingStructure read(String id){
        LOG.debug("Fetching reporting structure with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        ReportingStructure reportingStructure = new ReportingStructure();

        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(countNumberOfReports(employee, new HashSet<String>()));

        return reportingStructure;
    }

    /**
     * The recursive method to calculate the number of reports for an employee. It is a total of direct reports for the
     * employee and all of their distinct reports. If there are not direct reports for the employee it returns 0.
     * @param employee {@linkplain Employee employee} entity to calculate the number of reports.
     * @param accountedReports A hashset to keep track of already accounted reports to avoid duplicate counts.
     * @return the int value of total number of reports
     */
    private int countNumberOfReports(Employee employee, Set<String> accountedReports){
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
            if (accountedReports.contains(currentEmployee.getEmployeeId())){
                continue;
            }
            totalNumberOfReports += 1 + countNumberOfReports(currentEmployee,accountedReports);
        }

        return totalNumberOfReports;
    }

}
