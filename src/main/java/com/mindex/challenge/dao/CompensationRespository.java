package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The data access object contract for {@link Compensation} type.
 */
@Repository
public interface CompensationRespository extends MongoRepository<Compensation, String> {
    @Query(value = "{'employee.employeeId': ?0}")
    Compensation findCompensationByEmployeeId(String employeeId);
}
