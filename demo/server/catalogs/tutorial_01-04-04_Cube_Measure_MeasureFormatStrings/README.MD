# Documentation
### CatalogName : tutorial_01-04-04_Cube_Measure_MeasureFormatStrings
### Schema Minimal_Cube_With_Measures_Of_Different_Datatypes : 

		
		A minimal cube with two measures formatted by different format strings
		
		The displayed cell format of measures can be adapted by setting a formatString attribute. Further details see https://mondrian.pentaho.com/api/mondrian/util/Format.html. 
		
		In this sample cube the three measures derive from the same table column in the database, but have different expressions in the format String attribute.
		
  
---
### Cubes :

    CubeOneNumericMeasureDifferentFormatStrings

---
#### Cube "CubeOneNumericMeasureDifferentFormatStrings":

    

##### Table: "Fact"

### Cube "CubeOneNumericMeasureDifferentFormatStrings" diagram:

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
c0["CubeOneNumericMeasureDifferentFormatStrings"]{
M Measure1 ""
M Measure2 ""
M Measure3 ""
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

Cube CubeOneNumericMeasureDifferentFormatStrings: [0.0000, 0.0000]
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
