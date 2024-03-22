# Documentation
### CatalogName : tutorial_01-08_2_Cube_with_cub_dimension_with_level_with_property_with_wrong_column_Error
### Schema Minimal_Cube_with_cube_dimension_level_with_property_with_wrong_column : 

    
		A basic OLAP schema with a level with property with wrong column

		Data cubes (<Cube>) are defined in an OLAP schema (<Schema>). Within the schema the name of each data cube must be unique.
		This example schema contains one cube named "Cube".

		A cube is based on a fact table (<Table>) which refers to a database table containing one or more measurements to be aggregated (and optionally further columns defining factual dimensions).
		In this case the database table representing the fact table is named "Fact" in the database, which is adressed in the name attribute within the <Table> tag.

		Each measurement of the cube is defined in a separate <Measure> element.
		The measurement in this example cube is named "Measure" (name attribute). It corresponds to the "VALUE" column (column attribute) in the database table "Fact" and is aggregated by summation (aggregator attribute).
		Level is defined in <Level> element.
		Property is defined in <Property> element inside <Level> element. Property we can see in cell tooltip in excel
    We use wrong column name for test.
		
  
---
### Cubes :

    Cube

---
#### Cube "Cube":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension "Dimension":

Hierarchies:

    HierarchyWithHasAll

##### Hierarchy HierarchyWithHasAll:

Tables: "Fact"

Levels: "Level"

###### Level "Level" :

    column(s): L

### Cube "Cube" diagram:

---

```mermaid
%%{init: {
"theme": "default",
"themeCSS": [
    ".er.entityBox {stroke: black;}",
    ".er.attributeBoxEven {stroke: black;}",
    ".er.attributeBoxOdd {stroke: black;}",
    "[id^=entity-c] .er.entityBox { fill: lightgreen;} ",
    "[id^=entity-d] .er.entityBox { fill: powderblue;} ",
    "[id^=entity-h] .er.entityBox { fill: pink;} "
]
}}%%
erDiagram
c0["Cube"]{
M Measure ""
D Dimension ""
}
d00["Dimension"] {
H HierarchyWithHasAll ""
}
h000["HierarchyWithHasAll"] {
L Level ""
}
"c0" ||--|| "d00" : "KEY"
"d00" ||--|| "h000" : "KEY-L"
```
---
### Cube Matrix for Minimal_Cube_with_cube_dimension_level_with_property_with_wrong_column:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube Cube: [0.0500, 0.0000]
```
---
### Database :
---
```mermaid
---
title: Diagram;
---
erDiagram
Fact{
string KEY
int VALUE
string L
string prop
}

```
---
## Validation result for schema Minimal_Cube_with_cube_dimension_level_with_property_with_wrong_column
## <span style='color: red;'>ERROR</span> : 
|Type|   |
|----|---|
|DATABASE|Property column prop_wrong does not exist in Hierarchy table Fact|
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|DATABASE|Table: Schema must be set|
