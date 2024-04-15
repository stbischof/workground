# Documentation
### CatalogName : tutorial_01-08_5_Cube_with_cub_dimension_with_level_expressions
### Schema Minimal_Cube_with_cube_dimension_level_with_expressions : 

    
		A basic OLAP schema with a level with expressions

		Data cubes (<Cube>) are defined in an OLAP schema (<Schema>). Within the schema the name of each data cube must be unique.
		This example schema contains one cube named "Cube".

		A cube is based on a fact table (<Table>) which refers to a database table containing one or more measurements to be aggregated (and optionally further columns defining factual dimensions).
		In this case the database table representing the fact table is named "Fact" in the database, which is adressed in the name attribute within the <Table> tag.

		Each measurement of the cube is defined in a separate <Measure> element.
		The measurement in this example cube is named "Measure" (name attribute). It corresponds to the "VALUE" column (column attribute) in the database table "Fact" and is aggregated by summation (aggregator attribute).
		Level is defined in <Level> element.
		Property is defined in <Property> element inside <Level> element. Property we can see in cell tooltip in excel

		
  
---
### Cubes :

    Cube

---
#### Cube "Cube":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension "Dimension":

Hierarchies:

    HierarchyWithHasAll

##### Hierarchy HierarchyWithHasAll:

Tables: "Fact"

Levels: "Level1, Level2"

###### Level "Level1" :

    column(s): KEY

###### Level "Level2" :

    column(s): null

### Cube "Cube" diagram:

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
c0["Cube"]{
M Measure ""
D Dimension ""
}
d00["Dimension"] {
H HierarchyWithHasAll ""
}
h000["HierarchyWithHasAll"] {
L Level1 ""
L Level2 ""
}
"c0" ||--|| "d00" : "KEY"
"d00" ||--|| "h000" : "KEY-KEY"
"d00" ||--|| "h000" : "KEY-"
```
---
### Cube Matrix for Minimal_Cube_with_cube_dimension_level_with_expressions:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube Cube: [0.1000, 0.0000]
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
string KEY1 "✔"
int VALUE "✔"
}

```
---
## Validation result for schema Minimal_Cube_with_cube_dimension_level_with_expressions
## <span style='color: red;'>ERROR</span> : 
|Type|   |
|----|---|
|SCHEMA|Level: Column must be set for hierarchy with name HierarchyWithHasAll|
|DATABASE|Syntax error in SQL statement "[*]""KEY"" || '___' || ""KEY1"""; SQL statement:"KEY" || '___' || "KEY1" [42000-224]|
|DATABASE|Syntax error in SQL statement "[*]""KEY1"" || ' ' || ""KEY"""; SQL statement:"KEY1" || ' ' || "KEY" [42000-224]|
|DATABASE|Syntax error in SQL statement "[*]""KEY1"" || '___' || ""KEY"""; SQL statement:"KEY1" || '___' || "KEY" [42000-224]|
|DATABASE|Syntax error in SQL statement "[*]""KEY"" || ' ' || ""KEY1"""; SQL statement:"KEY" || ' ' || "KEY1" [42000-224]|
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|DATABASE|Table: Schema must be set|
