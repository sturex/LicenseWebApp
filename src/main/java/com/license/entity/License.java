package com.license.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Table(name = "license")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class License {
    @Id
    @Column(name = "id", nullable = false)
    @GenericGenerator(
            name = "UUID_generator",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID_generator")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "created_at", nullable = false)
    private long createdAt;

    @Column(name = "signature", nullable = false)
    private String signature;

    @ManyToOne(optional = false)
    @JoinColumn(name = "key_pair_id", nullable = false)
    private KeyPair keyPair;

}