# Documentation
### CatalogName : tutorial_01-02_Cube_IncorrectMeasureColumnReference
### Schema 01-02_Minimal_Cube_With_One_Measure_And_Incorrect_Measure_Column_Reference : 

	
    	A minimal cube with an incorrect measure column reference
		
		In this example the column "PRICE", which is referred to as the measurement column in the <Measure> tag, does not exist in the database table "Fact".	
	
---
### Cubes :

    CubeOneMeasure

---
#### Cube "CubeOneMeasure":

    

##### Table: "Fact"

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
### Cube Matrix for 01-02_Minimal_Cube_With_One_Measure_And_Incorrect_Measure_Column_Reference:
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
"Fact❌"{
string KEY "✔"
int VALUE "✔"
double VALUE_NUMERIC "✔"
VARCHAR PRICE "❌"
}

```
---
## Validation result for schema 01-02_Minimal_Cube_With_One_Measure_And_Incorrect_Measure_Column_Reference
## <span style='color: red;'>ERROR</span> : 
|Type|   |
|----|---|
|DATABASE|Measure Measure-Sum column PRICE doesn't exist in table Fact of cube with name CubeOneMeasure|
