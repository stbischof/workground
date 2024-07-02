package org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSQL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SQL", propOrder = { "statement", "dialects" })
public class SQLImpl implements MappingSQL {

    @XmlElement(name = "SQLStatement", required = true)
    protected String statement;

    @XmlElement(name = "Dialect")
    protected List<String> dialects;

    @Override
    public List<String> dialects() {
        if (dialects == null  || dialects.isEmpty()) {
            dialects = Arrays.asList("generic");
        }
        return dialects;
    }

    @Override
    public String statement() {
        return statement != null ? statement.trim() : statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public void setDialects(List<String> dialects) {
        this.dialects = dialects;
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
