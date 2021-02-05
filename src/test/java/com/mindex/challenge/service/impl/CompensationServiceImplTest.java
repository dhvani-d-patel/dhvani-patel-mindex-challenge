package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {
    private String compensationUrl;
    private String compensationIdUrl;
    private String employeeUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compensationUrl = "http://localhost:" + port + "/compensation";
        compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
        employeeUrl = "http://localhost:" + port + "/employee";
    }

    @Test
    public void testCreateRead() {
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");


        // Create employee
        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, testEmployee, Employee.class).getBody();


        // Create Check for Compensation
        Compensation testCompensation = new Compensation();
        testCompensation.setEmployee(createdEmployee);
        testCompensation.setSalary(100000);
        testCompensation.setEffectiveDate(new Date());

        Compensation createdCompensation = restTemplate.postForEntity(compensationUrl, testCompensation, Compensation.class).getBody();
        assertNotNull(createdCompensation.getEmployee().getEmployeeId());
        assertCompensationEquivalence(testCompensation,createdCompensation);


        // Read Check for Compensation
        Compensation readCompensation = restTemplate.getForEntity(compensationIdUrl, Compensation.class,
                createdCompensation.getEmployee().getEmployeeId()).getBody();
        assertCompensationEquivalence(createdCompensation, readCompensation);

    }

    private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
        assertEquals(expected.getEmployee().getEmployeeId(), actual.getEmployee().getEmployeeId());
        assertEquals(expected.getSalary(), actual.getSalary());
    }
}
