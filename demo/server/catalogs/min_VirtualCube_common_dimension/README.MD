# Documentation
### CatalogName : min_VirtualCube_common_dimension
## Olap Context Details:
## Schemas:
### Schema Minimal_Virtual_Cubes_With_1_Dimension : 
### Public Dimensions:

    Dimension1

##### Dimension "Dimension1":

Hierarchies:

    Hierarchy1

##### Hierarchy Hierarchy1:

Tables: "Dim_1"

Levels: "Level1"

###### Level "Level1" :

    column(s): KEY

---
### Cubes :

    Cube1, Cube2

---
#### Cube "Cube1":

    

##### Table: "C1_Fact"

##### Dimensions:
##### Dimension: "Dimension1 -> Dimension1":

### Cube "Cube1" diagram:

---

```mermaid
erDiagram
"cube:Cube1"{}

"cube:Cube1" ||--o{ "dim:Dimension1" : "DIM_1_KEY-KEY"
```
---
---
#### Cube "Cube2":

    

##### Table: "C2_Fact"

##### Dimensions:
##### Dimension: "Dimension1 -> Dimension1":

### Cube "Cube2" diagram:

---

```mermaid
erDiagram
"cube:Cube2"{}

"cube:Cube2" ||--o{ "dim:Dimension1" : "DIM_1_KEY-KEY"
```
---
### Database :
---
```mermaid
---
title: Diagram;
---
erDiagram
C1_Fact{
int DIM_1_KEY
int VALUE
}
C2_Fact{
int DIM_1_KEY
int VALUE
}
Dim_1{
int KEY
string NAME
}

"C1_Fact" ||--o{ "Dim_1" : "DIM_1_KEY-KEY"
"C2_Fact" ||--o{ "Dim_1" : "DIM_1_KEY-KEY"
```
---
## Validation result for schema Minimal_Virtual_Cubes_With_1_Dimension
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|SCHEMA|Level: Type should be set for Level1|
|DATABASE|Table: Schema must be set|
