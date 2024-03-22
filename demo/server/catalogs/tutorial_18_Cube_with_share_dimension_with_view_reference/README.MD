# Documentation
### CatalogName : tutorial_18_Cube_with_share_dimension_with_view_reference
### Schema Cube_with_share_dimension_with hierarchy_with_view_reference : 

  
		A basic OLAP schema with a level with reference with view
    Share dimension Dimension1 is defined in a <Dimension> element inside <Schema> element.
    Hierarchy is defined in a <Hierarchy> element inside <Dimension> element.
    Hierarchy have reference in view element
    Cube dimension Dimension1 have link on share dimension Dimension1 as source.


		Data cubes (<Cube>) are defined in an OLAP schema (<Schema>). Within the schema the name of each data cube must be unique.
		This example schema contains one cube named "Cube1".

		A cube is based on a fact table (<Table>) which refers to a database table containing one or more measurements to be aggregated (and optionally further columns defining factual dimensions).
		In this case the database table representing the fact table is named "Fact" in the database, which is adressed in the name attribute within the <Table> tag.

		Each measurement of the cube is defined in a separate <Measure> element.
		The measurement in this example cube is named "Measure" (name attribute). It corresponds to the "VALUE" column (column attribute) in the database table "Fact" and is aggregated by summation (aggregator attribute).
		Level is defined in <Level> element.
		Property is defined in <Property> element inside <Level> element. Property we can see in cell tooltip in excel

		
  
### Public Dimensions:

    Dimension1

##### Dimension "Dimension1":

Hierarchies:

    Hierarchy1

##### Hierarchy Hierarchy1:

Tables: "select * from HT"

Levels: "Level1"

###### Level "Level1" :

    column(s): KEY

---
### Cubes :

    Cube1

---
#### Cube "Cube1":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "Dimension1 -> Dimension1":

### Cube "Cube1" diagram:

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
c0["Cube1"]{
M Measure1 ""
D Dimension1 ""
}
d00["Dimension1"] {
H Hierarchy1 ""
}
h000["Hierarchy1"] {
L Level1 ""
}
"c0" ||--|| "d00" : "DIM_KEY-KEY"
"d00" ||--|| "h000" : "KEY-KEY"
```
---
### Cube Matrix for Cube_with_share_dimension_with hierarchy_with_view_reference:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube Cube1: [0.0500, 0.0000]
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
int DIM_KEY
int VALUE
}
HT{
int KEY
string NAME
int VALUE
}

"Fact" ||--o{ "HT_VIEW" : "DIM_KEY-KEY"
```
---
