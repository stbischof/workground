# Documentation
### CatalogName : tutorial_01-04-04-02_Cube_CalculatedMember_Wrong_CellFormatter
### Schema Minimal_Cube_With_CalculatedMember_CellFormatter : 

		
		A minimal cube with CellFormatter with class name in CalculatedMember

		
  
---
### Cubes :

    CubeOneNumericMeasureWithCellFormatter

---
#### Cube "CubeOneNumericMeasureWithCellFormatter":

    

##### Table: "Fact"

### Cube "CubeOneNumericMeasureWithCellFormatter" diagram:

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
c0["CubeOneNumericMeasureWithCellFormatter"]{
M Measure1 ""
CM CM ""
}
```
---
### Cube Matrix for Minimal_Cube_With_CalculatedMember_CellFormatter:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube CubeOneNumericMeasureWithCellFormatter: [0.0000, 0.0000]
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
double VALUE "✔"
}

```
---
## Validation result for schema Minimal_Cube_With_CalculatedMember_CellFormatter
## <span style='color: red;'>ERROR</span> : 
|Type|   |
|----|---|
|SCHEMA|Formatter: Class Name mondrian.rolap.format.CellFormatterWrong not found|
