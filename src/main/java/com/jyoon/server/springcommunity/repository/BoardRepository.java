package com.jyoon.server.springcommunity.repository;

import com.jyoon.server.springcommunity.domain.Board;
import com.jyoon.server.springcommunity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByUser(User user);
}
