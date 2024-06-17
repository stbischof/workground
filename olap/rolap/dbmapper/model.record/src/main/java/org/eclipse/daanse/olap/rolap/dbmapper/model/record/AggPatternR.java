/*
 * Copyright (c) 0 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License .0
 * which is available at https://www.eclipse.org/legal/epl-.0/
 *
 * SPDX-License-Identifier: EPL-.0
 *
 * Contributors:
 *   SmartCity Jena, Stefan Bischof - initial
 *
 */
package org.eclipse.daanse.olap.rolap.dbmapper.model.record;

import java.util.List;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggColumnName;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggExclude;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggForeignKey;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggLevel;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggMeasureFactCount;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggPattern;

public record AggPatternR(String pattern,
                          MappingAggColumnName aggFactCount,
                          List<MappingAggColumnName> aggIgnoreColumns,
                          List<MappingAggForeignKey> aggForeignKeys,
                          List<MappingAggMeasure> aggMeasures,
                          List<MappingAggLevel> aggLevels,
                          List<MappingAggExclude> aggExcludes,
                          Boolean ignorecase,
                          List<MappingAggMeasureFactCount> measuresFactCounts)
        implements MappingAggPattern {



	public  AggPatternR(String pattern,
            MappingAggColumnName aggFactCount,
            List<MappingAggColumnName> aggIgnoreColumns,
            List<MappingAggForeignKey> aggForeignKeys,
			List<MappingAggMeasure> aggMeasures,
            List<MappingAggLevel> aggLevels,
            List<MappingAggExclude> aggExcludes,
            Boolean ignorecase,
            List<MappingAggMeasureFactCount> measuresFactCounts)
 {
	this.pattern = pattern;
	this.aggFactCount = aggFactCount;
	this.aggIgnoreColumns = aggIgnoreColumns == null ? List.of() : aggIgnoreColumns;
	this.aggForeignKeys = aggForeignKeys == null ? List.of() : aggForeignKeys;
	this.aggMeasures = aggMeasures == null ? List.of() : aggMeasures;
	this.aggLevels = aggLevels == null ? List.of() : aggLevels;
	this.aggExcludes = aggExcludes == null ? List.of() : aggExcludes;
	this.ignorecase = ignorecase == null ? Boolean.TRUE : ignorecase;
	this.measuresFactCounts = measuresFactCounts == null ? List.of() : measuresFactCounts;

	}

    public String getPattern() {
        return pattern;
    }

    public MappingAggColumnName getAggFactCount() {
        return aggFactCount;
    }

    public List<MappingAggColumnName> getAggIgnoreColumns() {
        return aggIgnoreColumns;
    }

    public List<MappingAggForeignKey> getAggForeignKeys() {
        return aggForeignKeys;
    }

    public List<MappingAggMeasure> getAggMeasures() {
        return aggMeasures;
    }

    public List<MappingAggLevel> getAggLevels() {
        return aggLevels;
    }

    public List<MappingAggExclude> getAggExcludes() {
        return aggExcludes;
    }

    public Boolean getIgnorecase() {
        return ignorecase;
    }

    public List<MappingAggMeasureFactCount> getMeasuresFactCounts() {
        return measuresFactCounts;
    }
}
