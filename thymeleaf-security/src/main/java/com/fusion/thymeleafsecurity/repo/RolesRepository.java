package com.fusion.thymeleafsecurity.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.fusion.thymeleafsecurity.model.RoleName;
import com.fusion.thymeleafsecurity.model.Roles;

@Repository
public interface RolesRepository extends PagingAndSortingRepository<Roles, Long> {

	Roles findByName(RoleName role);

}
