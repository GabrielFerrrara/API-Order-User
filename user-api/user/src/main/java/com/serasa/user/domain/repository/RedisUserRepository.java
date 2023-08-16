package com.serasa.user.domain.repository;

import com.serasa.user.domain.model.User;
import org.springframework.data.repository.CrudRepository;
// import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Repository;

@Repository
// @EnableRedisRepositories
public interface RedisUserRepository extends CrudRepository<User, Long> {
    // ...
}