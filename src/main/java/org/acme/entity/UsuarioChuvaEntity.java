package org.acme.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios") // As per your SQL
public class UsuarioChuvaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Assumes DB support like Oracle 12c+ or PostgreSQL SERIAL
    @Column(name = "id")
    public Long id;

    @Column(name = "nome", nullable = false, length = 100)
    public String nome;

    @Column(name = "email", unique = true, length = 100) // `nullable = true` by default, matches SQL
    public String email;

    @Column(name = "cidade", length = 100)
    public String cidade;

    // Getters and setters can be added if needed, though Panache active record style might not require them publicly.
    // Constructors can also be added as needed.
}