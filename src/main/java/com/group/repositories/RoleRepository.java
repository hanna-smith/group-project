
package com.group.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List; 

import com.group.models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{
	 List<Role> findAll();
	    
	   List<Role> findByName(String name);
	
}