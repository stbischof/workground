# Documentation
### CatalogName : tutorial_01-04-05_2_Cube_WithSeveralDimensionsWithSameHierarchyName_CalculatedMember
### Schema Minimal_Cubes_With_Two_Dimension_With_Same_Hierarchy_Name_Plus_One_Calculated_Member : 

		
		A minimal cube with two dimension with same hierarchy name plus one calculated member.
		Calculated member should use hierarchy from Dimension1

		
  
### Public Dimensions:

    D0, D1

##### Dimension "D0":

Hierarchies:

    Hierarchy1

##### Hierarchy Hierarchy1:

Tables: "Fact"

Levels: "Level"

###### Level "Level" :

    column(s): KEY

##### Dimension "D1":

Hierarchies:

    Hierarchy1

##### Hierarchy Hierarchy1:

Tables: "Fact"

Levels: "Level"

###### Level "Level" :

    column(s): KEY

---
### Cubes :

    CubeTwoMeasuresPlusOneCalculatedMeasure

---
#### Cube "CubeTwoMeasuresPlusOneCalculatedMeasure":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "Dimension0 -> D0":

##### Dimension: "Dimension1 -> D1":

### Cube "CubeTwoMeasuresPlusOneCalculatedMeasure" diagram:

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
c0["CubeTwoMeasuresPlusOneCalculatedMeasure"]{
M Measure1-Sum ""
M Measure2-Count ""
D Dimension0 ""
D Dimension1 ""
CM CM2 ""
}
d00["D0"] {
H Hierarchy1 ""
}
h000["Hierarchy1"] {
L Level ""
}
d01["D1"] {
H Hierarchy1 ""
}
h010["Hierarchy1"] {
L Level ""
}
"c0" ||--|| "d00" : "KEY-KEY"
"d00" ||--|| "h000" : "KEY-KEY"
"c0" ||--|| "d01" : "KEY-KEY"
"d01" ||--|| "h010" : "KEY-KEY"
```
---
### Cube Matrix for Minimal_Cubes_With_Two_Dimension_With_Same_Hierarchy_Name_Plus_One_Calculated_Member:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube CubeTwoMeasuresPlusOneCalculatedMeasure: [0.1000, 0.0000]
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
double VALUE_NUMERIC "✔"
}

```
---
## Validation result for schema Minimal_Cubes_With_Two_Dimension_With_Same_Hierarchy_Name_Plus_One_Calculated_Member
## <span style='color: red;'>ERROR</span> : 
|Type|   |
|----|---|
|SCHEMA|Dimension must be set for CalculatedMember with name CM2|
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|DATABASE|Table: Schema must be set|
