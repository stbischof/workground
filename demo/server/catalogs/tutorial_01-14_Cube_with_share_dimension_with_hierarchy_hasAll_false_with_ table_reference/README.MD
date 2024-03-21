# Documentation
### CatalogName : tutorial_01-14_Cube_with_share_dimension_with_hierarchy_hasAll_false_with_ table_reference
### Schema Cube_with_share_dimension_with_hierarchy_hasAll_false_with_table_reference : 
### Public Dimensions:

    Dimension1

##### Dimension "Dimension1":

Hierarchies:

    HierarchyWithoutHasAll

##### Hierarchy HierarchyWithoutHasAll:

Tables: "Fact"

Levels: "Level2"

###### Level "Level2" :

    column(s): KEY

---
### Cubes :

    Cube1

---
#### Cube "Cube1":

    

##### Table: "Fact"

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
M Measure1 ""
D Dimension1 ""
}
d00["Dimension1"] {
H HierarchyWithoutHasAll ""
}
h000["HierarchyWithoutHasAll"] {
L Level2 ""
}
"c0" ||--|| "d00" : "KEY-KEY"
"d00" ||--|| "h000" : "KEY-KEY"
```
---
### Cube Matrix for Cube_with_share_dimension_with_hierarchy_hasAll_false_with_table_reference:
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
## Validation result for schema Cube_with_share_dimension_with_hierarchy_hasAll_false_with_table_reference
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|DATABASE|Table: Schema must be set|
