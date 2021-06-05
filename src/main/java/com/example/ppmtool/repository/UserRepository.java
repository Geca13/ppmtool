package com.example.ppmtool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ppmtool.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
