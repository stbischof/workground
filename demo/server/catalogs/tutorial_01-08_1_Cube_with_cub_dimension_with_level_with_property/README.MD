# Documentation
### CatalogName : tutorial_01-08_1_Cube_with_cub_dimension_with_level_with_property
### Schema Minimal_Cube_with_cube_dimension_level : 
---
### Cubes :

    Cube

---
#### Cube "Cube":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension "Dimension":

Hierarchies:

    HierarchyWithHasAll

##### Hierarchy HierarchyWithHasAll:

Tables: "Fact"

Levels: "Level"

###### Level "Level" :

    column(s): L

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
D Dimension ""
}
d00["Dimension"] {
H HierarchyWithHasAll ""
}
h000["HierarchyWithHasAll"] {
L Level ""
}
"c0" ||--|| "d00" : "KEY"
"d00" ||--|| "h000" : "KEY-L"
```
---
### Cube Matrix for Minimal_Cube_with_cube_dimension_level:
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
Fact{
string KEY
int VALUE
string L
string prop
}

```
---
## Validation result for schema Minimal_Cube_with_cube_dimension_level
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|DATABASE|Table: Schema must be set|
