package com.spoteditor.backend.placelog.repository;

import com.spoteditor.backend.placelog.entity.PlaceLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceLogRepository extends JpaRepository<PlaceLog, Long>, PlaceLogRepositoryCustom {
}
