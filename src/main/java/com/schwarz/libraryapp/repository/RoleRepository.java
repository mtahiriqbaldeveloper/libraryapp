package com.schwarz.libraryapp.repository;

import com.schwarz.libraryapp.entity.Role;
import com.schwarz.libraryapp.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}