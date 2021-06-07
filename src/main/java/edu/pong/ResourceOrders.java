package edu.pong;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/orders")
public class ResourceOrders {
    

    
    public ResourceOrders() {
    }

    @Inject
    ServiceOrder service;

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    // curl -w "\n" http://localhost:8080/fruits/ 
    // -H "Content-Type: application/x-www-form-urlencoded"
    public String hello() {
        return "Colmados Farmer Rick";
    }
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    // no es necesario Produces ya que por defecto
    // resteasy jackson desactiva la negociación
    // y sirve MediaType.APPLICATION_JSON
    // curl -w "\n" http://localhost:8080/fruits/ -H "Content-Type: application/json"
    public List<Orders> list() {
        return service.listOrders();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    // curl -d '{"name":"Banana", "description":"Brings a Gorilla too", "farmer": {"name": "Farmer Rick"}}'
    // -H "Content-Type: application/json" -X POST http://localhost:8080/fruits
    public List<Orders> add(@Valid Orders order) {
        service.addOrder(order);
        return this.list();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    // curl -d '{"name":"Banana", "description":"Brings a Gorilla too", "farmer": {"name": "Farmer Rick"}}'
    // -H "Content-Type: application/json" -X DELETE http://localhost:8080/fruits   
    public List<Orders> delete(@Valid Orders order) {
        service.remove(order.id);
        return list();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    // curl -w "\n" http://localhost:8080/fruits/Apple -v
    // curl -w "\n" http://localhost:8080/fruits/jkl -v
    public Response get(@PathParam("id") String id) {
        Optional<Orders> order = service.getOrder(id);
        return order.isPresent()? 
            Response.status(Response.Status.OK).entity(order.get()).build() : 
            Response.status(Response.Status.NOT_FOUND).build();
    }
}

