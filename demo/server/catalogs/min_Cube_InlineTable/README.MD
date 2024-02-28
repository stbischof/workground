# Documentation
### CatalogName : min_Cube_InlineTable
## Olap Context Details:
## Schemas:
### Schema Minimal_Cubes_With_Inline_Tables : 
### Public Dimensions:

    

---
### Cubes :

    CubeOneMeasureInlineTable

---
#### Cube "CubeOneMeasureInlineTable":

    

##### Table: ""

##### Dimensions:
### Cube "CubeOneMeasureInlineTable" diagram:

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
c0["CubeOneMeasureInlineTable"]{
M Measure-Sum ""}
```
---
### Cube Matrix for Minimal_Cubes_With_Inline_Tables:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube CubeOneMeasureInlineTable: [0.0000, 0.0001]
```
---
## Validation result for schema Minimal_Cubes_With_Inline_Tables
## <span style='color: red;'>ERROR</span> : 
|Type|   |
|----|---|
|SCHEMA|Cube with name CubeOneMeasureInlineTable must contain Dimensions|
