package com.example.demo.domain.repository.types;


import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="user_credentials")
public class UserCrendentials {
    @Id
    @OneToOne
    @JoinColumn(name = "user_t_id")
    private User user;
    
    @Column(unique = true)
    private Long user_t_id;

    @Column(nullable = false)
    private String hashed_password;
}
