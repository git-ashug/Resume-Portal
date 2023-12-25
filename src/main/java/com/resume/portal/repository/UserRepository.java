package com.resume.portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resume.portal.models.PortalUser;

public interface UserRepository extends JpaRepository<PortalUser, Integer>{

	Optional<PortalUser> findByUserName(String userName);
}
