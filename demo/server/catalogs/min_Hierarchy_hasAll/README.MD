# Documentation
### CatalogName : min_Hierarchy_hasAll
## Olap Context Details:
## Schemas:
### Schema Minimal_hasAll : 
### Public Dimensions:

    Dimension1

##### Dimension "Dimension1":

Hierarchies:

    HierarchyWithHasAll, HierarchyWithoutHasAll

##### Hierarchy HierarchyWithHasAll:

Tables: "Fact"

Levels: "Level1"

###### Level "Level1" :

    column(s): KEY

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
M Measure1 ""D Dimension1 ""
}
d00[Dimension1] {
H HierarchyWithHasAll ""
H HierarchyWithoutHasAll ""
}
h000[HierarchyWithHasAll] {
L Level1 ""
}
h001[HierarchyWithoutHasAll] {
L Level2 ""
}
"c0" ||--|| "d00" : "KEY-KEY"
"d00" ||--|| "h000" : "KEY-KEY"
"c0" ||--|| "d00" : "KEY-KEY"
"d00" ||--|| "h001" : "KEY-KEY"
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
## Validation result for schema Minimal_hasAll
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|SCHEMA|Level: Type should be set for Level1|
|SCHEMA|Level: Type should be set for Level2|
|DATABASE|Table: Schema must be set|
