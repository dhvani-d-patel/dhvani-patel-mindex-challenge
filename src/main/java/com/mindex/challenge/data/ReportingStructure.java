package com.mindex.challenge.data;

/**
 * ReportingStructure type consisting two properties: Employee and Number of reports under the employee.
 */
public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }
}
