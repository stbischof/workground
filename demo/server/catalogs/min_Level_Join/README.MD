# Documentation
### CatalogName : min_Level_Join
## Olap Context Details:
## Schemas:
### Schema Minimal_Single_Hierarchy : 
### Public Dimensions:

    Dimension1

##### Dimension "Dimension1":

Hierarchies:

    Hierarchy1

##### Hierarchy Hierarchy1:

Tables: "Level_2,Level_1"

Levels: "Level1, Level2"

###### Level "Level1" :

    column(s): KEY

###### Level "Level2" :

    column(s): KEY

---
### Cubes :

    Cube1

---
#### Cube "Cube1":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "Dimension1 -> Dimension1":

### Cube "Cube1" diagram:

---

```mermaid
erDiagram
"cube:Cube1"{}

"cube:Cube1" ||--o{ "dim:Dimension1" : "DIM_KEY-KEY"
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
Level_1{
int KEY
string NAME
}
Level_2{
int KEY
string NAME
int L1_KEY
}

"Fact" ||--o{ "Level_2" : "DIM_KEY-KEY"
"Level_2" ||--o{ "Level_1" : "L1_KEY-KEY"
```
---
## Validation result for schema Minimal_Single_Hierarchy
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|SCHEMA|Level: Type should be set for Level1|
|SCHEMA|Level: Type should be set for Level2|
|DATABASE|Table: Schema must be set|
