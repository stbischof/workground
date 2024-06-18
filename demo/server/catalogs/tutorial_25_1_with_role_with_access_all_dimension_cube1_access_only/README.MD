# Documentation
### CatalogName : tutorial_25_1_with_role_with_access_all_dimension_cube1_access_only
### Schema Cube_with_access_all_dimension_cube1_access_only : 

    Cube with examples of roles with SchemaGrant all_dimensions
    Cube1 - all access
    Cube2 - no access

  
### Public Dimensions:

    Dimension1

##### Dimension "Dimension1":

Hierarchies:

    Hierarchy1

##### Hierarchy Hierarchy1:

Tables: "Fact"

Levels: "Level2"

###### Level "Level2" :

    column(s): KEY

---
### Cubes :

    Cube1, Cube2

---
#### Cube "Cube1":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "Dimension1 -> Dimension1":

##### Dimension: "Dimension2 -> Dimension1":

---
#### Cube "Cube2":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "Dimension1 -> Dimension1":

### Roles :##### Role: "role1"

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
D Dimension2 ""
}
d00["Dimension1"] {
H Hierarchy1 ""
}
h000["Hierarchy1"] {
L Level2 ""
}
d01["Dimension1"] {
H Hierarchy1 ""
}
h010["Hierarchy1"] {
L Level2 ""
}
"c0" ||--|| "d00" : "KEY-KEY"
"d00" ||--|| "h000" : "KEY-KEY"
"c0" ||--|| "d01" : "KEY-KEY"
"d01" ||--|| "h010" : "KEY-KEY"
```
---
### Cube "Cube2" diagram:

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
c1["Cube2"]{
M Measure1 ""
D Dimension1 ""
}
d10["Dimension1"] {
H Hierarchy1 ""
}
h100["Hierarchy1"] {
L Level2 ""
}
"c1" ||--|| "d10" : "KEY-KEY"
"d10" ||--|| "h100" : "KEY-KEY"
```
---
### Cube Matrix for Cube_with_access_all_dimension_cube1_access_only:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube Cube1: [0.1000, 0.0000]
Cube Cube2: [0.0500, 0.0000]
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
## Validation result for schema Cube_with_access_all_dimension_cube1_access_only
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|DATABASE|Table: Schema must be set|
