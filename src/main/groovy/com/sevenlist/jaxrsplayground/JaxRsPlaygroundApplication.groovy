package com.sevenlist.jaxrsplayground

import com.sevenlist.jaxrsplayground.resource.PersonRessource
import org.glassfish.jersey.jackson.JacksonFeature

import javax.ws.rs.ApplicationPath
import javax.ws.rs.core.Application

@ApplicationPath('/')
class JaxRsPlaygroundApplication extends Application {

    private Set singletons = new HashSet();

    JaxRsPlaygroundApplication() {
        singletons << new JacksonFeature()
        singletons << new PersonRessource()
    }

    @Override
    Set<Object> getSingletons() {
        singletons
    }
}
