# Documentation
### CatalogName : tutorial_31-02_Level_hideMemberIf_IfParentsName
### Schema Minimal_Single_Hierarchy_Hidden_Members : 
### Public Dimensions:

    DimensionMembersHiddenIfParentsName

##### Dimension "DimensionMembersHiddenIfParentsName":

Hierarchies:

    Hierarchy1

##### Hierarchy Hierarchy1:

Tables: "Level_2,Level_1"

Levels: "Level1, Level2"

###### Level "Level1" :

    column(s): KEY

###### Level "Level2" :

    column(s): KEY

---
### Cubes :

    HiddenMembersIfParentName

---
#### Cube "HiddenMembersIfParentName":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "DimensionMembersHiddenIfParentsName -> DimensionMembersHiddenIfParentsName":

### Cube "HiddenMembersIfParentName" diagram:

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
c0["HiddenMembersIfParentName"]{
M Measure1 ""
D DimensionMembersHiddenIfParentsName ""
}
d00["DimensionMembersHiddenIfParentsName"] {
H Hierarchy1 ""
}
h000["Hierarchy1"] {
L Level1 ""
L Level2 ""
}
"c0" ||--|| "d00" : "DIM_KEY-KEY"
"d00" ||--|| "h000" : "KEY-KEY"
"d00" ||--|| "h000" : "KEY-KEY"
```
---
### Cube Matrix for Minimal_Single_Hierarchy_Hidden_Members:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube HiddenMembersIfParentName: [0.1000, 0.0000]
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
"Level_1✔"{
int KEY "✔"
string NAME "✔"
}
"Level_2✔"{
int KEY "✔"
string NAME "✔"
int L1_KEY "✔"
}

"Fact✔" ||--o{ "Level_2✔" : "DIM_KEY-KEY"
"Level_2✔" ||--o{ "Level_1✔" : "L1_KEY-KEY"
```
---
## Validation result for schema Minimal_Single_Hierarchy_Hidden_Members
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|DATABASE|Table: Schema must be set|
