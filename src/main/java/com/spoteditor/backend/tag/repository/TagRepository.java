package com.spoteditor.backend.tag.repository;

import com.spoteditor.backend.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long>, TagRepositoryCustom {
}
