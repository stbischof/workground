package mondrian.rolap.physicalschema;

import mondrian.olap.Util;
import mondrian.rolap.RolapAggregator;
import mondrian.rolap.RolapCube;

import java.util.Collections;
import java.util.List;
import java.util.Set;

class PhysSchemaBuilder {

    final RolapSchemaLoader loader;
    RolapCube cube;
    final PhysSchema physSchema;
    private int nextId = 0;

    PhysSchemaBuilder(
        RolapSchemaLoader loader,
        PhysSchema physSchema
    ) {
        assert physSchema != null;
        this.loader = loader;
        this.physSchema = physSchema;
    }


    /**
     * Returns the relations that a list of expressions belong to.
     *
     * @param list            List of expressions
     * @param defaultRelation Relation that an expression should belong to
     *                        if not explicitly specified
     * @param relationSet     Set of relations to add to
     */
    private static void collectRelations(
        List<PhysExpr> list,
        PhysRelation defaultRelation,
        Set<PhysRelation> relationSet
    ) {
        for (PhysExpr expr : list) {
            collectRelations(expr, defaultRelation, relationSet);
        }
    }

    /**
     * Collects the relations that an expression belongs to.
     *
     * @param expr            Expression
     * @param defaultRelation Default relation, for expressions that
     *                        do not explicitly belong to a relation (e.g. '1' or 'count(*)').
     *                        May be null if there are multiple relations to choose from
     * @param relationSet     Set of relations to add to
     */
    static void collectRelations(
        PhysExpr expr,
        PhysRelation defaultRelation,
        Set<PhysRelation> relationSet
    ) {
        if (expr instanceof PhysColumn) {
            PhysColumn column = (PhysColumn) expr;
            assert column.relation != null;
            relationSet.add(column.relation);
        } else if (expr instanceof PhysCalcColumn) {
            collectRelations(
                ((PhysCalcColumn) expr).getList(),
                defaultRelation,
                relationSet);
        } else if (expr instanceof PhysCalcExpr) {
            collectRelations(
                ((PhysCalcExpr) expr).list,
                defaultRelation,
                relationSet);
        } else if (defaultRelation != null) {
            relationSet.add(defaultRelation);
        }
    }

    public PhysRelation getPhysRelation(
        String alias,
        boolean fail
    ) {
        final PhysRelation physTable =
            physSchema.tablesByName.get(alias);
        if (physTable == null && fail) {
            throw Util.newInternal("Table '" + alias + "' not found");
        }
        return physTable;
    }

    public PhysColumn toPhysColumn(
        PhysRelation physRelation,
        String column
    ) {
        return physRelation.getColumn(column, true);
    }

    /**
     * Creates a dummy column expression. Generally this is used in the
     * event of an error, to continue the validation process.
     *
     * @param relation Relation, may be null
     * @return dummy column
     */
    public PhysColumn dummyColumn(
        PhysRelation relation
    ) {
        if (relation == null) {
            final String tableName = "dummyTable$" + (nextId++);
            relation =
                new PhysTable(
                    physSchema, null, tableName, tableName,
                    Collections.<String, String>emptyMap());
        }
        return
            new PhysCalcColumn(
                loader,
                null,
                relation,
                "dummy$" + (nextId++),
                null,
                null,
                Collections.<PhysExpr>singletonList(
                    new PhysTextExpr("0")));
    }

    /**
     * Collects the relationships used in an aggregate expression.
     *
     * @param aggregator      Aggregate function
     * @param expr            Expression (may be null)
     * @param defaultRelation Default relation
     * @param relationSet     Relation set to populate
     */
    public static void collectRelations(
        RolapAggregator aggregator,
        PhysExpr expr,
        PhysRelation defaultRelation,
        Set<PhysRelation> relationSet
    ) {
        assert aggregator != null;
        if (aggregator == RolapAggregator.Count
            && expr == null
            && defaultRelation != null) {
            relationSet.add(defaultRelation);
        }
        if (expr != null) {
            collectRelations(expr, defaultRelation, relationSet);
        }
    }

    public Handler getHandler() {
        return loader.getHandler();
    }

}
