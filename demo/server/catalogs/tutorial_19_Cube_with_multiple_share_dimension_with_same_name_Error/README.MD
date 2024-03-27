# Documentation
### CatalogName : tutorial_19_Cube_with_multiple_share_dimension_with_same_name_Error
### Schema Minimal_Cube_with_two_dimensions_with_same_name : 

    
		A basic OLAP schema with a level with reference with view
    Share dimension Dimension1 is defined in a <Dimension> element inside <Schema> element.
    Hierarchy is defined in a <Hierarchy> element inside <Dimension> element.
    Hierarchy have reference in view element
    Cube have two dimensions with one name Dimension1 for test case which have link on share dimension D1 and D2 as source.


		Data cubes (<Cube>) are defined in an OLAP schema (<Schema>). Within the schema the name of each data cube must be unique.
		This example schema contains one cube named "CubeTwoDimensions".

		A cube is based on a fact table (<Table>) which refers to a database table containing one or more measurements to be aggregated (and optionally further columns defining factual dimensions).
		In this case the database table representing the fact table is named "Fact" in the database, which is adressed in the name attribute within the <Table> tag.

		Each measurement of the cube is defined in a separate <Measure> element.
		The measurement in this example cube is named "Measure" (name attribute). It corresponds to the "VALUE" column (column attribute) in the database table "Fact" and is aggregated by summation (aggregator attribute).
		Level is defined in <Level> element.

		
  
### Public Dimensions:

    D1, D2, D1

##### Dimension "D1":

Hierarchies:

    D1H1

##### Hierarchy D1H1:

Tables: "DIM_1"

Levels: "D1H1L1"

###### Level "D1H1L1" :

    column(s): KEY

##### Dimension "D2":

Hierarchies:

    D2H1

##### Hierarchy D2H1:

Tables: "DIM_2"

Levels: "D2H1L1"

###### Level "D2H1L1" :

    column(s): KEY

##### Dimension "D1":

Hierarchies:

    D1H1

##### Hierarchy D1H1:

Tables: "DIM_1"

Levels: "D1H1L1"

###### Level "D1H1L1" :

    column(s): KEY

---
### Cubes :

    CubeTwoDimensions

---
#### Cube "CubeTwoDimensions":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "Dimension1 -> D1":

##### Dimension: "Dimension1 -> D2":

### Cube "CubeTwoDimensions" diagram:

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
c0["CubeTwoDimensions"]{
M Measure1 ""
D Dimension1 ""
D Dimension1 ""
}
d00["D1"] {
H D1H1 ""
}
h000["D1H1"] {
L D1H1L1 ""
}
d01["D2"] {
H D2H1 ""
}
h010["D2H1"] {
L D2H1L1 ""
}
"c0" ||--|| "d00" : "DIM1_KEY-KEY"
"d00" ||--|| "h000" : "KEY-KEY"
"c0" ||--|| "d01" : "DIM2_KEY-KEY"
"d01" ||--|| "h010" : "KEY-KEY"
```
---
### Cube Matrix for Minimal_Cube_with_two_dimensions_with_same_name:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube CubeTwoDimensions: [0.1000, 0.0000]
```
---
### Database :
---
```mermaid
---
title: Diagram;
---
erDiagram
"DIM_1✔"{
int KEY "✔"
string NAME "✔"
}
"DIM_2✔"{
int KEY "✔"
string NAME "✔"
}
"Fact✔"{
int DIM1_KEY "✔"
int DIM2_KEY "✔"
int VALUE "✔"
}

"Fact✔" ||--o{ "DIM_1✔" : "DIM1_KEY-KEY"
"Fact✔" ||--o{ "DIM_2✔" : "DIM2_KEY-KEY"
```
---
## Validation result for schema Minimal_Cube_with_two_dimensions_with_same_name
## <span style='color: red;'>ERROR</span> : 
|Type|   |
|----|---|
|SCHEMA|Schema Dimension with name D1 meets more then one time|
|SCHEMA|Cube Dimension with name Dimension1 meets more then one time in cube with name CubeTwoDimensions|
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|DATABASE|Table: Schema must be set|
