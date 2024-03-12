# Documentation
### CatalogName : min_Dimension_Hierarchy_multiple
### Schema Minimal_Multiple_Hierarchy : 
### Public Dimensions:

    Dimension1

##### Dimension "Dimension1":

Hierarchies:

    Hierarchy1, Hierarchy2

##### Hierarchy Hierarchy1:

Tables: "HX_L2,H1_L1"

Levels: "H1_Level1, H1_Level2"

###### Level "H1_Level1" :

    column(s): KEY

###### Level "H1_Level2" :

    column(s): KEY

##### Hierarchy Hierarchy2:

Tables: "HX_L2,H2_L1"

Levels: "H2_Level1, H2_Level2"

###### Level "H2_Level1" :

    column(s): KEY

###### Level "H2_Level2" :

    column(s): KEY

---
### Cubes :

    CubeMultipleHierarchy

---
#### Cube "CubeMultipleHierarchy":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "Dimension1 -> Dimension1":

### Cube "CubeMultipleHierarchy" diagram:

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
c0["CubeMultipleHierarchy"]{
M Measure1 ""
D Dimension1 ""
}
d00["Dimension1"] {
H Hierarchy1 ""
H Hierarchy2 ""
}
h000["Hierarchy1"] {
L H1_Level1 ""
L H1_Level2 ""
}
h001["Hierarchy2"] {
L H2_Level1 ""
L H2_Level2 ""
}
"c0" ||--|| "d00" : "DIM_KEY-KEY"
"d00" ||--|| "h000" : "KEY-KEY"
"d00" ||--|| "h000" : "KEY-KEY"
"c0" ||--|| "d00" : "DIM_KEY-KEY"
"d00" ||--|| "h001" : "KEY-KEY"
"d00" ||--|| "h001" : "KEY-KEY"
```
---
### Cube Matrix for Minimal_Multiple_Hierarchy:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube CubeMultipleHierarchy: [0.2000, 0.0000]
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
H1_L1{
int KEY
string NAME
}
H2_L1{
int KEY
string NAME
}
HX_L2{
int KEY
string NAME
int H1L1_KEY
int H2L1_KEY
}

"Fact" ||--o{ "HX_L2" : "DIM_KEY-KEY"
"HX_L2" ||--o{ "H1_L1" : "H1L1_KEY-KEY"
"Fact" ||--o{ "HX_L2" : "DIM_KEY-KEY"
"HX_L2" ||--o{ "H2_L1" : "H2L1_KEY-KEY"
```
---
## Validation result for schema Minimal_Multiple_Hierarchy
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|SCHEMA|Level: Type should be set for H1_Level2|
|SCHEMA|Level: Type should be set for H1_Level1|
|SCHEMA|Level: Type should be set for H2_Level1|
|SCHEMA|Level: Type should be set for H2_Level2|
|DATABASE|Table: Schema must be set|
