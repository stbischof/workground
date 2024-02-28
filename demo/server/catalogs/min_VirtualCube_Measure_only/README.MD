# Documentation
### CatalogName : min_VirtualCube_Measure_only
## Olap Context Details:
## Schemas:
### Schema Minimal_Virtual_Cubes_With_Measures : 
### Public Dimensions:

    

---
### Cubes :

    Cube1, Cube2

---
#### Cube "Cube1":

    

##### Table: "C1_Fact"

##### Dimensions:
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
M C1-Measure-Sum ""}
```
---
---
#### Cube "Cube2":

    

##### Table: "C2_Fact"

##### Dimensions:
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
M C2-Measure-Sum ""}
```
---
### Database :
---
```mermaid
---
title: Diagram;
---
erDiagram
C1_Fact{
string KEY
int VALUE
}
C2_Fact{
string KEY
int VALUE
}

```
---
## Validation result for schema Minimal_Virtual_Cubes_With_Measures
## <span style='color: red;'>ERROR</span> : 
|Type|   |
|----|---|
|SCHEMA|Cube with name Cube1 must contain Dimensions|
|SCHEMA|Cube with name Cube2 must contain Dimensions|
|SCHEMA|VirtualCube with name VirtualCubeMeasureOnly must contain dimensions |
