package com.fusion.thymeleafsecurity.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.fusion.thymeleafsecurity.model.Users;

@Repository
public interface UsersRepository extends PagingAndSortingRepository<Users, Long> {
	
	Users findByUsername(String username);

}
