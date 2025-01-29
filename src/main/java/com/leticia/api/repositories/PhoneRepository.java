package com.leticia.api.repositories;

import com.leticia.api.domain.phone.Phone;
import com.leticia.api.domain.phone.PhoneResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhoneRepository extends JpaRepository <Phone, UUID> {
}
