package com.impetus.userregistration.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.impetus.userregistration.domain.User;

import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findUserByEmail(String name);
}
