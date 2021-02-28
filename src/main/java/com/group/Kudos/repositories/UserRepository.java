package com.group.Kudos.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.group.Kudos.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);
	boolean existsByEmail(String email);
}
