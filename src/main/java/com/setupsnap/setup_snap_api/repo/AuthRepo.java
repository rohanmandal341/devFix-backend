package com.setupsnap.setup_snap_api.repo;

import com.setupsnap.setup_snap_api.model.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepo extends JpaRepository<AuthEntity,Long> {
    Optional<AuthEntity> findByEmail(String email);
}
