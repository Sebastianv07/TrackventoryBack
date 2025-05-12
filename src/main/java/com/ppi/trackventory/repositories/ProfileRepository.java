package com.ppi.trackventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ppi.trackventory.models.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
