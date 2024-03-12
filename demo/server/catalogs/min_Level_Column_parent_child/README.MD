# Documentation
### CatalogName : min_Level_Column_parent_child
### Schema Minimal_Parent_Child_DifferentValueLevels : 
### Public Dimensions:

    Dimension1

##### Dimension "Dimension1":

Hierarchies:

    Hierarchy1

##### Hierarchy Hierarchy1:

Tables: "Hierarchy"

Levels: "Level1, Level2, Level3"

###### Level "Level1" :

    column(s): PARENT_L1

###### Level "Level2" :

    column(s): PARENT_L2

###### Level "Level3" :

    column(s): KEY

---
### Cubes :

    CubeParentChild

---
#### Cube "CubeParentChild":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "Dimension1 -> Dimension1":

### Cube "CubeParentChild" diagram:

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
c0["CubeParentChild"]{
M Measure1 ""
D Dimension1 ""
}
d00["Dimension1"] {
H Hierarchy1 ""
}
h000["Hierarchy1"] {
L Level1 ""
L Level2 ""
L Level3 ""
}
"c0" ||--|| "d00" : "DIM_KEY-KEY"
"d00" ||--|| "h000" : "KEY-PARENT_L1"
"d00" ||--|| "h000" : "KEY-PARENT_L2"
"d00" ||--|| "h000" : "KEY-KEY"
```
---
### Cube Matrix for Minimal_Parent_Child_DifferentValueLevels:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube CubeParentChild: [0.1500, 0.0000]
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
int DIM_KEY
int VALUE
}
Hierarchy{
int KEY
string NAME
int PARENT_L1
int PARENT_L2
}

"Fact" ||--o{ "Hierarchy" : "DIM_KEY-KEY"
```
---
## Validation result for schema Minimal_Parent_Child_DifferentValueLevels
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|SCHEMA|Level: Type should be set for Level3|
|SCHEMA|Level: Type should be set for Level1|
|SCHEMA|Level: Type should be set for Level2|
|DATABASE|Table: Schema must be set|
