# Documentation
### CatalogName : tutorial_01-04-01_Cube_Measure_MeasuresWithDifferentAggregators
### Schema 01-03_Minimal_Cube_With_One_Measure_And_Different_Aggregators : 

		
		A minimal cube with one measurement column and different aggregators
		
		Measurement values in a data cube can be aggregated differently, depending on the chosen aggregation function in the aggregator attribute of the <Measure> tag. Available aggregators are:
		- sum: summation of numeric values
		- count: number of values
		- min: minimal value
		- max: maximal value
		- avg: average of numeric values
		- distinct-count: number of different values (without duplicate values)
		
		In this example cube every measure traces back to the "VALUE" column of the "Fact" database table, but uses another aggregator. 
		
  
---
### Cubes :

    CubeDifferentAggregators

---
#### Cube "CubeDifferentAggregators":

    

##### Table: "Fact"

### Cube "CubeDifferentAggregators" diagram:

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
c0["CubeDifferentAggregators"]{
M Measure1-Sum ""
M Measure1-Count ""
M Measure1-Ming ""
M Measure1-Maxg ""
M Measure1-Avg ""
M Measure1-DistCount ""
}
```
---
### Cube Matrix for 01-03_Minimal_Cube_With_One_Measure_And_Different_Aggregators:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube CubeDifferentAggregators: [0.0000, 0.0000]
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
