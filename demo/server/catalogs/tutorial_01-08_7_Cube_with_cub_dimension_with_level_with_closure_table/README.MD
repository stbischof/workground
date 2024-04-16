# Documentation
### CatalogName : tutorial_01-08_7_Cube_with_cub_dimension_with_level_with_closure_table
### Schema Minimal_Cube_with_cube_dimension_level_with_closure_table : 

    
		A basic OLAP schema with a level

		Data cubes (<Cube>) are defined in an OLAP schema (<Schema>). Within the schema the name of each data cube must be unique.
		This example schema contains one cube named "Cube".

    A closure table is a SQL table which contains a record for every employee/supervisor relationship,
    regardless of depth. (In mathematical terms, this is called the 'reflexive transitive closure'
    of the employee/supervisor relationship.
    The distance column is not strictly required, but it makes it easier to populate the table.)

		
  
---
### Cubes :

    Cube

---
#### Cube "Cube":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension "Employees":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "Fact"

Levels: "Employee Id"

###### Level "Employee Id" :

    column(s): employee_id

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
D Employees ""
}
d00["Employees"] {
H _ ""
}
h000["_"] {
L Employee_Id ""
}
"c0" ||--|| "d00" : "employee_id-employee_id"
"d00" ||--|| "h000" : "employee_id-employee_id"
```
---
### Cube Matrix for Minimal_Cube_with_cube_dimension_level_with_closure_table:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube Cube: [0.0500, 0.0000]
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
int employee_id "✔"
string full_name "✔"
int supervisor_id "✔"
string marital_status "✔"
string position_title "✔"
string gender "✔"
int salary "✔"
int education_level "✔"
string management_role "✔"
}
"employee_closure✔"{
int supervisor_id "✔"
int employee_id "✔"
int distance "✔"
}

```
---
## Validation result for schema Minimal_Cube_with_cube_dimension_level_with_closure_table
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|DATABASE|Table: Schema must be set|
