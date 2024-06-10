# Documentation
### CatalogName : tutorial_for_writeback_without_dimension
### Schema tutorial_for_writeback : 

    
      writeback with fact as table with only measure
		
  
---
### Cubes :

    C

---
#### Cube "C":

    

##### Table: "FACT"

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
}
```
---
### Cube Matrix for tutorial_for_writeback:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube C: [0.0000, 0.0000]
```
---
### Database :
---
```mermaid
---
title: Diagram;
---
erDiagram
"FACT✔"{
int VAL "✔"
int VAL1 "✔"
}
"FACTWB✔"{
int VAL "✔"
int VAL1 "✔"
string L2 "✔"
string ID "✔"
string USER "✔"
}
"L1✔"{
string L1 "✔"
string L2 "✔"
}
"L2✔"{
string L2 "✔"
}
"WRITE_BACK_DATA✔"{
double CURRENTVALUE "✔"
double NEWVALUE "✔"
string ALLOCATIONPOLICY "✔"
string CUBE_NAME "✔"
string MEMBERUNIQUENAMES "✔"
}

```
---
