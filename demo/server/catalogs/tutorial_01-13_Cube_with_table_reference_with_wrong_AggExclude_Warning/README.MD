# Documentation
### CatalogName : tutorial_01-13_Cube_with_table_reference_with_wrong_AggExclude_Warning
### Schema Cube_with_reference_with_wrong_AggExclude_Warning : 
---
### Cubes :

    Cube

---
#### Cube "Cube":

    

##### Table: "Fact"

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
}
```
---
### Cube Matrix for Cube_with_reference_with_wrong_AggExclude_Warning:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube Cube: [0.0000, 0.0000]
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
}
agg_01_Fact{
string KEY
int VALUE_count
}

```
---
## Validation result for schema Cube_with_reference_with_wrong_AggExclude_Warning
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|DATABASE|Agg Exclude table agg_01_Fact1 does not exist in database .|
