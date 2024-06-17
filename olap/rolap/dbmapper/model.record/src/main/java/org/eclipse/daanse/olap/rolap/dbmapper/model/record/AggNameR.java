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
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggForeignKey;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggLevel;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggMeasure;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggMeasureFactCount;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAggName;

public record AggNameR(String name,
                       MappingAggColumnName aggFactCount,
                       List<MappingAggMeasure> aggMeasures,
                       List<MappingAggColumnName> aggIgnoreColumns,
                       List<MappingAggForeignKey> aggForeignKeys,
                       List<MappingAggLevel> aggLevels,
                       Boolean ignorecase,
                       List<MappingAggMeasureFactCount> measuresFactCounts,
                       String approxRowCount)
        implements MappingAggName {




	public  AggNameR(String name,
            MappingAggColumnName aggFactCount,
            List<MappingAggMeasure> aggMeasures,
            List<MappingAggColumnName> aggIgnoreColumns,
            List<MappingAggForeignKey> aggForeignKeys,
            List<MappingAggLevel> aggLevels,
            Boolean ignorecase,
            List<MappingAggMeasureFactCount> measuresFactCounts,
            String approxRowCount)
 {
	this.name = name;
	this.aggFactCount = aggFactCount;
	this.aggMeasures = aggMeasures == null ? List.of() : aggMeasures;
	this.aggIgnoreColumns = aggIgnoreColumns == null ? List.of() : aggIgnoreColumns;
	this.aggForeignKeys = aggForeignKeys == null ? List.of() : aggForeignKeys;
	this.aggLevels = aggLevels == null ? List.of() : aggLevels;
	this.ignorecase = ignorecase == null ? Boolean.TRUE : ignorecase;
	this.measuresFactCounts = measuresFactCounts == null ? List.of() : measuresFactCounts;
	this.approxRowCount = approxRowCount;

 }

    public String getName() {
        return name;
    }

    public MappingAggColumnName getAggFactCount() {
        return aggFactCount;
    }

    public List<MappingAggMeasure> getAggMeasures() {
        return aggMeasures;
    }

    public List<MappingAggColumnName> getAggIgnoreColumns() {
        return aggIgnoreColumns;
    }

    public List<MappingAggForeignKey> getAggForeignKeys() {
        return aggForeignKeys;
    }

    public List<MappingAggLevel> getAggLevels() {
        return aggLevels;
    }

    public Boolean getIgnorecase() {
        return ignorecase;
    }

    public List<MappingAggMeasureFactCount> getMeasuresFactCounts() {
        return measuresFactCounts;
    }

    public String getApproxRowCount() {
        return approxRowCount;
    }
}
