# Documentation
### CatalogName : tutorial_30_Cube_KPI
### Schema Minimal_Cubes_With_KPI : 

		
		A mininmal cube with Kpi
		
  
---
### Cubes :

    CubeKPI

---
#### Cube "CubeKPI":

    

##### Table: "Fact"

### Cube "CubeKPI" diagram:

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
c0["CubeKPI"]{
M Measure1-Sum ""
M Measure2-Count ""
CM MeasureValue ""
CM MeasureGoal ""
CM MeasureStatus ""
CM MeasureTrend ""
CM MeasureWeight ""
CM MeasureCurrentTimeMember ""
}
```
---
### Cube Matrix for Minimal_Cubes_With_KPI:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube CubeKPI: [0.0000, 0.0000]
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
null VALUE_NUMERIC "✔"
}

```
---
