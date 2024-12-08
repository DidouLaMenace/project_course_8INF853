package com.prixbanque.accounts_ms.service;

import com.prixbanque.accounts_ms.model.Account;
import com.prixbanque.accounts_ms.model.Session;
import com.prixbanque.accounts_ms.repository.AccountRepository;
import com.prixbanque.accounts_ms.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public boolean validateSession(String sessionId) {
        return sessionRepository.findById(sessionId)
//                .filter(session -> session.getUserId().equals(userId))
                .filter(session -> session.getExpirationDate().isAfter(LocalDateTime.now()))
                .filter(session -> session.getActive().equals(true))
                .isPresent();
    }

    public String createSession(Long userId) {
        Session newSession = new Session();
        newSession.setUserId(userId);
        newSession.setCreationDate(LocalDateTime.now());
        newSession.setExpirationDate(LocalDateTime.now().plusHours(1));
        newSession.setActive(true);
        return sessionRepository.save(newSession).getSessionId();
    }

    public void logout(String sessionId) {
        sessionRepository.deleteById(sessionId);
    }

    public Optional<Long> getUserIdBySessionId(String sessionId) {
        Optional<Session> s = sessionRepository.findById(sessionId);
        if (s.isPresent()) {
            return Optional.of(s.get().getUserId());
        } else {
            return Optional.empty();
        }
    }
}