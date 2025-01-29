package com.leticia.api.repositories;

import com.leticia.api.domain.user.User;
import com.leticia.api.domain.user.UserResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}
