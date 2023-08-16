package com.serasa.user.domain.model;

import java.time.LocalDateTime;

// import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name= "user" )
// @RedisHash("users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String cpf;
	private String email;
	private String phoneNumber;

	@Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public User() {}

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}