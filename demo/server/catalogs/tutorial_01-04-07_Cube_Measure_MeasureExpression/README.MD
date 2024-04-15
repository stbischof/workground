# Documentation
### CatalogName : tutorial_01-04-07_Cube_Measure_MeasureExpression
### Schema Minimal_Cubes_With_MeasureExpression : 

		
		A mininmal cube with a simple measure with MeasureExpression.

		
  
---
### Cubes :

    CubeMeasureWithMeasureExpression

---
#### Cube "CubeMeasureWithMeasureExpression":

    

##### Table: "FACT"

### Cube "CubeMeasureWithMeasureExpression" diagram:

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
c0["CubeMeasureWithMeasureExpression"]{
M Measure1-Sum ""
M Measure2-Sum ""
}
```
---
### Cube Matrix for Minimal_Cubes_With_MeasureExpression:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube CubeMeasureWithMeasureExpression: [0.0000, 0.0000]
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
int KEY "✔"
int VALUE "✔"
null VALUE_NUMERIC "✔"
}
"MEASURE_TABLE✔"{
int ID "✔"
int VALUE "✔"
int FLAG "✔"
}

```
---
## Validation result for schema Minimal_Cubes_With_MeasureExpression
## <span style='color: red;'>ERROR</span> : 
|Type|   |
|----|---|
|DATABASE|Syntax error in SQL statement "([*]CASE WHEN ""FACT"".""VALUE"" > 21 THEN 50 ELSE ""FACT"".""VALUE"" END)"; expected "(, SELECT, TABLE, VALUES"; SQL statement:(CASE WHEN "FACT"."VALUE" > 21 THEN 50 ELSE "FACT"."VALUE" END) [42001-224]|
