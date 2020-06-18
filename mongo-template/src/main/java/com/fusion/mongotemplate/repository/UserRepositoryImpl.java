package com.fusion.mongotemplate.repository;

import com.fusion.mongotemplate.entity.User;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserCustomRepository {

    private final MongoTemplate mongoTemplate;

    public UserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<User> findUsers(Pageable pageable) {
        Query query = new Query();
        long count = this.mongoTemplate.count(query, User.class);

        query.with(pageable);
        List<User> users = mongoTemplate.find(query, User.class);
        return PageableExecutionUtils.getPage(users, pageable, () -> count);
    }
}
