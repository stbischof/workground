package org.eclipse.daanse.ws.api.whiteboard.prototypes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.daanse.ws.api.whiteboard.annotations.RequireSoapWhiteboard;
import org.osgi.service.component.annotations.ComponentPropertyType;

@ComponentPropertyType
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
@RequireSoapWhiteboard
public @interface SOAPEndpointPath {

    String PREFIX_ = "osgi.";

    String path() default "";
}
