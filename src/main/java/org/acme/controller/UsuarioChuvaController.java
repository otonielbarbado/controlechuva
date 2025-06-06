package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entity.UsuarioChuvaEntity;
import org.acme.services.UsuarioChuvaService;

import java.util.List;

@Path("/api/usuarios-chuva") // Changed path to avoid conflict if old UsuarioController exists
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioChuvaController {

    @Inject
    UsuarioChuvaService usuarioService;

    @GET
    public List<UsuarioChuvaEntity> listAll() {
        return usuarioService.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        try {
            return Response.ok(usuarioService.findById(id)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"erro\": \"" + e.getMessage() + "\"}").build();
        }
    }

    @POST
    public Response create(UsuarioChuvaEntity usuario) {
        try {
            UsuarioChuvaEntity criado = usuarioService.create(usuario);
            return Response.status(Response.Status.CREATED).entity(criado).build();
        } catch (Exception e) { // Catch more specific exceptions as needed (e.g., for unique constraint
                                // violation)
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\": \"Erro ao criar Usuário (chuva): " + e.getMessage() + "\"}").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, UsuarioChuvaEntity usuario) {
        try {
            return Response.ok(usuarioService.update(id, usuario)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"erro\": \"" + e.getMessage() + "\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\": \"Erro ao atualizar Usuário (chuva): " + e.getMessage() + "\"}").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            usuarioService.delete(id);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"erro\": \"" + e.getMessage() + "\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\": \"Erro ao deletar Usuário (chuva): " + e.getMessage() + "\"}").build();
        }
    }
}