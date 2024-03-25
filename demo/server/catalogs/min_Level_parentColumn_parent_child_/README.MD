# Documentation
### CatalogName : min_Level_parentColumn_parent_child_
### Schema Minimal_Parent_Child_Hierarchy : 
### Public Dimensions:

    Dimension1

##### Dimension "Dimension1":

Hierarchies:

    Hierarchy1

##### Hierarchy Hierarchy1:

Tables: "Hier_One_Top_Member"

Levels: "Level"

###### Level "Level" :

    column(s): KEY

---
### Cubes :

    CubeParentChildOneTopMember

---
#### Cube "CubeParentChildOneTopMember":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "Dimension1 -> Dimension1":

### Cube "CubeParentChildOneTopMember" diagram:

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
c0["CubeParentChildOneTopMember"]{
M Measure1 ""
D Dimension1 ""
}
d00["Dimension1"] {
H Hierarchy1 ""
}
h000["Hierarchy1"] {
L Level ""
}
"c0" ||--|| "d00" : "DIM_KEY-KEY"
"d00" ||--|| "h000" : "KEY-KEY"
```
---
### Cube Matrix for Minimal_Parent_Child_Hierarchy:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube CubeParentChildOneTopMember: [0.0500, 0.0000]
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
int DIM_KEY "✔"
int VALUE "✔"
}
"Hier_One_Top_Member✔"{
int KEY "✔"
string NAME "✔"
int PARENT_KEY "✔"
}

"Fact" ||--o{ "Hier_One_Top_Member" : "DIM_KEY-KEY"
```
---
## Validation result for schema Minimal_Parent_Child_Hierarchy
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|DATABASE|Table: Schema must be set|
