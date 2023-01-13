package org.eclipse.daanse.ws.servlet;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition()
@interface WebServiceServletAdapterConfig {

    @AttributeDefinition
    String name();

    @AttributeDefinition
    String path();

    @AttributeDefinition
    String contextSelect();

}