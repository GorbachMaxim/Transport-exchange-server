package com.example.transportexchangeserver.repository;


import com.example.transportexchangeserver.model.ERole;
import com.example.transportexchangeserver.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
