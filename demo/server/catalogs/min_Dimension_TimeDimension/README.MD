# Documentation
### CatalogName : min_Dimension_TimeDimension
### Schema Minimal_Time_Dimension : 
### Public Dimensions:

    Time

##### Dimension "Time":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "Fact"

Levels: "Years, Quarters, Months"

###### Level "Years" :

    column(s): YEAR_ID

###### Level "Quarters" :

    column(s): QTR_NAME

###### Level "Months" :

    column(s): MONTH_NAME

---
### Cubes :

    CubeTimeDimension

---
#### Cube "CubeTimeDimension":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "Time -> Time":

### Cube "CubeTimeDimension" diagram:

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
c0["CubeTimeDimension"]{
M Measure-Sum ""
D Time ""
}
d00["Time"] {
H _ ""
}
h000["_"] {
L Years ""
L Quarters ""
L Months ""
}
"c0" ||--|| "d00" : "DATE_KEY-DATE_KEY"
"d00" ||--|| "h000" : "DATE_KEY-YEAR_ID"
"d00" ||--|| "h000" : "DATE_KEY-QTR_NAME"
"d00" ||--|| "h000" : "DATE_KEY-MONTH_NAME"
```
---
### Cube Matrix for Minimal_Time_Dimension:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube CubeTimeDimension: [0.1500, 0.0000]
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
timestamp DATE_KEY "✔"
int VALUE "✔"
int YEAR_ID "✔"
string QTR_ID "✔"
string QTR_NAME "✔"
string MONTH_ID "✔"
string MONTH_NAME "✔"
}

```
---
## Validation result for schema Minimal_Time_Dimension
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|DATABASE|Table: Schema must be set|
