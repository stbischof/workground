package org.eclipse.daanse.xmla.model.record;

import java.util.Optional;

import org.eclipse.daanse.xmla.api.RequestMetaData;

public record RequestMetaDataR(Optional<String> userAgent, Optional<String> sessionId) implements RequestMetaData {

}
