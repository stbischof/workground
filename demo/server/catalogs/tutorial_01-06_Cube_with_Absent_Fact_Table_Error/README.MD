# Documentation
### CatalogName : tutorial_01-06_Cube_with_Absent_Fact_Table_Error
### Schema Minimal_Cubes_With_No_Fact_Reference : 

		
		A minimal cube with absent Fact reference.
		
  
---
### Cubes :

    Cube

---
#### Cube "Cube":

    

##### Table: ""

### Cube "Cube" diagram:

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
c0["Cube"]{
M Measure1 ""
}
```
---
### Cube Matrix for Minimal_Cubes_With_No_Fact_Reference:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube Cube: [0.0000, 0.0000]
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
## Validation result for schema Minimal_Cubes_With_No_Fact_Reference
## <span style='color: red;'>ERROR</span> : 
|Type|   |
|----|---|
|SCHEMA|Fact name must be set for cube with name Cube|
