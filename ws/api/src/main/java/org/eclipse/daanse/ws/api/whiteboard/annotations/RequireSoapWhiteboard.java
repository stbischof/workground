package org.eclipse.daanse.ws.api.whiteboard.annotations;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.daanse.ws.api.whiteboard.SoapWhiteboardConstants;
import org.osgi.annotation.bundle.Requirement;
import org.osgi.namespace.implementation.ImplementationNamespace;


@Documented
@Retention(RetentionPolicy.CLASS)
@Target({
		ElementType.TYPE, ElementType.PACKAGE
})
@Requirement(namespace = ImplementationNamespace.IMPLEMENTATION_NAMESPACE, //
		name = SoapWhiteboardConstants.SOAP, //
		version = SoapWhiteboardConstants.SOAP_SPECIFICATION_VERSION)
public @interface RequireSoapWhiteboard {
	// This is a marker annotation.
}