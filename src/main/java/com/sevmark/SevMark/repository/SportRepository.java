package com.sevmark.SevMark.repository;

import com.sevmark.SevMark.model.Sport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportRepository extends JpaRepository<Sport, Long> {
}
