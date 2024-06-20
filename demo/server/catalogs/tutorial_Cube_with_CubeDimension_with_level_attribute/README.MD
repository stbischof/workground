# Documentation
### CatalogName : tutorial_Cube_with_CubeDimension_with_level_attribute
### Schema Minimal_Cube_with_cube_dimension_level_with_property : 

    
		 
		A basic OLAP schema with DimensionUsage with level attribute
    Level attribute in DimensionUsage uses for optimize sql inner join
    Level attribute is name of the level to join to
    If not specified joins to the lowest level of the dimension
		
  
### Public Dimensions:

    Dim

##### Dimension "Dim":

Hierarchies:

    Hierarchy1

##### Hierarchy Hierarchy1:

Tables: "HX_L2,H1_L1"

Levels: "H1_Level1, H1_Level2"

###### Level "H1_Level1" :

    column(s): KEY

###### Level "H1_Level2" :

    column(s): KEY

---
### Cubes :

    Cube

---
#### Cube "Cube":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "Diml1 -> Dim":

##### Dimension: "Diml2 -> Dim":

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
D Diml1 ""
D Diml2 ""
}
d00["Dim"] {
H Hierarchy1 ""
}
h000["Hierarchy1"] {
L H1_Level1 ""
L H1_Level2 ""
}
d01["Dim"] {
H Hierarchy1 ""
}
h010["Hierarchy1"] {
L H1_Level1 ""
L H1_Level2 ""
}
"c0" ||--|| "d00" : "DIM_KEY-"
"d00" ||--|| "h000" : "KEY"
"d00" ||--|| "h000" : "KEY"
"c0" ||--|| "d01" : "DIM_KEY-"
"d01" ||--|| "h010" : "KEY"
"d01" ||--|| "h010" : "KEY"
```
---
### Cube Matrix for Minimal_Cube_with_cube_dimension_level_with_property:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube Cube: [0.2000, 0.0000]
```
---
### Database :
---
```mermaid
---
title: Diagram;
---
erDiagram
"Fact✔"{
int DIM_KEY "✔"
int VALUE "✔"
}
"H1_L1✔"{
int KEY "✔"
string NAME "✔"
}
"HX_L2✔"{
int KEY "✔"
string NAME "✔"
int H1L1_KEY "✔"
int H2L1_KEY "✔"
}

"Fact✔" ||--o{ "HX_L2✔" : "DIM_KEY-"
"HX_L2✔" ||--o{ "H1_L1✔" : "H1L1_KEY-KEY"
"Fact✔" ||--o{ "HX_L2✔" : "DIM_KEY-"
"HX_L2✔" ||--o{ "H1_L1✔" : "H1L1_KEY-KEY"
```
---
## Validation result for schema Minimal_Cube_with_cube_dimension_level_with_property
## <span style='color: red;'>ERROR</span> : 
|Type|   |
|----|---|
|SCHEMA|Hierarchy: PrimaryKeyTable and PrimaryKey must be set for Join in dimension with name Dim|
|SCHEMA|Hierarchy: PrimaryKey must be set for Join in dimension with name Dim|
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|DATABASE|Table: Schema must be set|
