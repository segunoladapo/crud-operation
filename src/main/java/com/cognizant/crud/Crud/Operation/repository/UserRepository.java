package com.cognizant.crud.Crud.Operation.repository;

import com.cognizant.crud.Crud.Operation.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
}
