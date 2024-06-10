# Documentation
### CatalogName : tutorial_01-11_Cube_with_cub_with_dimension_without_name_Error
### Schema Cube_with_cub_with_dimension_without_name : 
---
### Cubes :

    Cube

---
#### Cube "Cube":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension "":

Hierarchies:

    HierarchyWithHasAll

##### Hierarchy HierarchyWithHasAll:

Tables: "Fact"

Levels: "Level"

###### Level "Level" :

    column(s): KEY

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
D _ ""
}
d00[""] {
H HierarchyWithHasAll ""
}
h000["HierarchyWithHasAll"] {
L Level ""
}
"c0" ||--|| "d00" : "KEY"
"d00" ||--|| "h000" : "KEY-KEY"
```
---
### Cube Matrix for Cube_with_cub_with_dimension_without_name:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube Cube: [0.0500, 0.0000]
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
string KEY "✔"
int VALUE "✔"
}

```
---
## Validation result for schema Cube_with_cub_with_dimension_without_name
## <span style='color: red;'>ERROR</span> : 
|Type|   |
|----|---|
|SCHEMA|Cube Dimension name must be set for element with name Cube|
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|DATABASE|Table: Schema must be set|
