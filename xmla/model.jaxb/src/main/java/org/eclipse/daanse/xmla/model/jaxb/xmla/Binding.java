
package org.eclipse.daanse.xmla.model.jaxb.xmla;

import org.eclipse.daanse.xmla.model.jaxb.engine200_200.ExpressionBinding;
import org.eclipse.daanse.xmla.model.jaxb.engine200_200.RowNumberBinding;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Binding complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Binding"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Binding")
@XmlSeeAlso({ ColumnBinding.class, RowBinding.class, DataSourceViewBinding.class, AttributeBinding.class,
    UserDefinedGroupBinding.class, MeasureBinding.class, CubeAttributeBinding.class, DimensionBinding.class,
    CubeDimensionBinding.class, MeasureGroupBinding.class, MeasureGroupDimensionBinding.class, TimeBinding.class,
    TimeAttributeBinding.class, InheritedBinding.class, CalculatedMeasureBinding.class, RowNumberBinding.class,
    ExpressionBinding.class })
public abstract class Binding {

}
