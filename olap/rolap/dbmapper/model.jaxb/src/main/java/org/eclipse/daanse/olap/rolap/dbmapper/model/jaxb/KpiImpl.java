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
package org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingKpi;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingTranslation;

import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class KpiImpl extends AbstractMainElement implements MappingKpi {

    @XmlElement(name = "id")
    protected String id;
    @XmlElement(name = "Translation", type = TranslationImpl.class)
    @XmlElementWrapper(name = "Translations")
    protected List<MappingTranslation> translations;
    @XmlElement(name = "DisplayFolder")
    protected String displayFolder;
    @XmlElement(name = "AssociatedMeasureGroupID")
    protected String associatedMeasureGroupID;
    @XmlElement(name = "Value", required = true)
    protected String value;
    @XmlElement(name = "Goal")
    protected String goal;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "Trend")
    protected String trend;
    @XmlElement(name = "Weight")
    protected String weight;
    @XmlElement(name = "TrendGraphic")
    protected String trendGraphic;
    @XmlElement(name = "StatusGraphic")
    protected String statusGraphic;
    @XmlElement(name = "CurrentTimeMember")
    protected String currentTimeMember;
    @XmlElement(name = "ParentKpiID")
    protected String parentKpiID;

    @Override
    public String id() {
        return id;
    }

    @Override
    public List<MappingTranslation> translations() {
        return translations;
    }

    @Override
    public String displayFolder() {
        return displayFolder;
    }

    @Override
    public String associatedMeasureGroupID() {
        return associatedMeasureGroupID;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public String goal() {
        return goal;
    }

    @Override
    public String status() {
        return status;
    }

    @Override
    public String trend() {
        return trend;
    }

    @Override
    public String weight() {
        return weight;
    }

    @Override
    public String trendGraphic() {
        return trendGraphic;
    }

    @Override
    public String statusGraphic() {
        return statusGraphic;
    }

    @Override
    public String currentTimeMember() {
        return currentTimeMember;
    }

    @Override
    public String parentKpiID() {
        return parentKpiID;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTranslations(List<MappingTranslation> translations) {
        this.translations = translations;
    }

    public void setDisplayFolder(String displayFolder) {
        this.displayFolder = displayFolder;
    }

    public void setAssociatedMeasureGroupID(String associatedMeasureGroupID) {
        this.associatedMeasureGroupID = associatedMeasureGroupID;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTrend(String trend) {
        this.trend = trend;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setTrendGraphic(String trendGraphic) {
        this.trendGraphic = trendGraphic;
    }

    public void setStatusGraphic(String statusGraphic) {
        this.statusGraphic = statusGraphic;
    }

    public void setCurrentTimeMember(String currentTimeMember) {
        this.currentTimeMember = currentTimeMember;
    }

    public void setParentKpiID(String parentKpiID) {
        this.parentKpiID = parentKpiID;
    }
}
