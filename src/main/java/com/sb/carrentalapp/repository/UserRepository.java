package com.sb.carrentalapp.repository;

import com.sb.carrentalapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
