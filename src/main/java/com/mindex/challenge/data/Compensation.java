package com.mindex.challenge.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 * Compensation type consists of three fields: Employee, Salary and the effective date for the salary.
 */
public class Compensation {
    private Employee employee;
    private int salary;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-mm-dd-HH:mm")
    private Date effectiveDate;


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
