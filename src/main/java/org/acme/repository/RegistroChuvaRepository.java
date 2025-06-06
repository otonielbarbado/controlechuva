package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.RegistroChuvaEntity;
// import java.util.List; // If adding custom methods returning List

@ApplicationScoped
public class RegistroChuvaRepository implements PanacheRepository<RegistroChuvaEntity> {
    // Example custom query (adapt as needed):
    // public List<RegistroChuvaEntity> findByUsuarioId(Long usuarioId) {
    // return list("usuario.id", usuarioId);
    // }
}