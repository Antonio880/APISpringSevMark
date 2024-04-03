package com.sevmark.SevMark.repository;

import com.sevmark.SevMark.model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkRepository  extends JpaRepository<Mark, Long> {
}
