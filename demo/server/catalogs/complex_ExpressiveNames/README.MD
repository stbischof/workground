# Documentation
### CatalogName : complex_ExpressiveNames
## Olap Context Details:
## Schemas:
### Schema ExpressiveNames : 

    
        Expressive names schema.
    
  
### Public Dimensions:

    Dimension1, Dimension2, Dimension3

##### Dimension "Dimension1":

Hierarchies:

    D1H1

##### Hierarchy D1H1:

Tables: "D1H1L1Table"

Levels: "D1H1L1"

###### Level "D1H1L1" :

    column(s): D1H1L1

##### Dimension "Dimension2":

Hierarchies:

    D2H1, D2H2

##### Hierarchy D2H1:

Tables: "D2H1L1Table"

Levels: "D2H1L1"

###### Level "D2H1L1" :

    column(s): D2H1L1

##### Hierarchy D2H2:

Tables: "D2H2L2Table"

Levels: "D2H2L1, D2H2L2"

###### Level "D2H2L1" :

    column(s): D2H2L1

###### Level "D2H2L2" :

    column(s): D2H2L2

##### Dimension "Dimension3":

Hierarchies:

    D3H1, D3H3

##### Hierarchy D3H1:

Tables: "D3H1L1Table"

Levels: "D3H1L1"

###### Level "D3H1L1" :

    column(s): D3H1L1

##### Hierarchy D3H3:

Tables: "D3H3L3Table,D3H3L2Table,D3H3L1Table"

Levels: "D3H3L1, D3H3L2, D3H3L3"

###### Level "D3H3L1" :

    column(s): D3H3L1

###### Level "D3H3L2" :

    column(s): D3H3L2

###### Level "D3H3L3" :

    column(s): D3H3L3

---
### Cubes :

    Cube 1

---
#### Cube "Cube 1":

    Test Cube

##### Table: "Cube1Fact"

##### Dimensions:
##### Dimension: "Dimension1 -> Dimension1":

##### Dimension: "Dimension2 -> Dimension2":

##### Dimension: "Dimension3 -> Dimension3":

### Cube "Cube 1" diagram:

---

```mermaid
erDiagram
"cube:Cube 1"{}

"cube:Cube 1" ||--o{ "dim:Dimension1" : "D1-D1H1L1"
"cube:Cube 1" ||--o{ "dim:Dimension2" : "D2-D2H1L1"
"cube:Cube 1" ||--o{ "dim:Dimension2" : "D2-D2H2L2"
"cube:Cube 1" ||--o{ "dim:Dimension3" : "D3-D3H1L1"
"cube:Cube 1" ||--o{ "dim:Dimension3" : "D3-D3H3L3"
```
---
### Database :
---
```mermaid
---
title: Diagram;
---
erDiagram
Cube1Fact{
string D1
string D2
string D3
int M1
}
D1H1L1Table{
string D1H1L1
string D1H1L1_NAME
string D1H1L1_Ordinal
}
D2H1L1Table{
string D2H1L1
string D2H1L1_NAME
string D2H1L1_Ordinal
}
D2H2L2Table{
string D2H2L2
string D2H2L1
string D2H2L2_NAME
string D2H2L1_NAME
string D2H2L2_Ordinal
string D2H2L1_Ordinal
}
D3H1L1Table{
string D3H1L1
string D3H1L1_NAME
string D3H1L1_Ordinal
}
D3H2L1Table{
string D3H2L1
string D3H2L1_NAME
string D3H2L1_Ordinal
}
D3H2L2Table{
string D3H2L2
string D3H2L1_id
string D3H2L2_NAME
string D3H2L2_Ordinal
}
D3H3L1Table{
string D3H3L1
string D3H3L1_NAME
string D3H3L1_Ordinal
}
D3H3L2Table{
string D3H3L2
string D3H3L1_id
string D3H3L2_NAME
string D3H3L2_Ordinal
}
D3H3L3Table{
string D3H3L3
string D3H3L2_id
string D3H3L3_NAME
string D3H3L3_Ordinal
}

"Cube1Fact" ||--o{ "D1H1L1Table" : "D1-D1H1L1"
"Cube1Fact" ||--o{ "D2H1L1Table" : "D2-D2H1L1"
"Cube1Fact" ||--o{ "D2H2L2Table" : "D2-D2H2L2"
"Cube1Fact" ||--o{ "D3H1L1Table" : "D3-D3H1L1"
"Cube1Fact" ||--o{ "D3H3L3Table" : "D3-D3H3L3"
"D3H3L3Table" ||--o{ "D3H3L2Table" : "D3H3L2_id-D3H3L2"
"D3H3L2Table" ||--o{ "D3H3L1Table" : "D3H3L1_id-D3H3L1"
```
---
## Validation result for schema ExpressiveNames
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|SCHEMA|Level: Type should be set for D2H1L1|
|SCHEMA|Level: Type should be set for D3H3L3|
|SCHEMA|Level: Type should be set for D3H3L2|
|SCHEMA|Level: Type should be set for D2H2L2|
|SCHEMA|Level: Type should be set for D3H3L1|
|SCHEMA|Level: Type should be set for D2H2L1|
|SCHEMA|Level: Type should be set for D1H1L1|
|SCHEMA|Level: Type should be set for D3H1L1|
|DATABASE|Table: Schema must be set|
