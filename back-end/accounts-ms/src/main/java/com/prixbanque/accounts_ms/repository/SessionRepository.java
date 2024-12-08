package com.prixbanque.accounts_ms.repository;

import com.prixbanque.accounts_ms.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, String> {
    List<Session> findByUserId(Long userId);
    void deleteByUserId(Long userId);
}
