package org.eclipse.daanse.ws.runtime.embedded;

public @interface EmbeddedPublisherConfig {

	String protocol() default "http";
	
	String host() default "localhost";
	
	int port() default 9090;

}
