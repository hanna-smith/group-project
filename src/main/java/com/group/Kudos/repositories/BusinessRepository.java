package com.group.Kudos.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.group.Kudos.models.Business;

@Repository
public interface BusinessRepository extends CrudRepository<Business, Long> {
	List<Business> findAll();
	Business findByBingId(String bingId);
}
