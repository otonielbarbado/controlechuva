package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.acme.entity.UsuarioChuvaEntity;
import org.acme.repository.UsuarioChuvaRepository;

import java.util.List;

@ApplicationScoped
public class UsuarioChuvaService {

    @Inject
    UsuarioChuvaRepository usuarioRepository;

    public List<UsuarioChuvaEntity> listAll() {
        return usuarioRepository.listAll();
    }

    public UsuarioChuvaEntity findById(Long id) {
        return usuarioRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Usuário (chuva) não encontrado com id: " + id));
    }

    @Transactional
    public UsuarioChuvaEntity create(UsuarioChuvaEntity usuario) {
        // Add any validation logic here
        // e.g., check if email already exists if it's mandatory and should be unique based on business rules beyond db constraint
        usuarioRepository.persist(usuario);
        return usuario;
    }

    @Transactional
    public UsuarioChuvaEntity update(Long id, UsuarioChuvaEntity usuarioData) {
        UsuarioChuvaEntity entity = findById(id);
        entity.nome = usuarioData.nome;
        entity.email = usuarioData.email; // Consider validation for email uniqueness if it can be changed
        entity.cidade = usuarioData.cidade;
        // usuarioRepository.persist(entity) is not strictly necessary for managed entities within a transaction
        return entity;
    }

    @Transactional
    public void delete(Long id) {
        UsuarioChuvaEntity entity = findById(id);
        // Consider implications of 'ON DELETE SET NULL' for RegistroChuvaEntity.
        // If there are RegistroChuvaEntities associated, their 'usuario_id' will become null by DB.
        usuarioRepository.delete(entity);
    }
}