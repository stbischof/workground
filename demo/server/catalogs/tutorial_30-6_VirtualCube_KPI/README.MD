# Documentation
### CatalogName : tutorial_30-6_VirtualCube_KPI
### Schema Cube_with_virtual_cube_with_kpi : 

    
		A basic OLAP schema with virtual cube which have reference to Cube1, Cube2 and with KPI

		A KPI has four important properties which are value, goal, status and trend.
		Let's explain this by means of Profit Margin with the below calculation.

    Value: is the actual value of the KPI. This will be a numeric value. For example, this can be the Profit Margin.
      This might not be included in the fact table hence we may have to calculate or derive this column.

    Goal: every organization has a goal for this value. For example, the organization may look at the
      goal of achieving a five percent Profit Margin. Also, sometimes they may have different values for
      different business areas. For example, depending on the product category or sales territory,
      the sales margin goal will differ.

    Status: depending on the KPI value and the KPI goal, the KPI status can be defined.
      For an example, we can say that if the KPI value is greater than the goal it is great if it is not greater
      than the goal, but still greater than zero it is good and if less than zero or running at a loss it is bad.
      This Great, Good or Bad can be displayed to the user by means of a graphical representation such as an arrow,
      traffic lights or a gauge.

    Trend: trend is an optional parameter when defining a KPI, but still an important feature in a KPI.
      For example, you may have a great profit margin, but comparing with last year, it could be less.
      On the other hand, you might have a bad profit margin, but compared to last year it is improving.

    Weight:  The unique name of the member in the measures dimension for the KPI weight.

    StatusGraphic: The default graphical representation of the KPI status.
      (Traffic Light, Road Signs, Gauge - Ascending, Gauge - Descending, Thermometer, Cylinder, Smiley Face)

    TrendGraphic: The default graphical representation of the KPI trend.
      (Standard Arrow, Status Arrow - Ascending, Status Arrow - Descending, Smiley Face)

    DisplayFolder:  The display folder.
		
  
### Public Dimensions:

    Dimension1

##### Dimension "Dimension1":

Hierarchies:

    HierarchyWithoutHasAll

##### Hierarchy HierarchyWithoutHasAll:

Tables: "Fact"

Levels: "Level2"

###### Level "Level2" :

    column(s): KEY

---
### Cubes :

    Cube1, Cube2

---
#### Cube "Cube1":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "Cube1Dimension1 -> Dimension1":

---
#### Cube "Cube2":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "Cube2Dimension1 -> Dimension1":

### Cube "Cube1" diagram:

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
c0["Cube1"]{
M MeasureCube1 ""
D Cube1Dimension1 ""
}
d00["Dimension1"] {
H HierarchyWithoutHasAll ""
}
h000["HierarchyWithoutHasAll"] {
L Level2 ""
}
"c0" ||--|| "d00" : "KEY-KEY"
"d00" ||--|| "h000" : "KEY-KEY"
```
---
### Cube "Cube2" diagram:

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
c1["Cube2"]{
M MeasureCube2 ""
D Cube2Dimension1 ""
}
d10["Dimension1"] {
H HierarchyWithoutHasAll ""
}
h100["HierarchyWithoutHasAll"] {
L Level2 ""
}
"c1" ||--|| "d10" : "KEY-KEY"
"d10" ||--|| "h100" : "KEY-KEY"
```
---
### Virtual Cube "Cube1Cube2Kpi" diagram:

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
c2["Cube1Cube2Kpi"]{
M Cube1_Measures_MeasureCube1 ""
M Cube2_Measures_MeasureCube2 ""
D Cube1Dimension1 ""
D Cube2Dimension1 ""
CM CalculatedValue ""
CM CalculatedTrend ""
KPI Kpi1 "Kpi with all parameters"
}
d20["Dimension1"] {
H HierarchyWithoutHasAll ""
}
h200["HierarchyWithoutHasAll"] {
L Level2 ""
}
d21["Dimension1"] {
H HierarchyWithoutHasAll ""
}
h210["HierarchyWithoutHasAll"] {
L Level2 ""
}
"c2" ||--|| "d20" : "KEY-KEY"
"d20" ||--|| "h200" : "KEY-KEY"
"c2" ||--|| "d21" : "KEY-KEY"
"d21" ||--|| "h210" : "KEY-KEY"
```
---
### Cube Matrix for Cube_with_virtual_cube_with_kpi:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube Cube1: [0.0500, 0.0000]
Cube Cube2: [0.0500, 0.0000]
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
## Validation result for schema Cube_with_virtual_cube_with_kpi
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|DATABASE|Table: Schema must be set|
