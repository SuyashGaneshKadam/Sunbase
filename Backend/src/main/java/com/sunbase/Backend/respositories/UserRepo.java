package com.sunbase.Backend.respositories;

import com.sunbase.Backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    List<User> findByFirstName(String firstName);
    List<User>findByCity(String city);
    List<User> findByEmail(String email);
    List<User> findByPhone(String phone);

}