package org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.mandantory;

import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.api.Level.ERROR;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.api.Level.WARNING;
import static org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaWalkerMessages.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.TreeSet;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAction;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggColumnName;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggForeignKey;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggLevel;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggMeasureFactCount;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggName;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggPattern;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAnnotation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCalculatedMember;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCalculatedMemberProperty;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingClosure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumn;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingColumnDef;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeUsage;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDimensionGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDimensionUsage;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDrillThroughAttribute;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingDrillThroughMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingElementFormatter;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingFormula;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchy;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchyGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHint;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingInlineTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingJoin;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMemberGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingNamedSet;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingParameter;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingPrivateDimension;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingProperty;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRole;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingRoleUsage;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSQL;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchema;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingSchemaGrant;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingUnion;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingUserDefinedFunction;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingValue;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingView;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingVirtualCube;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingWritebackAttribute;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingWritebackMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingWritebackTable;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.DimensionTypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.enums.LevelTypeEnum;
import org.eclipse.daanse.olap.rolap.dbmapper.verifyer.api.Cause;
import org.eclipse.daanse.olap.rolap.dbmapper.verifyer.api.Level;
import org.eclipse.daanse.olap.rolap.dbmapper.verifyer.api.VerificationResult;
import org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.AbstractSchemaWalker;
import org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.SchemaExplorer;
import org.eclipse.daanse.olap.rolap.dbmapper.verifyer.basic.VerificationResultR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MandantoriesSchemaWalker extends AbstractSchemaWalker {

    private static final Logger LOGGER = LoggerFactory.getLogger(MandantoriesSchemaWalker.class);
    private static final String[] DEF_LEVEL = {"column", "nameColumn", "parentColumn", "ordinalColumn", "captionColumn"};

    public MandantoriesSchemaWalker(MandantoriesVerifierConfig config) {
    }

    @Override
    public List<VerificationResult> checkSchema(MappingSchema schema) {
        super.checkSchema(schema);
        if (schema != null) {
            if (isEmpty(schema.name())) {
                results.add(new VerificationResultR(SCHEMA, SCHEMA_NAME_MUST_BE_SET, ERROR,
                    Cause.SCHEMA));
            }
        } else {
            results.add(new VerificationResultR(SCHEMA, SCHEMA_MUST_BE_NOT_NULL, ERROR,
                Cause.SCHEMA));
        }

        return results;
    }

    @Override
    protected void checkCube(MappingCube cube) {
        super.checkCube(cube);
        if (cube != null) {
            if (isEmpty(cube.name())) {
                results.add(new VerificationResultR(CUBE, CUBE_NAME_MUST_SET, Level.ERROR,
                    Cause.SCHEMA));
            }

            if (cube.fact() == null
                || ((cube.fact() instanceof MappingTable table) && isEmpty(table.name()))
                || ((cube.fact() instanceof MappingView view) && isEmpty(view.alias()))) {
                String msg = String.format(FACT_NAME_MUST_BE_SET, orNotSet(cube.name()));
                results.add(new VerificationResultR(CUBE, msg, ERROR,
                    Cause.SCHEMA));
            }

            //CubeDimension
            if (cube.dimensionUsageOrDimensions() == null || cube.dimensionUsageOrDimensions()
                .isEmpty()) {
                String msg = String.format(CUBE_WITH_NAME_MUST_CONTAIN, orNotSet(cube.name()), DIMENSIONS);
                results.add(new VerificationResultR(DIMENSIONS, msg,
                    ERROR, Cause.SCHEMA));
            }

            //Measure
            if (cube.measures() == null || cube.measures()
                .isEmpty()) {
                String msg = String.format(CUBE_WITH_NAME_MUST_CONTAIN, orNotSet(cube.name()), MEASURE);
                results.add(new VerificationResultR(MEASURE, msg, ERROR,
                    Cause.SCHEMA));
            }
        }
    }

    @Override
    protected void checkMeasure(MappingMeasure measure, MappingCube cube) {
        super.checkMeasure(measure, cube);
        if (measure != null) {
            if (isEmpty(measure.name())) {
                String msg = String.format(MEASURE_NAME_MUST_BE_SET, orNotSet(cube.name()));
                results.add(new VerificationResultR(MEASURE, msg, ERROR, Cause.SCHEMA));

            }
            if (isEmpty(measure.aggregator())) {
                String msg = String.format(MEASURE_AGGREGATOR_MUST_BE_SET, orNotSet(cube.name()));
                results.add(new VerificationResultR(MEASURE, msg, ERROR, Cause.SCHEMA));
            }

            //ExpressionView
            if (measure.measureExpression() != null) {
                // Measure expressions are OK
            } else if (isEmpty(measure.column())) {
                String msg = String.format(MEASURE_COLUMN_MUST_BE_SET, orNotSet(cube.name()));
                results.add(new VerificationResultR(MEASURE, msg, ERROR,
                    Cause.SCHEMA));

            }
        }
    }

    @Override
    protected void checkElementFormatter(MappingElementFormatter elementFormatter) {
        super.checkElementFormatter(elementFormatter);
        if (elementFormatter != null
            && isEmpty(elementFormatter.className())
            && elementFormatter.script() == null) {
            results.add(new VerificationResultR(ELEMENT_FORMATTER,
                FORMATTER_EITHER_A_CLASS_NAME_OR_A_SCRIPT_ARE_REQUIRED, ERROR, Cause.SCHEMA));
        }

    }

    @Override
    protected void checkCubeDimension(MappingCubeDimension cubeDimension, MappingCube cube) {
        super.checkCubeDimension(cubeDimension, cube);
        if (cube != null) {
            if (isEmpty(cubeDimension.name())) {
                String msg = String.format(CUBE_DIMENSION_NAME_MUST_BE_SET, orNotSet(cube.name()));
                results.add(new VerificationResultR(CUBE_DIMENSION, msg, ERROR, Cause.SCHEMA));

            }

            if (cubeDimension instanceof MappingDimensionUsage dimensionUsage && isEmpty(dimensionUsage.source())) {
                String msg = String.format(SOURCE_MUST_BE_SET, orNotSet(cubeDimension.name()));
                results.add(new VerificationResultR(CUBE_DIMENSION, msg, ERROR, Cause.SCHEMA));
            }
        }
    }

    @Override
    protected void checkHierarchy(MappingHierarchy hierarchy, MappingPrivateDimension cubeDimension, MappingCube cube) {
        super.checkHierarchy(hierarchy, cubeDimension, cube);

        if (hierarchy != null) {
            checkHierarchyJoin(hierarchy, cubeDimension);


            //Level
            checkHierarchyLevels(hierarchy, cubeDimension);


            // Validates against primaryKeyTable name on field when using
            // Table.
            checkHierarchyTable(hierarchy, cubeDimension);

            // Validates that the value at primaryKeyTable corresponds to
            // tables in joins.
            checkHierarchyPrimaryKeyTable(hierarchy, cubeDimension);
        }

    }

    @Override
    protected void checkJoin(MappingJoin join) {
        super.checkJoin(join);
        if (join != null) {
            if (isEmpty(join.leftKey())) {
                results.add(new VerificationResultR(JOIN, JOIN_LEFT_KEY_MUST_BE_SET, ERROR,
                    Cause.SCHEMA));
            }
            if (isEmpty(join.rightKey())) {
                results.add(new VerificationResultR(JOIN, JOIN_RIGHT_KEY_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));
            }
            if (join.relations() == null || join.relations().size() < 2) {
                results.add(new VerificationResultR(JOIN, JOIN_RELATION_MUST_BE_SET_LEFT_AND_RIGHT,
                    ERROR, Cause.SCHEMA));
            }
        }
    }

    @Override
    protected void checkTable(MappingTable table) {
        super.checkTable(table);
        if (table != null) {
            if (isEmpty(table.name())) {
                results.add(
                    new VerificationResultR(TABLE, TABLE_NAME_MUST_BE_SET, ERROR, Cause.DATABASE));
            }

            String theSchema = table.schema();
            if (isEmpty(theSchema) && isSchemaRequired()) {
                results.add(
                    new VerificationResultR(TABLE, SCHEMA_MUST_BE_SET, WARNING, Cause.DATABASE));
            }
        }
    }

    @Override
    protected void checkLevel(
        org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingLevel level, MappingHierarchy hierarchy,
        MappingPrivateDimension parentDimension, MappingCube cube
    ) {
        super.checkLevel(level, hierarchy, parentDimension, cube);
        // Check 'column' exists in 'table' if table is specified
        // otherwise :: case of join.

        // It should exist in relation table if it is specified
        // otherwise :: case of table.

        // It should exist in fact table :: case of degenerate dimension
        // where dimension columns exist in fact table and there is no
        // separate table.

        if (level != null) {
            checkLevelType(level, parentDimension);
            // verify level's name is set
            if (isEmpty(level.name())) {
                String msg = String.format(LEVEL_NAME_MUST_BE_SET, orNotSet(hierarchy.name()));
                results.add(new VerificationResultR(LEVEL, msg, ERROR, Cause.SCHEMA));
            }

            // check level's column is in fact table
            checkLevelColumn(level, hierarchy, cube);
            if (level.memberFormatter() != null) {
                checkElementFormatter(level.memberFormatter());
            }
        }
    }

    @Override
    protected void checkProperty(
        MappingProperty property, org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingLevel level,
        MappingHierarchy hierarchy, MappingCube cube
    ) {
        super.checkProperty(property, level, hierarchy, cube);
        // Check 'column' exists in 'table' if [level table] is
        // specified otherwise :: case of join.

        // It should exist in [hierarchy relation table] if it is
        // specified otherwise :: case of table.

        // It should exist in [fact table] :: case of degenerate
        // dimension where dimension columns exist in fact table and
        // there is no separate table.

        // check property's column is in table
        if (property != null) {
            String column = property.column();
            if (isEmpty(column)) {
                results.add(new VerificationResultR(PROPERTY, PROPERTY_COLUMN_MUST_BE_SET, ERROR, Cause.SCHEMA));
            }

            if (property.type() == null) {
                results.add(new VerificationResultR(PROPERTY, PROPERTY_TYPE_MUST_BE_SET, WARNING, Cause.SCHEMA));
            }
        }

    }

    @Override
    protected void checkVirtualCube(MappingVirtualCube virtCube) {
        super.checkVirtualCube(virtCube);
        if (virtCube != null) {
            if (isEmpty(virtCube.name())) {
                results.add(new VerificationResultR(VIRTUAL_CUBE, VIRTUAL_CUBE_NAME_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));

            }
            if (virtCube.virtualCubeDimensions() == null || virtCube.virtualCubeDimensions().isEmpty()) {
                String msg = String.format(VIRTUAL_CUBE_MUST_CONTAIN_DIMENSIONS, orNotSet(virtCube.name()));
                results.add(new VerificationResultR(VIRTUAL_CUBE,
                    msg, ERROR, Cause.SCHEMA));
            }
            if (virtCube.virtualCubeMeasures() == null || virtCube.virtualCubeMeasures().isEmpty()) {
                String msg = String.format(VIRTUAL_CUBE_MUST_CONTAIN_MEASURES, orNotSet(virtCube.name()));
                results.add(new VerificationResultR(VIRTUAL_CUBE, msg, ERROR, Cause.SCHEMA));
            }
        }
    }

    @Override
    protected void checkCalculatedMember(MappingCalculatedMember calculatedMember) {
        super.checkCalculatedMember(calculatedMember);
        if (calculatedMember != null) {
            if (isEmpty(calculatedMember.name())) {
                results.add(new VerificationResultR(CALCULATED_MEMBER,
                    CALCULATED_MEMBER_NAME_MUST_BE_SET, ERROR, Cause.SCHEMA));
            }
            if (isEmpty(calculatedMember.dimension())) {
                String msg = String.format(DIMENSION_MUST_BE_SET_FOR_CALCULATED_MEMBER,
                    orNotSet(calculatedMember.name()));
                results.add(new VerificationResultR(CALCULATED_MEMBER, msg, ERROR, Cause.SCHEMA));
            }
            if (isEmpty(calculatedMember.formula()) && calculatedMember.formulaElement() == null) {
                String msg = String.format(FORMULA_MUST_BE_SET_FOR_CALCULATED_MEMBER,
                    orNotSet(calculatedMember.name()));
                results.add(new VerificationResultR(CALCULATED_MEMBER, msg, ERROR, Cause.SCHEMA));
            }
        }
    }

    @Override
    protected void checkFormula(MappingFormula formula) {
        super.checkFormula(formula);
        if (formula != null && isEmpty(formula.cdata())) {
            results.add(
                new VerificationResultR(FORMULA, FORMULA_MUST_BE_SET, ERROR, Cause.SCHEMA));
        }
    }

    /**
     * Validates a column, and returns an error message if it is invalid.
     *
     * @param column          Column
     * @param fieldName       Field name
     * @param level           Level
     * @param cube            Cube
     * @param parentHierarchy Hierarchy
     */
    @Override
    protected void checkColumn(
        String column, String fieldName, org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingLevel level,
        MappingCube cube, MappingHierarchy parentHierarchy
    ) {
        super.checkColumn(column, fieldName, level, cube, parentHierarchy);
        if (!isEmpty(column)) {

            // specified table for level's column
            String table = level.table();
            checkColumnJoin(table, parentHierarchy);
            checkColumnTable(table, parentHierarchy);
            checkColumnView(table, parentHierarchy);

            if (isEmpty(table)) {
                if (parentHierarchy != null && parentHierarchy.relation() instanceof MappingJoin join) {
                    // relation is join, table should be specified
                    results.add(new VerificationResultR(LEVEL, TABLE_MUST_BE_SET, ERROR,
                        Cause.DATABASE));

                    checkJoin(join);
                }
            } else {
                // if using Joins then gets the table name for doesColumnExist
                // validation.
                if (parentHierarchy != null && parentHierarchy.relation() instanceof MappingJoin join) {
                    checkJoin(join);
                }
            }
        }
    }

    @Override
    protected void checkNamedSet(MappingNamedSet namedSet) {
        super.checkNamedSet(namedSet);
        if (namedSet != null) {
            if (isEmpty(namedSet.name())) {
                results.add(new VerificationResultR(NAMED_SET, NAMED_SET_NAME_MUST_BE_SET, ERROR,
                    Cause.SCHEMA));
            }
            if (isEmpty(namedSet.formula()) && namedSet.formulaElement() == null) {
                results.add(new VerificationResultR(NAMED_SET, NAMED_SET_FORMULA_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));
            }
        }
    }

    /**
     * @return @deprecated
     */
    @Deprecated(since="new version", forRemoval=true)
    @Override
    protected void checkUserDefinedFunction(MappingUserDefinedFunction udf) {
        super.checkUserDefinedFunction(udf);
        if (udf != null) {
            if (isEmpty(udf.name())) {
                results.add(new VerificationResultR(USER_DEFINED_FUNCTION,
                    USER_DEFINED_FUNCTION_NAME_MUST_BE_SET, ERROR, Cause.SCHEMA));
            }
            if (isEmpty(udf.className()) && udf.script() == null) {
                String msg = String.format(EITHER_A_CLASS_NAME_OR_A_SCRIPT_ARE_REQUIRED, orNotSet(udf.name()));
                results.add(new VerificationResultR(USER_DEFINED_FUNCTION, msg, ERROR, Cause.SCHEMA));
            }
        }
    }

    // were add from mondrian.xml
    @Override
    protected void checkCubeUsage(MappingCubeUsage cubeUsage) {
        super.checkCubeUsage(cubeUsage);
        if (cubeUsage != null && cubeUsage.cubeName() == null) {
            results.add(new VerificationResultR(CUBE_USAGE, CUBE_USAGE_CUBE_NAME_MUST_BE_SET,
                ERROR, Cause.SCHEMA));
        }
    }

    @Override
    protected void checkClosure(MappingClosure closure) {
        super.checkClosure(closure);
        if (closure != null) {
            if (isEmpty(closure.parentColumn())) {
                results.add(new VerificationResultR(CLOSURE, CLOSURE_PARENT_COLUMN_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));
            }
            if (isEmpty(closure.childColumn())) {
                results.add(new VerificationResultR(CLOSURE, CLOSURE_CHILD_COLUMN_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));
            }
            if (closure.table() == null) {
                results.add(new VerificationResultR(CLOSURE, CLOSURE_TABLE_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));
            }
        }
    }

    @Override
    protected void checkCalculatedMemberProperty(MappingCalculatedMemberProperty calculatedMemberProperty) {
        super.checkCalculatedMemberProperty(calculatedMemberProperty);
        if (calculatedMemberProperty != null && isEmpty(calculatedMemberProperty.name())) {
            results.add(new VerificationResultR(CALCULATED_MEMBER_PROPERTY, CALCULATED_MEMBER_PROPERTY_NAME_MUST_BE_SET,
                ERROR, Cause.SCHEMA));
        }
    }

    @Override
    protected void checkView(MappingView view) {
        super.checkView(view);
        if (view != null && isEmpty(view.alias())) {
            results.add(new VerificationResultR(VIEW, VIEW_ALIAS_MUST_BE_SET,
                ERROR, Cause.SCHEMA));
        }
    }

    @Override
    protected void checkSQL(MappingSQL sql) {
        super.checkSQL(sql);
        if (sql != null && isEmpty(sql.dialect())) {
            results.add(new VerificationResultR(SQL, SQL_DIALECT_MUST_BE_SET,
                ERROR, Cause.SCHEMA));
        }
    }

    @Override
    protected void checkHint(MappingHint hint) {
        super.checkHint(hint);
        if (hint != null && isEmpty(hint.type())) {
            results.add(new VerificationResultR(HINT, HINT_TYPE_MUST_BE_SET,
                ERROR, Cause.SCHEMA));
        }
    }

    @Override
    protected void checkInlineTable(MappingInlineTable inlineTable) {
        super.checkInlineTable(inlineTable);
        if (inlineTable != null) {
            if (inlineTable.columnDefs() == null) {
                results.add(new VerificationResultR(INLINE_TABLE, INLINE_TABLE_COLUMN_DEFS_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));
            }
            if (inlineTable.rows() == null) {
                results.add(new VerificationResultR(INLINE_TABLE, INLINE_TABLE_ROWS_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));
            }
        }
    }

    @Override
    protected void checkColumnDef(MappingColumnDef columnDef) {
        super.checkColumnDef(columnDef);
        if (columnDef != null) {
            if (isEmpty(columnDef.name())) {
                results.add(new VerificationResultR(COLUMN_DEF, COLUMN_DEF_NAME_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));
            }
            if (columnDef.type() == null) {
                results.add(new VerificationResultR(COLUMN_DEF, COLUMN_DEF_TYPE_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));
            }
        }
    }

    @Override
    protected void checkValue(MappingValue value) {
        super.checkValue(value);
        if (value != null && isEmpty(value.column())) {
            results.add(new VerificationResultR(VALUE, VALUE_COLUMN_MUST_BE_SET,
                ERROR, Cause.SCHEMA));
        }
    }

    @Override
    protected void checkAggTable(MappingAggTable aggTable) {
        super.checkAggTable(aggTable);
        if (aggTable != null && aggTable.aggFactCount() == null) {
            results.add(new VerificationResultR(AGG_TABLE, AGG_TABLE_AGG_FACT_COUNT_MUST_BE_SET,
                ERROR, Cause.SCHEMA));
        }
    }

    @Override
    protected void checkAggName(MappingAggName aggName) {
        super.checkAggName(aggName);
        if (aggName != null && isEmpty(aggName.name())) {
            results.add(new VerificationResultR(AGG_NAME, AGG_NAME_NAME_MUST_BE_SET,
                ERROR, Cause.SCHEMA));
        }
    }

    @Override
    protected void checkAggPattern(MappingAggPattern aggPattern) {
        super.checkAggPattern(aggPattern);
        if (aggPattern != null && isEmpty(aggPattern.pattern())) {
            results.add(new VerificationResultR(AGG_PATTERN, AGG_PATTERN_PATTERN_MUST_BE_SET,
                ERROR, Cause.SCHEMA));
        }
    }

    @Override
    protected void checkAggColumnName(MappingAggColumnName aggColumnName) {
        super.checkAggColumnName(aggColumnName);
        if (aggColumnName != null && isEmpty(aggColumnName.column())) {
            results.add(new VerificationResultR(AGG_COLUMN_NAME, AGG_COLUMN_NAME_COLUMN_MUST_BE_SET,
                ERROR, Cause.SCHEMA));
        }
    }

    @Override
    protected void checkAggMeasureFactCount(MappingAggMeasureFactCount aggMeasureFactCount) {
        super.checkAggMeasureFactCount(aggMeasureFactCount);
        if (aggMeasureFactCount != null && isEmpty(aggMeasureFactCount.factColumn())) {
            results.add(new VerificationResultR(AGG_MEASURE_FACT_COUNT, AGG_MEASURE_FACT_COUNT_FACT_COLUMN_MUST_BE_SET,
                ERROR, Cause.SCHEMA));
        }
    }

    @Override
    protected void checkAggForeignKey(MappingAggForeignKey aggForeignKey) {
        super.checkAggForeignKey(aggForeignKey);
        if (aggForeignKey != null) {
            if (isEmpty(aggForeignKey.factColumn())) {
                results.add(new VerificationResultR(AGG_FOREIGN_KEY, AGG_FOREIGN_KEY_FACT_COLUMN_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));
            }
            if (isEmpty(aggForeignKey.aggColumn())) {
                results.add(new VerificationResultR(AGG_FOREIGN_KEY, AGG_FOREIGN_KEY_AGG_COLUMN_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));
            }
        }
    }

    @Override
    protected void checkAggLevel(MappingAggLevel aggLevel) {
        super.checkAggLevel(aggLevel);
        if (aggLevel != null) {
            if (aggLevel.name() == null) {
                results.add(new VerificationResultR(AGG_LEVEL, AGG_LEVEL_NAME_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));
            }
            if (aggLevel.column() == null) {
                results.add(new VerificationResultR(AGG_LEVEL, AGG_LEVEL_COLUMN_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));
            }
        }
    }

    @Override
    protected void checkAggMeasure(MappingAggMeasure aggMeasure) {
        super.checkAggMeasure(aggMeasure);
        if (aggMeasure != null) {
            if (aggMeasure.column() == null) {
                results.add(new VerificationResultR(AGG_MEASURE, AGG_MEASURE_COLUMN_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));
            }
            if (aggMeasure.name() == null) {
                results.add(new VerificationResultR(AGG_MEASURE, AGG_MEASURE_NAME_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));
            }
        }
    }

    @Override
    protected void checkColumn(MappingColumn column) {
        super.checkColumn(column);
        if (column != null && column.name() == null) {
            results.add(new VerificationResultR(COLUMN, COLUMN_NAME_MUST_BE_SET,
                ERROR, Cause.SCHEMA));
        }
    }

    @Override
    protected void checkRole(MappingRole role) {
        super.checkRole(role);
        if (role != null && role.name() == null) {
            results.add(new VerificationResultR(ROLE, ROLE_NAME_MUST_BE_SET,
                ERROR, Cause.SCHEMA));
        }
    }

    @Override
    protected void checkSchemaGrant(MappingSchemaGrant schemaGrant) {
        super.checkSchemaGrant(schemaGrant);
        if (schemaGrant != null && schemaGrant.access() == null) {
            results.add(new VerificationResultR(SCHEMA_GRANT, SCHEMA_GRANT_ACCESS_MUST_BE_SET,
                ERROR, Cause.SCHEMA));
        }
    }

    @Override
    protected void checkCubeGrant(MappingCubeGrant cubeGrant) {
        super.checkCubeGrant(cubeGrant);
        if (cubeGrant != null && isEmpty(cubeGrant.cube())) {
            results.add(new VerificationResultR(CUBE_GRANT, CUBE_GRANT_CUBE_MUST_BE_SET,
                ERROR, Cause.SCHEMA));
        }
    }

    @Override
    protected void checkDimensionGrant(MappingDimensionGrant dimensionGrant) {
        super.checkDimensionGrant(dimensionGrant);
        if (dimensionGrant != null && isEmpty(dimensionGrant.dimension())) {
            results.add(new VerificationResultR(DIMENSION_GRANT, DIMENSION_GRANT_DIMENSION_MUST_BE_SET,
                ERROR, Cause.SCHEMA));
        }
    }

    @Override
    protected void checkHierarchyGrant(MappingHierarchyGrant hierarchyGrant) {
        super.checkHierarchyGrant(hierarchyGrant);
        if (hierarchyGrant != null && isEmpty(hierarchyGrant.hierarchy())) {
            results.add(new VerificationResultR(HIERARCHY_GRANT, HIERARCHY_GRANT_HIERARCHY_MUST_BE_SET,
                ERROR, Cause.SCHEMA));
        }
    }

    @Override
    protected void checkMemberGrant(MappingMemberGrant memberGrant) {
        super.checkMemberGrant(memberGrant);
        if (memberGrant != null) {
            if (isEmpty(memberGrant.member())) {
                results.add(new VerificationResultR(MEMBER_GRANT, MEMBER_GRANT_MEMBER_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));
            }
            if (memberGrant.access() == null) {
                results.add(new VerificationResultR(MEMBER_GRANT, MEMBER_GRANT_ACCESS_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));
            }
        }
    }

    @Override
    protected void checkUnion(MappingUnion union) {
        super.checkUnion(union);
        if (union != null && union.roleUsages() == null) {
            results.add(new VerificationResultR(UNION, UNION_ROLE_USAGE_MUST_BE_SET,
                ERROR, Cause.SCHEMA));
        }
    }

    @Override
    protected void checkRoleUsage(MappingRoleUsage roleUsage) {
        super.checkRoleUsage(roleUsage);
        if (roleUsage != null && isEmpty(roleUsage.roleName())) {
            results.add(new VerificationResultR(ROLE_USAGE, ROLE_USAGE_ROLE_NAME_MUST_BE_SET,
                ERROR, Cause.SCHEMA));
        }
    }

    @Override
    protected void checkParameter(MappingParameter parameter) {
        super.checkParameter(parameter);
        if (parameter != null) {
            if (isEmpty(parameter.name())) {
                results.add(new VerificationResultR(PARAMETER, PARAMETER_NAME_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));
            }
            if (parameter.type() == null) {
                results.add(new VerificationResultR(PARAMETER, PARAMETER_TYPE_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));
            }
        }
    }

    @Override
    protected void checkAnnotation(MappingAnnotation annotation) {
        if (annotation != null) {
            super.checkAnnotation(annotation);
            if (isEmpty(annotation.name())) {
                results.add(new VerificationResultR(ANNOTATION, ANNOTATION_NAME_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));
            }
        }
    }

    @Override
    protected void checkDrillThroughAttribute(MappingDrillThroughAttribute drillThroughAttribute) {

        super.checkDrillThroughAttribute(drillThroughAttribute);
        if (drillThroughAttribute != null && isEmpty(drillThroughAttribute.dimension())) {
            results.add(new VerificationResultR(DRILL_THROUGH_ATTRIBUTE, DRILL_THROUGH_ATTRIBUTE_NAME_MUST_BE_SET,
                ERROR, Cause.SCHEMA));
        }
    }

    @Override
    protected void checkDrillThroughMeasure(MappingDrillThroughMeasure drillThroughMeasure) {
        super.checkDrillThroughMeasure(drillThroughMeasure);
        if (drillThroughMeasure != null && isEmpty(drillThroughMeasure.name())) {
            results.add(new VerificationResultR(DRILL_THROUGH_MEASURE, DRILL_THROUGH_MEASURE_NAME_MUST_BE_SET,
                ERROR, Cause.SCHEMA));
        }
    }

    @Override
    protected void checkAction(MappingAction action) {
        super.checkAction(action);
        if (action != null && isEmpty(action.name())) {
            results.add(new VerificationResultR(ACTION, ACTION_NAME_MUST_BE_SET,
                ERROR, Cause.SCHEMA));
        }
    }

    @Override
    protected void checkWritebackAttribute(MappingWritebackAttribute writebackAttribute) {
        super.checkWritebackAttribute(writebackAttribute);
        if (writebackAttribute != null) {
            if (isEmpty(writebackAttribute.dimension())) {
                results.add(new VerificationResultR(WRITEBACK_ATTRIBUTE, WRITEBACK_ATTRIBUTE_DIMENSION_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));
            }
            if (isEmpty(writebackAttribute.column())) {
                results.add(new VerificationResultR(WRITEBACK_ATTRIBUTE, WRITEBACK_ATTRIBUTE_COLUMN_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));
            }
        }
    }

    @Override
    protected void checkWritebackMeasure(MappingWritebackMeasure writebackMeasure) {
        super.checkWritebackMeasure(writebackMeasure);
        if (writebackMeasure != null) {
            if (isEmpty(writebackMeasure.name())) {
                results.add(new VerificationResultR(WRITEBACK_MEASURE, WRITEBACK_MEASURE_NAME_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));
            }
            if (isEmpty(writebackMeasure.column())) {
                results.add(new VerificationResultR(WRITEBACK_MEASURE, WRITEBACK_MEASURE_COLUMN_MUST_BE_SET,
                    ERROR, Cause.SCHEMA));
            }
        }
    }

    @Override
    protected void checkWritebackTable(MappingWritebackTable writebackTable) {
        super.checkWritebackTable(writebackTable);
        if (writebackTable != null && isEmpty(writebackTable.name())) {
            results.add(new VerificationResultR(WRITEBACK_TABLE, WRITEBACK_TABLE_NAME_MUST_BE_SET,
                ERROR, Cause.SCHEMA));
        }
    }

    private void checkHierarchyJoin(MappingHierarchy hierarchy, MappingPrivateDimension cubeDimension) {
        if (hierarchy.relation() instanceof MappingJoin) {
            if (isEmpty(hierarchy.primaryKeyTable())) {
                if (isEmpty(hierarchy.primaryKey())) {
                    String msg = String.format(PRIMARY_KEY_TABLE_AND_PRIMARY_KEY_MUST_BE_SET_FOR_JOIN,
                        orNotSet(cubeDimension.name()));
                    results.add(new VerificationResultR(
                        HIERARCHY, msg, ERROR, Cause.SCHEMA));

                } else {
                    String msg = String.format(PRIMARY_KEY_TABLE_MUST_BE_SET_FOR_JOIN,
                        orNotSet(cubeDimension.name()));
                    results.add(new VerificationResultR(HIERARCHY, msg, ERROR, Cause.SCHEMA));
                }
            }
            if (isEmpty(hierarchy.primaryKey())) {
                String msg = String.format(PRIMARY_KEY_MUST_BE_SET_FOR_JOIN, orNotSet(cubeDimension.name()));
                results.add(new VerificationResultR(HIERARCHY, msg, ERROR, Cause.SCHEMA));
            }
        }
    }

    private void checkHierarchyLevels(MappingHierarchy hierarchy, MappingPrivateDimension cubeDimension) {
        List<? extends org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingLevel> levels = hierarchy.levels();
        if (levels == null || levels.isEmpty()) {
            String msg = String.format(LEVEL_MUST_BE_SET_FOR_HIERARCHY, orNotSet(cubeDimension.name()));
            results.add(new VerificationResultR(HIERARCHY,
                msg, ERROR, Cause.SCHEMA));
        }
    }

    private void checkHierarchyTable(MappingHierarchy hierarchy, MappingPrivateDimension cubeDimension) {
        if (hierarchy.relation() instanceof MappingTable table) {
            if (!isEmpty(hierarchy.primaryKeyTable())) {
                String msg = String.format(HIERARCHY_TABLE_FIELD_MUST_BE_EMPTY, orNotSet(cubeDimension.name()));
                results.add(new VerificationResultR(HIERARCHY, msg, ERROR, Cause.SCHEMA));
            }
            checkTable(table);
        }
    }

    private void checkHierarchyPrimaryKeyTable(MappingHierarchy hierarchy, MappingPrivateDimension cubeDimension) {
        String primaryKeyTable = hierarchy.primaryKeyTable();
        if (!isEmpty(primaryKeyTable) && (hierarchy.relation() instanceof MappingJoin join)) {
            TreeSet<String> joinTables = new TreeSet<>();
            SchemaExplorer.getTableNamesForJoin(hierarchy.relation(), joinTables);
            if (!joinTables.contains(primaryKeyTable)) {
                String msg = String.format(HIERARCHY_TABLE_VALUE_DOES_NOT_CORRESPOND_TO_ANY_JOIN,
                    orNotSet(cubeDimension.name()));
                results.add(new VerificationResultR(HIERARCHY, msg, ERROR, Cause.DATABASE));
            }
            checkJoin(join);
        }

        if (!isEmpty(primaryKeyTable) && (hierarchy.relation() instanceof MappingTable theTable)) {
            String compareTo = (theTable.alias() != null && theTable.alias()
                .trim()
                .length() > 0) ? theTable.alias() : theTable.name();
            if (!primaryKeyTable.equals(compareTo)) {
                String msg = String.format(HIERARCHY_TABLE_VALUE_DOES_NOT_CORRESPOND_TO_HIERARCHY_RELATION,
                    orNotSet(cubeDimension.name()));
                results.add(new VerificationResultR(HIERARCHY,
                    msg, ERROR,
                    Cause.DATABASE));
            }
            checkTable(theTable);
        }
    }

    private void checkLevelType(org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingLevel level, MappingPrivateDimension parentDimension) {
        if (level.levelType() != null && parentDimension != null) {
            // Empty leveltype is treated as default value of "Regular""
            // which is ok with standard/time dimension.
            if ((parentDimension.type() == null || parentDimension.type()
                .equals(DimensionTypeEnum.STANDARD_DIMENSION)) && level.levelType() != null
                && (!level.levelType()
                .equals(LevelTypeEnum.REGULAR))) {
                // If dimension type is 'standard' then leveltype
                // should be 'regular'
                String msg = String.format(LEVEL_LEVEL_TYPE_S_CAN_ONLY_BE_USED_WITH_A_TIME_DIMENSION,
                    level.levelType()
                        .getValue());
                results.add(new VerificationResultR(LEVEL, msg, ERROR, Cause.SCHEMA));

            } else if (parentDimension.type() != null && (parentDimension.type()
                .equals(DimensionTypeEnum.TIME_DIMENSION)) && level.levelType() != null && (level.levelType()
                .equals(LevelTypeEnum.REGULAR))) {
                // If dimension type is 'time' then leveltype value
                // could be 'timeyears', 'timedays' etc'
                String msg = String.format(LEVEL_TYPE_S_CAN_ONLY_BE_USED_WITH_A_STANDARD_DIMENSION,
                    level.levelType()
                        .getValue());
                results.add(new VerificationResultR(LEVEL, msg, ERROR, Cause.SCHEMA));
            }
        }
        if (level.type() != null) {
            String msg = String.format(LEVEL_TYPE_MUST_BE_SET,
                level.name() == null ? NOT_SET : level.name());
            results.add(new VerificationResultR(LEVEL, msg, WARNING, Cause.SCHEMA));
        }
    }

    private void checkLevelColumn(org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingLevel level, MappingHierarchy hierarchy,
                                  MappingCube cube) {
        String column = level.column();
        if (isEmpty(column)) {
            if (level.properties() == null || level.properties().isEmpty()) {
                String msg = String.format(LEVEL_COLUMN_MUST_BE_SET, orNotSet(hierarchy.name()));
                results.add(new VerificationResultR(LEVEL, msg, ERROR, Cause.SCHEMA));
            } else {
                level.properties()
                    .forEach(p -> checkProperty(p, level, hierarchy, cube));
            }
        } else {
            // Enforces validation for all column types against invalid
            // value.
            try {
                for (String element : DEF_LEVEL) {
                    Method method = level.getClass()
                        .getDeclaredMethod(element);
                    column = (String) method.invoke(level);
                    checkColumn(column, element, level, cube, hierarchy);
                }
            } catch (Exception ex) {
                LOGGER.error("Validation", ex);
            }
        }
    }

    private void checkColumnJoin(String table, MappingHierarchy parentHierarchy) {
        // If table has been changed in join then sets the table value
        // to null to cause "tableMustBeSet" validation fail.
        if (!isEmpty(table) && parentHierarchy != null
            && parentHierarchy.relation() instanceof MappingJoin) {
            TreeSet<String> joinTables = new TreeSet<>();
            SchemaExplorer.getTableNamesForJoin(parentHierarchy.relation(), joinTables);
            if (!joinTables.contains(table)) {

                results.add(new VerificationResultR(LEVEL,
                    TABLE_VALUE_DOES_NOT_CORRESPOND_TO_ANY_JOIN, ERROR, Cause.SCHEMA));
            }
        }
    }

    private void checkColumnTable(String table, MappingHierarchy parentHierarchy) {
        if (!isEmpty(table) && parentHierarchy != null
            && parentHierarchy.relation() instanceof MappingTable parentTable) {
            MappingTable theTable = parentTable;
            String compareTo = (theTable.alias() != null && theTable.alias()
                .trim()
                .length() > 0) ? theTable.alias() : theTable.name();
            if (!table.equals(compareTo)) {
                results.add(new VerificationResultR(LEVEL,
                    TABLE_VALUE_DOES_NOT_CORRESPOND_TO_HIERARCHY_RELATION, ERROR, Cause.SCHEMA));
            }
            checkTable(parentTable);
        }
    }

    private void checkColumnView(String table, MappingHierarchy parentHierarchy) {
        if (!isEmpty(table) && parentHierarchy != null
            && parentHierarchy.relation() instanceof MappingView) {
            results.add(new VerificationResultR(LEVEL,
                TABLE_FOR_COLUMN_CANNOT_BE_SET_IN_VIEW, ERROR, Cause.SCHEMA));
        }
    }
}
