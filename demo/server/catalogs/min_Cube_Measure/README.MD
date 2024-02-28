# Documentation
### CatalogName : min_Cube_Measure
## Olap Context Details:
## Schemas:
### Schema Minimal_Cubes_With_Measures : 
### Public Dimensions:

    

---
### Cubes :

    CubeOneMeasure, CubeTwoMeasures, CubeTwoMeasuresPlusOneCalculatedMeasure, CubeOneNumericMeasureDifferentDataTypes

---
#### Cube "CubeOneMeasure":

    

##### Table: "Fact"

##### Dimensions:
### Cube "CubeOneMeasure" diagram:

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
c0["CubeOneMeasure"]{
M Measure-Sum ""}
```
---
---
#### Cube "CubeTwoMeasures":

    

##### Table: "Fact"

##### Dimensions:
### Cube "CubeTwoMeasures" diagram:

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
c1["CubeTwoMeasures"]{
M Measure1-Sum ""M Measure2-Count ""}
```
---
---
#### Cube "CubeTwoMeasuresPlusOneCalculatedMeasure":

    

##### Table: "Fact"

##### Dimensions:
### Cube "CubeTwoMeasuresPlusOneCalculatedMeasure" diagram:

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
c2["CubeTwoMeasuresPlusOneCalculatedMeasure"]{
M Measure1-Sum ""M Measure2-Count ""}
```
---
---
#### Cube "CubeOneNumericMeasureDifferentDataTypes":

    

##### Table: "Fact"

##### Dimensions:
### Cube "CubeOneNumericMeasureDifferentDataTypes" diagram:

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
c3["CubeOneNumericMeasureDifferentDataTypes"]{
M Measure1-Integer ""M Measure2-Numeric ""}
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
null VALUE_NUMERIC
}

```
---
## Validation result for schema Minimal_Cubes_With_Measures
## <span style='color: red;'>ERROR</span> : 
|Type|   |
|----|---|
|SCHEMA|Cube with name CubeOneMeasure must contain Dimensions|
|SCHEMA|Cube with name CubeOneNumericMeasureDifferentDataTypes must contain Dimensions|
|SCHEMA|Cube with name CubeTwoMeasuresPlusOneCalculatedMeasure must contain Dimensions|
|SCHEMA|Cube with name CubeTwoMeasures must contain Dimensions|
