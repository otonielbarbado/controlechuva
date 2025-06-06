package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.acme.dto.RegistroChuvaRequestDTO;
import org.acme.entity.RegistroChuvaEntity;
import org.acme.entity.UsuarioChuvaEntity;
import org.acme.repository.RegistroChuvaRepository;
import org.acme.repository.UsuarioChuvaRepository; // Renamed to UsuarioChuvaRepository

import java.util.List;
import java.util.stream.Collectors;
import org.acme.dto.RegistroChuvaResponseDTO;

@ApplicationScoped
public class RegistroChuvaService {

    @Inject
    RegistroChuvaRepository registroChuvaRepository;

    @Inject
    UsuarioChuvaRepository usuarioChuvaRepository; // Corrected repository name

    public List<RegistroChuvaResponseDTO> listAll() {
        return registroChuvaRepository.listAll().stream()
                .map(RegistroChuvaResponseDTO::new)
                .collect(Collectors.toList());
    }

    public RegistroChuvaResponseDTO findById(Long id) {
        RegistroChuvaEntity entity = registroChuvaRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Registro de Chuva não encontrado com id: " + id));
        return new RegistroChuvaResponseDTO(entity);
    }

    @Transactional
    public RegistroChuvaResponseDTO create(RegistroChuvaRequestDTO dto) {
        RegistroChuvaEntity entity = new RegistroChuvaEntity();
        entity.dataOcorrencia = dto.getDataOcorrencia();
        entity.local = dto.getLocal();
        entity.volumeMm = dto.getVolumeMm();
        entity.observacoes = dto.getObservacoes();

        if (dto.getUsuarioId() != null) {
            UsuarioChuvaEntity usuario = usuarioChuvaRepository.findByIdOptional(dto.getUsuarioId())
                    .orElseThrow(() -> new NotFoundException(
                            "Usuário (chuva) não encontrado com id: " + dto.getUsuarioId()));
            entity.usuario = usuario;
        } else {
            // Depending on requirements, you might allow null usuario_id or throw an error
            entity.usuario = null; // Or throw new BadRequestException("usuarioId é obrigatório.");
        }

        registroChuvaRepository.persist(entity);
        return new RegistroChuvaResponseDTO(entity);
    }

    @Transactional
    public RegistroChuvaResponseDTO update(Long id, RegistroChuvaRequestDTO dto) {
        RegistroChuvaEntity entity = registroChuvaRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Registro de Chuva não encontrado com id: " + id));

        entity.dataOcorrencia = dto.getDataOcorrencia();
        entity.local = dto.getLocal();
        entity.volumeMm = dto.getVolumeMm();
        entity.observacoes = dto.getObservacoes();

        if (dto.getUsuarioId() != null) {
            UsuarioChuvaEntity usuario = usuarioChuvaRepository.findByIdOptional(dto.getUsuarioId())
                    .orElseThrow(() -> new NotFoundException(
                            "Usuário (chuva) não encontrado com id: " + dto.getUsuarioId()));
            entity.usuario = usuario;
        } else {
            entity.usuario = null; // Allow unsetting the user
        }
        return new RegistroChuvaResponseDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        RegistroChuvaEntity entity = registroChuvaRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Registro de Chuva não encontrado com id: " + id));
        registroChuvaRepository.delete(entity);
    }
}