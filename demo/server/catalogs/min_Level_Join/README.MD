# Documentation
### CatalogName : min_Level_Join
## Olap Context Details:
## Schemas:
### Schema Minimal_Single_Hierarchy : 
### Public Dimensions:

    Dimension1

##### Dimension "Dimension1":

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
H Hierarchy1 ""
}
h000[Hierarchy1] {
L Level1 ""
L Level2 ""
}
"c0" ||--|| "d00" : "DIM_KEY-KEY"
"d00" ||--|| "h000" : "KEY-KEY"
"d00" ||--|| "h000" : "KEY-KEY"
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
Level_1{
int KEY
string NAME
}
Level_2{
int KEY
string NAME
int L1_KEY
}

"Fact" ||--o{ "Level_2" : "DIM_KEY-KEY"
"Level_2" ||--o{ "Level_1" : "L1_KEY-KEY"
```
---
## Validation result for schema Minimal_Single_Hierarchy
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|SCHEMA|Level: Type should be set for Level1|
|SCHEMA|Level: Type should be set for Level2|
|DATABASE|Table: Schema must be set|
