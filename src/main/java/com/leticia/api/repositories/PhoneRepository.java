package com.leticia.api.repositories;

import com.leticia.api.domain.phone.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhoneRepository extends JpaRepository <Phone, UUID> {
}
