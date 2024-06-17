package org.eclipse.daanse.olap.rolap.dbmapper.model.record;

import java.util.List;

import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingAnnotation;
import org.eclipse.daanse.olap.rolap.dbmapper.model.api.MappingCubeDimension;

public record CubeDimensionR(
                             String name,
                             String description,
                             List<MappingAnnotation> annotations,
                             String caption,
                             Boolean visible,
                             String foreignKey
                            ) implements MappingCubeDimension {



	public  CubeDimensionR(
            String name,
            String description,
            List<MappingAnnotation> annotations,
            String caption,
            Boolean visible,
            String foreignKey
           )
{
	this.name = name;
	this.description = description;
	this.annotations = annotations == null ? List.of() : annotations;
	this.caption = caption;
	this.visible = visible == null ? Boolean.TRUE : visible;
	this.foreignKey = foreignKey;

}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<MappingAnnotation> getAnnotations() {
        return annotations;
    }

    public String getCaption() {
        return caption;
    }

    public Boolean getVisible() {
        return visible;
    }

    public String getForeignKey() {
        return foreignKey;
    }
}
