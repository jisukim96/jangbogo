package com.jangbogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jangbogo.domain.board.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
