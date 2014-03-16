package com.sevenlist.jaxrsplayground.resource

import com.sevenlist.jaxrsplayground.domain.Person

import javax.ws.rs.*
import javax.ws.rs.core.Response
import java.util.concurrent.ConcurrentHashMap

import static javax.ws.rs.core.MediaType.APPLICATION_JSON

@Path('/persons')
class PersonResource {

    private Map persons = new ConcurrentHashMap()

    @POST
    @Consumes(APPLICATION_JSON)
    Response addPerson(Person person) {
        def id = UUID.randomUUID().toString()
        persons[id] = person

        def location = URI.create(id)
        Response.created(location).build()
    }

    @GET
    @Produces(APPLICATION_JSON)
    Collection getPersons() {
        persons.values()
    }
}
