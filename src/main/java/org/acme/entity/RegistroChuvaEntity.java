package org.acme.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "registros_chuva") // As per your SQL
public class RegistroChuvaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "data_ocorrencia", nullable = false)
    public LocalDate dataOcorrencia;

    @Column(name = "local", nullable = false, length = 100)
    public String local;

    @Column(name = "volume_mm", nullable = false, precision = 5, scale = 2)
    public BigDecimal volumeMm;

    @Lob // For potentially large text, maps to CLOB in Oracle or TEXT in PostgreSQL
    @Column(name = "observacoes")
    public String observacoes;

    @ManyToOne(fetch = FetchType.LAZY) // Default is EAGER for ManyToOne, LAZY is often better
    @JoinColumn(name = "usuario_id") // This column in 'registros_chuva' table
                                     // 'ON DELETE SET NULL' should be configured at DB level.
                                     // Hibernate's @OnDelete(action = OnDeleteAction.SET_NULL) could be used if only Hibernate is targeted.
    public UsuarioChuvaEntity usuario;

    // Getters and setters
    // Constructors
}