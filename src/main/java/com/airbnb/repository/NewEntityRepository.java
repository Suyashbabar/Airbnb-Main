package com.airbnb.repository;

import com.airbnb.entity.NewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewEntityRepository extends JpaRepository<NewEntity, Long> {
}