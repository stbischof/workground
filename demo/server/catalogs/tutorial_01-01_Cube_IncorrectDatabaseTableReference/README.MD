# Documentation
### CatalogName : tutorial_01-01_Cube_IncorrectDatabaseTableReference
### Schema 01-01_Minimal_Cube_With_One_Measure_And_Incorrect_Table_Reference : 

	
    	This schema shows a minimal cube with an incorrect fact table reference.
		In this example the table "Fakt", which is referred to as the database fact table in the <Table> tag, does not exist in the database.
	
---
### Cubes :

    CubeOneMeasure

---
#### Cube "CubeOneMeasure":

    

##### Table: "Fakt"

### Cube "CubeOneMeasure" diagram:

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
c0["CubeOneMeasure"]{
M Measure-Sum ""
}
```
---
### Cube Matrix for 01-01_Minimal_Cube_With_One_Measure_And_Incorrect_Table_Reference:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube CubeOneMeasure: [0.0000, 0.0000]
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
"Fakt❌"{
VARCHAR VALUE "❌"
}

```
---
## Validation result for schema 01-01_Minimal_Cube_With_One_Measure_And_Incorrect_Table_Reference
## <span style='color: red;'>ERROR</span> : 
|Type|   |
|----|---|
|DATABASE|Fact table Fakt does not exist in database .|
|DATABASE|Measure Measure-Sum column VALUE doesn't exist in table Fakt of cube with name CubeOneMeasure|
