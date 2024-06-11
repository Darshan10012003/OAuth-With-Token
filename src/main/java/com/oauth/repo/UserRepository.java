package com.oauth.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.oauth.userdetails.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

	boolean existsByUserName(String username);

	Optional<User> findByUserName(String username);
}
