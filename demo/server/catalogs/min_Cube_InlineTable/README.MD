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
## Validation result for schema Minimal_Cubes_With_Inline_Tables
## <span style='color: red;'>ERROR</span> : 
|Type|   |
|----|---|
|SCHEMA|Cube with name CubeOneMeasureInlineTable must contain Dimensions|
