package org.eclipse.daanse.xmla.api;

import java.util.Optional;

public interface RequestMetaData {

	Optional<String> userAgent();
    Optional<String> sessionId();
}
