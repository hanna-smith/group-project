package com.group.kudos.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List; 

import com.group.kudos.models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{
	 List<Role> findAll();
	    
	   List<Role> findByName(String name);
	
}
