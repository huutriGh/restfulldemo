package com.example.restfull.anotation;


import jakarta.ws.rs.NameBinding;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


//AX-RS provides @NameBinding, a meta-annotation used to create name-binding annotations for filters and interceptors:
@NameBinding
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface Secured {
}
