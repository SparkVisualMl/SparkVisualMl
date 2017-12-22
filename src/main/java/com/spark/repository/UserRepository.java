package com.spark.repository;

import com.spark.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  UserRepository extends JpaRepository<User, Long> {

    List<User> findByAddress(String name);
    User findByNameAndAddress(String name, String address);

}
