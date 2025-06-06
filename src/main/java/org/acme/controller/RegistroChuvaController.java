package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.RegistroChuvaRequestDTO;
import org.acme.dto.RegistroChuvaResponseDTO;
import org.acme.services.RegistroChuvaService;

import java.util.List;

@Path("/api/registros-chuva")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegistroChuvaController {

    @Inject
    RegistroChuvaService registroChuvaService;

    @GET
    public List<RegistroChuvaResponseDTO> listAll() {
        return registroChuvaService.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        try {
            return Response.ok(registroChuvaService.findById(id)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"erro\": \"" + e.getMessage() + "\"}").build();
        }
    }

    @POST
    public Response create(RegistroChuvaRequestDTO registroDTO) {
        try {
            RegistroChuvaResponseDTO criado = registroChuvaService.create(registroDTO);
            return Response.status(Response.Status.CREATED).entity(criado).build();
        } catch (NotFoundException e) { // If referenced usuarioId is not found
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"erro\": \"" + e.getMessage() + "\"}")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\": \"Erro ao criar Registro de Chuva: " + e.getMessage() + "\"}").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, RegistroChuvaRequestDTO registroDTO) {
        try {
            RegistroChuvaResponseDTO atualizado = registroChuvaService.update(id, registroDTO);
            return Response.ok(atualizado).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"erro\": \"" + e.getMessage() + "\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\": \"Erro ao atualizar Registro de Chuva: " + e.getMessage() + "\"}").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            registroChuvaService.delete(id);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"erro\": \"" + e.getMessage() + "\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\": \"Erro ao deletar Registro de Chuva: " + e.getMessage() + "\"}").build();
        }
    }
}