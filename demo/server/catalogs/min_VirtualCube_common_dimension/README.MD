# Documentation
### CatalogName : min_VirtualCube_common_dimension
### Schema Minimal_Virtual_Cubes_With_1_Dimension : 
### Public Dimensions:

    Dimension1

##### Dimension "Dimension1":

Hierarchies:

    Hierarchy1

##### Hierarchy Hierarchy1:

Tables: "Dim_1"

Levels: "Level1"

###### Level "Level1" :

    column(s): KEY

---
### Cubes :

    Cube1, Cube2

---
#### Cube "Cube1":

    

##### Table: "C1_Fact"

##### Dimensions:
##### Dimension: "Dimension1 -> Dimension1":

---
#### Cube "Cube2":

    

##### Table: "C2_Fact"

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
M C1-Measure-Sum ""
D Dimension1 ""
}
d00["Dimension1"] {
H Hierarchy1 ""
}
h000["Hierarchy1"] {
L Level1 ""
}
"c0" ||--|| "d00" : "DIM_1_KEY-KEY"
"d00" ||--|| "h000" : "KEY-KEY"
```
---
### Cube "Cube2" diagram:

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
c1["Cube2"]{
M C2-Measure-Sum ""
D Dimension1 ""
}
d10["Dimension1"] {
H Hierarchy1 ""
}
h100["Hierarchy1"] {
L Level1 ""
}
"c1" ||--|| "d10" : "DIM_1_KEY-KEY"
"d10" ||--|| "h100" : "KEY-KEY"
```
---
### Virtual Cube "VirtualCubeSharedDimension" diagram:

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
c2["VirtualCubeSharedDimension"]{
M Cube1_Measures_C1-Measure-Sum ""
M Cube2_Measures_C2-Measure-Sum ""
D Dimension1 ""
CM Calculation1 ""
}
```
---
### Cube Matrix for Minimal_Virtual_Cubes_With_1_Dimension:
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
Cube Cube2: [0.0500, 0.0000]
```
---
### Database :
---
```mermaid
---
title: Diagram;
---
erDiagram
"C1_Fact✔"{
int DIM_1_KEY "✔"
int VALUE "✔"
}
"C2_Fact✔"{
int DIM_1_KEY "✔"
int VALUE "✔"
}
"Dim_1✔"{
int KEY "✔"
string NAME "✔"
}

"C1_Fact✔" ||--o{ "Dim_1✔" : "DIM_1_KEY-KEY"
"C2_Fact✔" ||--o{ "Dim_1✔" : "DIM_1_KEY-KEY"
```
---
## Validation result for schema Minimal_Virtual_Cubes_With_1_Dimension
## <span style='color: red;'>ERROR</span> : 
|Type|   |
|----|---|
|SCHEMA|Virtual Cube Dimension Cube name must be set|
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|DATABASE|Table: Schema must be set|
