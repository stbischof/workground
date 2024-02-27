# Documentation
### CatalogName : min_Dimension_Hierarchy_multiple
## Olap Context Details:
## Schemas:
### Schema Minimal_Multiple_Hierarchy : 
### Public Dimensions:

    Dimension1

##### Dimension "Dimension1":

Hierarchies:

    Hierarchy1, Hierarchy2

##### Hierarchy Hierarchy1:

Tables: "HX_L2,H1_L1"

Levels: "H1_Level1, H1_Level2"

###### Level "H1_Level1" :

    column(s): KEY

###### Level "H1_Level2" :

    column(s): KEY

##### Hierarchy Hierarchy2:

Tables: "HX_L2,H2_L1"

Levels: "H2_Level1, H2_Level2"

###### Level "H2_Level1" :

    column(s): KEY

###### Level "H2_Level2" :

    column(s): KEY

---
### Cubes :

    CubeMultipleHierarchy

---
#### Cube "CubeMultipleHierarchy":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "Dimension1 -> Dimension1":

### Cube "CubeMultipleHierarchy" diagram:

---

```mermaid
erDiagram
"cube:CubeMultipleHierarchy"{}

"cube:CubeMultipleHierarchy" ||--o{ "dim:Dimension1" : "DIM_KEY-KEY"
"cube:CubeMultipleHierarchy" ||--o{ "dim:Dimension1" : "DIM_KEY-KEY"
```
---
### Database :
---
```mermaid
---
title: Diagram;
---
erDiagram
Fact{
int DIM_KEY
int VALUE
}
H1_L1{
int KEY
string NAME
}
H2_L1{
int KEY
string NAME
}
HX_L2{
int KEY
string NAME
int H1L1_KEY
int H2L1_KEY
}

"Fact" ||--o{ "HX_L2" : "DIM_KEY-KEY"
"HX_L2" ||--o{ "H1_L1" : "H1L1_KEY-KEY"
"Fact" ||--o{ "HX_L2" : "DIM_KEY-KEY"
"HX_L2" ||--o{ "H2_L1" : "H2L1_KEY-KEY"
```
---
## Validation result for schema Minimal_Multiple_Hierarchy
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|SCHEMA|Level: Type should be set for H1_Level2|
|SCHEMA|Level: Type should be set for H1_Level1|
|SCHEMA|Level: Type should be set for H2_Level1|
|SCHEMA|Level: Type should be set for H2_Level2|
|DATABASE|Table: Schema must be set|
