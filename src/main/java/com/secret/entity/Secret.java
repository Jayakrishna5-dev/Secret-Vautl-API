package com.secret.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import com.secret.user.userEntity.UserEntity;

@Entity
@Table(name = "secrets")
@Getter
@Setter
public class Secret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Secret key cannot be blank")
    @Column(nullable = false, unique = true)
    private String secretKey;

    @NotBlank(message = "Content cannot be blank")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @NotNull(message = "Expiry date is required")
    @Future(message = "Expiry date must be in the future")
    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private Boolean viewed = false;

    private LocalDateTime viewedAt;

    @NotNull(message = "Creator is required")
    @ManyToOne
    @JoinColumn(name = "created_by")
    private UserEntity createdBy;
    
}