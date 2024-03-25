# Documentation
### CatalogName : tutorial_01-04-03_Cube_Measure_MeasureDatatypes
### Schema Minimal_Cube_With_Measures_Of_Different_Datatypes : 

		
		A minimal cube with measures of different datatypes

		The datatype of the measuse can be adapted by using the optional datatype attribute of the <Measure> element.
		- String
		- Numeric
		- Integer
		- Boolean
		- Date
		- Time
		- Timestamp
		If no datatype attribute is defined, the following default datatypes are used:
		- if the aggregator "count" or "distinct-count" is used: Integer
		- it another aggregator is used: Numeric

		In this example cube both measurements are generated from the "VALUE_NUMERIC" column of the "Fact" table, but one is provided with an Intger and the other one with a Numeric datatype.
		
  
---
### Cubes :

    CubeOneNumericMeasureDifferentDataTypes

---
#### Cube "CubeOneNumericMeasureDifferentDataTypes":

    

##### Table: "Fact"

### Cube "CubeOneNumericMeasureDifferentDataTypes" diagram:

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
c0["CubeOneNumericMeasureDifferentDataTypes"]{
M Measure1-Integer ""
M Measure1-Numeric ""
}
```
---
### Cube Matrix for Minimal_Cube_With_Measures_Of_Different_Datatypes:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube CubeOneNumericMeasureDifferentDataTypes: [0.0000, 0.0000]
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
null VALUE "✔"
}

```
---
