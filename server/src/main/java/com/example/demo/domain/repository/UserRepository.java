package com.example.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.repository.types.User;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    boolean existsByName(String username);

    @Query("SELECT u.name FROM User u WHERE LOCATE(LOWER(:pattern), LOWER(u.name)) > 0")
    List<String> findNamesByPartialNameMatch(@Param("pattern") String pattern);
}
