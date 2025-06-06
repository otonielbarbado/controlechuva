package org.acme.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RegistroChuvaRequestDTO {
    public LocalDate dataOcorrencia;
    public String local;
    public BigDecimal volumeMm;
    public String observacoes;
    public Long usuarioId; // To link to UsuarioChuvaEntity

    // Getters and Setters
    public LocalDate getDataOcorrencia() {
        return dataOcorrencia;
    }

    public void setDataOcorrencia(LocalDate dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public BigDecimal getVolumeMm() {
        return volumeMm;
    }

    public void setVolumeMm(BigDecimal volumeMm) {
        this.volumeMm = volumeMm;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}