package mondrian.rolap;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.eclipse.daanse.olap.api.ConnectionProps;

public record RolapConnectionPropsR(List<String> roles, boolean useSchemaPool, Locale locale, long pinSchemaTimeout,
		TimeUnit pinSchemaTimeoutUnit, Optional<String> aggregateScanSchema, Optional<String> aggregateScanCatalog)
		implements ConnectionProps {

	public RolapConnectionPropsR() {
		this(List.of(), true, Locale.getDefault(), -1, TimeUnit.SECONDS, Optional.empty(), Optional.empty());
	}

    public RolapConnectionPropsR(List<String> roles) {
        this(roles, true, Locale.getDefault(), -1, TimeUnit.SECONDS, Optional.empty(), Optional.empty());
    }
}
