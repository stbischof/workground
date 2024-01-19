package mondrian.rolap.physicalschema;

import mondrian.rolap.util.DirectedGraph;

import java.util.List;

/**
 * Link between two tables, also known as a relationship.
 *
 * <p>A link has a direction: it is said to be from the target table (with
 * the foreign key) to the source table (which contains the primary key).
 * It is in the 'many to one' direction.
 *
 * <p>For example, in the FoodMart star schema, there are links from the
 * fact table SALES_FACT to the dimension tables TIME_BY_DAY and PRODUCT,
 * and a further link from PRODUCT to PRODUCT_CLASS, making Product a
 * snowflake dimension.
 */
public class PhysLink implements DirectedGraph.Edge<PhysRelation> {
    final PhysKey sourceKey;
    public final PhysRelation targetRelation;
    final List<PhysColumn> columnList;
    public final String sql;

    /**
     * Creates a link from {@code targetTable} to {@code sourceTable} over
     * a list of columns.
     *
     * @param sourceKey Key of source table (usually the primary key)
     * @param targetRelation Target table (contains foreign key)
     * @param columnList Foreign key columns in target table
     */
    public PhysLink(
        PhysKey sourceKey,
        PhysRelation targetRelation,
        List<PhysColumn> columnList)
    {
        this.sourceKey = sourceKey;
        this.targetRelation = targetRelation;
        this.columnList = columnList;
        assert columnList.size() == sourceKey.columnList.size()
            : columnList + " vs. " + sourceKey.columnList;
        for (PhysColumn column : columnList) {
            assert column.relation == targetRelation
                : column.relation + "/" + targetRelation;
        }
        this.sql = deriveSql();
    }

    public int hashCode() {
        return Util.hashV(0, sourceKey, targetRelation, columnList);
    }

    public boolean equals(Object obj) {
        if (obj instanceof PhysLink) {
            PhysLink that = (PhysLink) obj;
            return this.sourceKey.equals(that.sourceKey)
                && this.targetRelation.equals(that.targetRelation)
                && this.columnList.equals(that.columnList);
        }
        return false;
    }

    public String toString() {
        return "Link from " + targetRelation + " "
            + columnList
            + " to " + sourceKey;
    }

    public PhysRelation getFrom() {
        return targetRelation;
    }

    public PhysRelation getTo() {
        return sourceKey.relation;
    }

    public String toSql() {
        return sql;
    }

    private String deriveSql() {
        final StringBuilder buf = new StringBuilder();
        for (int i = 0; i < columnList.size(); i++) {
            if (buf.length() > 0) {
                buf.append(" and ");
            }
            PhysColumn targetColumn = columnList.get(i);
            final PhysExpr sourceColumn = sourceKey.columnList.get(i);
            buf.append(targetColumn.toSql())
                .append(" = ").append(sourceColumn.toSql());
        }
        return buf.toString();
    }
}
