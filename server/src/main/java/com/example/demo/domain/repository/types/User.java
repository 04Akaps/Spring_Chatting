package com.example.demo.domain.repository.types;

import java.security.Timestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long t_id;

    @Column
    private String name;

    @Column(nullable = false)
    private Timestamp created_at;

    @Column(nullable =  false)
    private Timestamp update_at;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = false)
    private UserCrendentials userCrendentials;
}
