package com.setupsnap.setup_snap_api.repo;

import com.setupsnap.setup_snap_api.model.SetupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetupRepo extends JpaRepository<SetupEntity,Long> {
    List<SetupEntity> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title,String description);
}
