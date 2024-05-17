# Documentation
### CatalogName : tutorial_for_writeback_multiple_measure
### Schema tutorial_for_writeback_multiple_measure : 

    


		
  
---
### Cubes :

    C

---
#### Cube "C":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension "D1":

Hierarchies:

    HierarchyWithHasAll

##### Hierarchy HierarchyWithHasAll:

Tables: "Fact"

Levels: "L1, L2"

###### Level "L1" :

    column(s): L1

###### Level "L2" :

    column(s): L2

##### Dimension "D2":

Hierarchies:

    HierarchyWithHasAll

##### Hierarchy HierarchyWithHasAll:

Tables: "Fact"

Levels: "L1, L2"

###### Level "L1" :

    column(s): L1

###### Level "L2" :

    column(s): L2

### Cube "C" diagram:

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
c0["C"]{
M Measure1 ""
M Measure2 ""
D D1 ""
D D2 ""
}
d00["D1"] {
H HierarchyWithHasAll ""
}
h000["HierarchyWithHasAll"] {
L L1 ""
L L2 ""
}
d01["D2"] {
H HierarchyWithHasAll ""
}
h010["HierarchyWithHasAll"] {
L L1 ""
L L2 ""
}
"c0" ||--|| "d00" : "KEY"
"d00" ||--|| "h000" : "KEY-L1"
"d00" ||--|| "h000" : "KEY-L2"
"c0" ||--|| "d01" : "KEY"
"d01" ||--|| "h010" : "KEY-L1"
"d01" ||--|| "h010" : "KEY-L2"
```
---
### Cube Matrix for tutorial_for_writeback_multiple_measure:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube C: [0.2000, 0.0000]
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
int VALUE1 "✔"
string L1 "✔"
int L2 "✔"
string PROP1 "✔"
int PROP2 "✔"
}
"WRITE_BACK_DATA✔"{
null CURRENTVALUE "✔"
null NEWVALUE "✔"
string ALLOCATIONPOLICY "✔"
string CUBE_NAME "✔"
string MEMBERUNIQUENAMES "✔"
}

```
---
## Validation result for schema tutorial_for_writeback_multiple_measure
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|DATABASE|Table: Schema must be set|
