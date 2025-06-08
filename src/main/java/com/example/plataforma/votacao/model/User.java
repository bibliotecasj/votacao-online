package com.voting.platform.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "users") // Evita conflito com palavra reservada 'user' no MySQL
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "is_admin", nullable = false)
    private boolean isAdmin = false;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // Construtores
    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters e Setters (gerados via IDE ou Lombok)
}