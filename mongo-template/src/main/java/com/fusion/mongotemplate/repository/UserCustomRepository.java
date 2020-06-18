package com.fusion.mongotemplate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fusion.mongotemplate.entity.User;

public interface UserCustomRepository {

    Page<User> findUsers(Pageable pageable);
}
