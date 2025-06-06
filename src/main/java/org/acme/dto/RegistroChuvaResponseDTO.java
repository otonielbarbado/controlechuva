package org.acme.dto;

import org.acme.entity.RegistroChuvaEntity;
import java.math.BigDecimal;
import java.time.LocalDate;

public class RegistroChuvaResponseDTO {
    public Long id;
    public LocalDate dataOcorrencia;
    public String local;
    public BigDecimal volumeMm;
    public String observacoes;
    public String nomeUsuario; // From joined UsuarioChuvaEntity
    public Long usuarioId;

    public RegistroChuvaResponseDTO(RegistroChuvaEntity entity) {
        this.id = entity.id;
        this.dataOcorrencia = entity.dataOcorrencia;
        this.local = entity.local;
        this.volumeMm = entity.volumeMm;
        this.observacoes = entity.observacoes;
        if (entity.usuario != null) {
            this.nomeUsuario = entity.usuario.nome;
            this.usuarioId = entity.usuario.id;
        }
    }

    // Getters and Setters (optional, public fields are often used with DTOs in Quarkus)
};
