package com.sevenlist.jaxrsplayground.resource

import com.sevenlist.jaxrsplayground.domain.Person
import org.codehaus.jackson.jaxrs.JacksonJsonProvider
import spock.lang.Specification

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.GenericType

import static javax.ws.rs.client.Entity.json

class PersonResourceTest extends Specification {

    Client client
    WebTarget webTarget

    def setup() {
        client = ClientBuilder.newClient()
        client.register(JacksonJsonProvider)
        webTarget = client.target('http://localhost:8080/persons')
    }

    def cleanup() {
        client.close()
    }

    def "person posted can be got"() {
        given:
        def person = new Person(firstName: 'John', lastName: 'Doe')
        webTarget.request().post(json(person))

        when:
        List<Person> persons = webTarget.request().get(new GenericType<List<Person>>() {})
        def personGot = persons[0]

        then:
        personGot == person
    }
}
