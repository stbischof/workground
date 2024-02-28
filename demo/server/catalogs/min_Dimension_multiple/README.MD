# Documentation
### CatalogName : min_Dimension_multiple
## Olap Context Details:
## Schemas:
### Schema Minimal_Two_Dimensions : 
### Public Dimensions:

    D1, D2

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

---
### Cubes :

    CubeTwoDimensions

---
#### Cube "CubeTwoDimensions":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "Dimension1 -> D1":

##### Dimension: "Dimension2 -> D2":

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
M Measure1 ""D Dimension1 ""
D Dimension2 ""
}
d00[D1] {
H D1H1 ""
}
h000[D1H1] {
L D1H1L1 ""
}
d01[D2] {
H D2H1 ""
}
h010[D2H1] {
L D2H1L1 ""
}
"c0" ||--|| "d00" : "DIM1_KEY-KEY"
"d00" ||--|| "h000" : "KEY-KEY"
"c0" ||--|| "d01" : "DIM2_KEY-KEY"
"d01" ||--|| "h010" : "KEY-KEY"
```
---
### Cube Matrix for Minimal_Two_Dimensions:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube CubeTwoDimensions: [0.1000, 0.0001]
```
---
### Database :
---
```mermaid
---
title: Diagram;
---
erDiagram
DIM_1{
int KEY
string NAME
}
DIM_2{
int KEY
string NAME
}
Fact{
int DIM1_KEY
int DIM2_KEY
int VALUE
}

"Fact" ||--o{ "DIM_1" : "DIM1_KEY-KEY"
"Fact" ||--o{ "DIM_2" : "DIM2_KEY-KEY"
```
---
## Validation result for schema Minimal_Two_Dimensions
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|SCHEMA|Level: Type should be set for D2H1L1|
|SCHEMA|Level: Type should be set for D1H1L1|
|DATABASE|Table: Schema must be set|
