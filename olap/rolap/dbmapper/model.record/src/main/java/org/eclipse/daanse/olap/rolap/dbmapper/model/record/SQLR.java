package org.eclipse.daanse.olap.rolap.dbmapper.model.record;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSQL;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record SQLR(String statement, List<String> dialects) implements MappingSQL {

    public SQLR(String statement, List<String> dialects) {
        this.dialects = dialects == null ? new ArrayList<>() : dialects;
        this.statement = statement;
    }

    @Override
    public int hashCode() {
        return Objects.hash(statement, dialects);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof MappingSQL that) {
            if (!Objects.equals(statement(), that.statement())) {
                return false;
            }
            if (dialects() == null || that.dialects() == null ||
                dialects().size() != that.dialects().size()) {
                return false;
            }
            for (int i = 0; i < dialects().size(); i++) {
                if (!dialects().get(i).equals(that.dialects().get(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
