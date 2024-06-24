
/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   SmartCity Jena, Stefan Bischof - initial
 *
 */
package org.eclipse.daanse.olap.rolap.dbmapper.model.jaxb;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingHierarchy;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingLevel;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingMemberReaderParameter;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingQuery;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Hierarchy", propOrder = { "relation", "levels", "memberReaderParameters" })
public class HierarchyImpl extends AbstractMainElement implements MappingHierarchy {

	@XmlElement(name = "Level", required = true, type = LevelImpl.class)
	protected List<MappingLevel> levels;
	@XmlElement(name = "MemberReaderParameter", type = MemberReaderParameterImpl.class)
	protected List<MappingMemberReaderParameter> memberReaderParameters;

	@XmlAttribute(name = "hasAll", required = true)
	protected boolean hasAll;
	@XmlAttribute(name = "allMemberName")
	protected String allMemberName;
	@XmlAttribute(name = "allMemberCaption")
	protected String allMemberCaption;
	@XmlAttribute(name = "allLevelName")
	protected String allLevelName;
	@XmlAttribute(name = "primaryKey")
	protected String primaryKey;
	@XmlAttribute(name = "primaryKeyTable")
	protected String primaryKeyTable;
	@XmlAttribute(name = "defaultMember")
	protected String defaultMember;
	@XmlAttribute(name = "memberReaderClass")
	protected String memberReaderClass;

	@XmlAttribute(name = "uniqueKeyLevelName")
	protected String uniqueKeyLevelName;
	@XmlAttribute(name = "visible")
	private boolean visible = true;
	@XmlAttribute(name = "displayFolder")
	private String displayFolder;
	@XmlAttribute(name = "origin")
	private String origin;
	@XmlElements({ @XmlElement(name = "Table", type = TableImpl.class),
			@XmlElement(name = "View", type = ViewImpl.class), @XmlElement(name = "Join", type = JoinQueryImpl.class),
			@XmlElement(name = "InlineTable", type = InlineTableImpl.class) })
	protected MappingQuery relation;

	@Override
	public List<MappingLevel> levels() {
		if (levels == null) {
			levels = new ArrayList<>();
		}
		return this.levels;
	}

	@Override
	public List<MappingMemberReaderParameter> memberReaderParameters() {
		if (memberReaderParameters == null) {
			memberReaderParameters = new ArrayList<>();
		}
		return this.memberReaderParameters;
	}

	@Override
	public Boolean hasAll() {
		return hasAll;
	}

	public void setHasAll(Boolean value) {
		this.hasAll = value;
	}

	@Override
	public String allMemberName() {
		return allMemberName;
	}

	public void setAllMemberName(String value) {
		this.allMemberName = value;
	}

	@Override
	public String allMemberCaption() {
		return allMemberCaption;
	}

	public void setAllMemberCaption(String value) {
		this.allMemberCaption = value;
	}

	@Override
	public String allLevelName() {
		return allLevelName;
	}

	public void setAllLevelName(String value) {
		this.allLevelName = value;
	}

	@Override
	public String primaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String value) {
		this.primaryKey = value;
	}

	@Override
	public String primaryKeyTable() {
		return primaryKeyTable;
	}

	public void setPrimaryKeyTable(String value) {
		this.primaryKeyTable = value;
	}

	@Override
	public String defaultMember() {
		return defaultMember;
	}

	public void setDefaultMember(String value) {
		this.defaultMember = value;
	}

	@Override
	public String memberReaderClass() {
		return memberReaderClass;
	}

	public void setMemberReaderClass(String value) {
		this.memberReaderClass = value;
	}

	@Override
	public String uniqueKeyLevelName() {
		return uniqueKeyLevelName;
	}

	@Override
	public Boolean visible() {
		return visible;
	}

	@Override
	public String displayFolder() {
		return displayFolder;
	}

	@Override
	public MappingQuery relation() {
		return relation;
	}

	@Override
	public String origin() {
		return origin;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public void setDisplayFolder(String displayFolder) {
		this.displayFolder = displayFolder;
	}

	public void setUniqueKeyLevelName(String value) {
		this.uniqueKeyLevelName = value;
	}

	public void setLevels(List<MappingLevel> levels) {
		this.levels = levels;
	}

	public void setRelation(MappingQuery relation) {
		this.relation = relation;
	}

	public void setMemberReaderParameters(List<MappingMemberReaderParameter> memberReaderParameters) {
		this.memberReaderParameters = memberReaderParameters;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

}
