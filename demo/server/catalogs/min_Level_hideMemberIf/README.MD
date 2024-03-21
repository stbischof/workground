# Documentation
### CatalogName : min_Level_hideMemberIf
### Schema Minimal_Single_Hierarchy_Hidden_Members : 
### Public Dimensions:

    DimensionMembersHiddenIfParentsName, DimensionMembersHiddenIfBlankName, DimensionMembersHiddenMultipleLevels

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

##### Dimension "DimensionMembersHiddenIfBlankName":

Hierarchies:

    Hierarchy1

##### Hierarchy Hierarchy1:

Tables: "Level_2_NULL,Level_1"

Levels: "Level1, Level2"

###### Level "Level1" :

    column(s): KEY

###### Level "Level2" :

    column(s): KEY

##### Dimension "DimensionMembersHiddenMultipleLevels":

Hierarchies:

    Hierarchy1

##### Hierarchy Hierarchy1:

Tables: "Level_3_Multiple,Level_2_Multiple,Level_1_Multiple"

Levels: "Level1, Level2, Level3"

###### Level "Level1" :

    column(s): KEY

###### Level "Level2" :

    column(s): KEY

###### Level "Level3" :

    column(s): KEY

---
### Cubes :

    HiddenMembersIfParentName, HiddenMembersIfBlankName, HiddenMembersMultipleLevels

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
---
#### Cube "HiddenMembersIfBlankName":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "DimensionMembersHiddenIfBlankName -> DimensionMembersHiddenIfBlankName":

### Cube "HiddenMembersIfBlankName" diagram:

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
c1["HiddenMembersIfBlankName"]{
M Measure1 ""
D DimensionMembersHiddenIfBlankName ""
}
d10["DimensionMembersHiddenIfBlankName"] {
H Hierarchy1 ""
}
h100["Hierarchy1"] {
L Level1 ""
L Level2 ""
}
"c1" ||--|| "d10" : "DIM_KEY-KEY"
"d10" ||--|| "h100" : "KEY-KEY"
"d10" ||--|| "h100" : "KEY-KEY"
```
---
---
#### Cube "HiddenMembersMultipleLevels":

    

##### Table: "Fact_Multiple"

##### Dimensions:
##### Dimension: "DimensionMembersHiddenMultipleLevels -> DimensionMembersHiddenMultipleLevels":

### Cube "HiddenMembersMultipleLevels" diagram:

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
c2["HiddenMembersMultipleLevels"]{
M Measure1 ""
D DimensionMembersHiddenMultipleLevels ""
}
d20["DimensionMembersHiddenMultipleLevels"] {
H Hierarchy1 ""
}
h200["Hierarchy1"] {
L Level1 ""
L Level2 ""
L Level3 ""
}
"c2" ||--|| "d20" : "DIM_KEY-KEY"
"d20" ||--|| "h200" : "KEY-KEY"
"d20" ||--|| "h200" : "KEY-KEY"
"d20" ||--|| "h200" : "KEY-KEY"
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
Cube HiddenMembersIfBlankName: [0.1000, 0.0000]
Cube HiddenMembersMultipleLevels: [0.1500, 0.0000]
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
Fact_Multiple{
int DIM_KEY
int VALUE
}
Level_1{
int KEY
string NAME
}
Level_1_Multiple{
int KEY
string NAME
}
Level_2{
int KEY
string NAME
int L1_KEY
}
Level_2_Multiple{
int KEY
string NAME
int L1_KEY
}
Level_2_NULL{
int KEY
string NAME
int L1_KEY
}
Level_3_Multiple{
int KEY
string NAME
int L2_KEY
}

"Fact" ||--o{ "Level_2" : "DIM_KEY-KEY"
"Level_2" ||--o{ "Level_1" : "L1_KEY-KEY"
"Fact" ||--o{ "Level_2_NULL" : "DIM_KEY-KEY"
"Level_2_NULL" ||--o{ "Level_1" : "L1_KEY-KEY"
"Fact_Multiple" ||--o{ "Level_3_Multiple" : "DIM_KEY-KEY"
"Level_3_Multiple" ||--o{ "Level_2_Multiple" : "L2_KEY-KEY"
"Level_2_Multiple" ||--o{ "Level_1_Multiple" : "L1_KEY-KEY"
```
---
## Validation result for schema Minimal_Single_Hierarchy_Hidden_Members
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|DATABASE|Table: Schema must be set|
