# Documentation
### CatalogName : tutorial_01-10_Cube_with_cub_with_share_dimension_without_name_Error
### Schema Minimal_Cube_with_cub_with_share_dimension_without_name : 
### Public Dimensions:

    null

##### Dimension "null":

Hierarchies:

    HierarchyWithHasAll

##### Hierarchy HierarchyWithHasAll:

Tables: "Fact"

Levels: "Level"

###### Level "Level" :

    column(s): KEY

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
### Cube Matrix for Minimal_Cube_with_cub_with_share_dimension_without_name:
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

```
---
## Validation result for schema Minimal_Cube_with_cub_with_share_dimension_without_name
## <span style='color: red;'>ERROR</span> : 
|Type|   |
|----|---|
|SCHEMA|Schema Dimension name must be set|
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|DATABASE|Table: Schema must be set|
