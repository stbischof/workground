# Documentation
### CatalogName : tutorial_20-00_VirtualCube
### Schema Cube_with_virtual_cube : 

    
		A basic OLAP schema with virtual cube which have reference to Cube1, Cube2

		
  
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

    Cube1, Cube2

---
#### Cube "Cube1":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "Cube1Dimension1 -> Dimension1":

---
#### Cube "Cube2":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "Cube2Dimension1 -> Dimension1":

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
M MeasureCube1 ""
D Cube1Dimension1 ""
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
M MeasureCube2 ""
D Cube2Dimension1 ""
}
d10["Dimension1"] {
H HierarchyWithoutHasAll ""
}
h100["HierarchyWithoutHasAll"] {
L Level2 ""
}
"c1" ||--|| "d10" : "KEY-KEY"
"d10" ||--|| "h100" : "KEY-KEY"
```
---
### Virtual Cube "Cube1Cube2" diagram:

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
c2["Cube1Cube2"]{
M Cube1_Measures_MeasureCube1 ""
M Cube2_Measures_MeasureCube2 ""
D Cube1Dimension1 ""
D Cube2Dimension1 ""
}
d20["Dimension1"] {
H HierarchyWithoutHasAll ""
}
h200["HierarchyWithoutHasAll"] {
L Level2 ""
}
d21["Dimension1"] {
H HierarchyWithoutHasAll ""
}
h210["HierarchyWithoutHasAll"] {
L Level2 ""
}
"c2" ||--|| "d20" : "KEY-KEY"
"d20" ||--|| "h200" : "KEY-KEY"
"c2" ||--|| "d21" : "KEY-KEY"
"d21" ||--|| "h210" : "KEY-KEY"
```
---
### Cube Matrix for Cube_with_virtual_cube:
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
## Validation result for schema Cube_with_virtual_cube
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|DATABASE|Table: Schema must be set|
