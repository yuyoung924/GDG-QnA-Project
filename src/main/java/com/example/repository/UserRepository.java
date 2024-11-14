package com.example.repository;

import com.example.qna_project.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// user/repository/UserRepository.java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    //TODO: 페이징,검색 기능 추가
}
