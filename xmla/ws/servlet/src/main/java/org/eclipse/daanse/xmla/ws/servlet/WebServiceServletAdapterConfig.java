package org.eclipse.daanse.xmla.ws.servlet;

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