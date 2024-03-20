# Documentation
### CatalogName : tutorial_01-00_Cube
### Schema 01_Minimal_Cube_With_One_Measure : 

		
		A basic OLAP schema with a minimal cube
		
		Data cubes (<Cube>) are defined in an OLAP schema (<Schema>). Within the schema the name of each data cube must be unique. 
		This example schema contains one cube named "CubeOneMeasure".
		
		A cube is based on a fact table (<Table>) which refers to a database table containing one or more measurements to be aggregated (and optionally further columns defining factual dimensions). 
		In this case the database table representing the fact table is named "Fact" in the database, which is adressed in the name attribute within the <Table> tag.
		
		Each measurement of the cube is defined in a separate <Measure> element.
		The measurement in this example cube is named "Measure-Sum" (name attribute). It corresponds to the "VALUE" column (column attribute) in the database table "Fact" and is aggregated by summation (aggregator attribute).
		
  
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
### Cube Matrix for 01_Minimal_Cube_With_One_Measure:
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
Fact{
string KEY
int VALUE
}

```
---
