package com.resume.portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resume.portal.models.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

	Optional<UserProfile> findByUserName(String userName);
}
