package com.license.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Table(name = "key_pair")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeyPair {
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

    @Column(name = "public_key", nullable = false)
    private String publicKey;

    @Column(name = "private_key", nullable = false)
    private String privateKey;

    @Column(name = "created_at", nullable = false)
    private long createdAt;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "keyPair")
    private List<License> licenses;
}