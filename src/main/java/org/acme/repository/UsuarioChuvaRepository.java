package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.UsuarioChuvaEntity;

@ApplicationScoped
public class UsuarioChuvaRepository implements PanacheRepository<UsuarioChuvaEntity> {

    public UsuarioChuvaEntity findByEmail(String email) {
        return find("email", email).firstResult();
    }
    // Add other custom queries if needed
}