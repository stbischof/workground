# Documentation
### CatalogName : min_Level_hideMemberIf
## Olap Context Details:
## Schemas:
### Schema Minimal_Single_Hierarchy_Hidden_Members : 
### Public Dimensions:

    DimensionMembersHiddenIfParentsName, DimensionMembersHiddenIfBlankName, DimensionMembersHiddenMultipleLevels

##### Dimension "DimensionMembersHiddenIfParentsName":

Hierarchies:

    Hierarchy1

##### Hierarchy Hierarchy1:

Tables: "Level_2,Level_1"

Levels: "Level1, Level2"

###### Level "Level1" :

    column(s): KEY

###### Level "Level2" :

    column(s): KEY

##### Dimension "DimensionMembersHiddenIfBlankName":

Hierarchies:

    Hierarchy1

##### Hierarchy Hierarchy1:

Tables: "Level_2_NULL,Level_1"

Levels: "Level1, Level2"

###### Level "Level1" :

    column(s): KEY

###### Level "Level2" :

    column(s): KEY

##### Dimension "DimensionMembersHiddenMultipleLevels":

Hierarchies:

    Hierarchy1

##### Hierarchy Hierarchy1:

Tables: "Level_3_Multiple,Level_2_Multiple,Level_1_Multiple"

Levels: "Level1, Level2, Level3"

###### Level "Level1" :

    column(s): KEY

###### Level "Level2" :

    column(s): KEY

###### Level "Level3" :

    column(s): KEY

---
### Cubes :

    HiddenMembersIfParentName, HiddenMembersIfBlankName, HiddenMembersMultipleLevels

---
#### Cube "HiddenMembersIfParentName":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "DimensionMembersHiddenIfParentsName -> DimensionMembersHiddenIfParentsName":

### Cube "HiddenMembersIfParentName" diagram:

---

```mermaid
erDiagram
"cube:HiddenMembersIfParentName"{}

"cube:HiddenMembersIfParentName" ||--o{ "dim:DimensionMembersHiddenIfParentsName" : "DIM_KEY-KEY"
```
---
---
#### Cube "HiddenMembersIfBlankName":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "DimensionMembersHiddenIfBlankName -> DimensionMembersHiddenIfBlankName":

### Cube "HiddenMembersIfBlankName" diagram:

---

```mermaid
erDiagram
"cube:HiddenMembersIfBlankName"{}

"cube:HiddenMembersIfBlankName" ||--o{ "dim:DimensionMembersHiddenIfBlankName" : "DIM_KEY-KEY"
```
---
---
#### Cube "HiddenMembersMultipleLevels":

    

##### Table: "Fact_Multiple"

##### Dimensions:
##### Dimension: "DimensionMembersHiddenMultipleLevels -> DimensionMembersHiddenMultipleLevels":

### Cube "HiddenMembersMultipleLevels" diagram:

---

```mermaid
erDiagram
"cube:HiddenMembersMultipleLevels"{}

"cube:HiddenMembersMultipleLevels" ||--o{ "dim:DimensionMembersHiddenMultipleLevels" : "DIM_KEY-KEY"
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
Fact_Multiple{
int DIM_KEY
int VALUE
}
Level_1{
int KEY
string NAME
}
Level_1_Multiple{
int KEY
string NAME
}
Level_2{
int KEY
string NAME
int L1_KEY
}
Level_2_Multiple{
int KEY
string NAME
int L1_KEY
}
Level_2_NULL{
int KEY
string NAME
int L1_KEY
}
Level_3_Multiple{
int KEY
string NAME
int L2_KEY
}

"Fact" ||--o{ "Level_2" : "DIM_KEY-KEY"
"Level_2" ||--o{ "Level_1" : "L1_KEY-KEY"
"Fact" ||--o{ "Level_2_NULL" : "DIM_KEY-KEY"
"Level_2_NULL" ||--o{ "Level_1" : "L1_KEY-KEY"
"Fact_Multiple" ||--o{ "Level_3_Multiple" : "DIM_KEY-KEY"
"Level_3_Multiple" ||--o{ "Level_2_Multiple" : "L2_KEY-KEY"
"Level_2_Multiple" ||--o{ "Level_1_Multiple" : "L1_KEY-KEY"
```
---
## Validation result for schema Minimal_Single_Hierarchy_Hidden_Members
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|SCHEMA|Level: Type should be set for Level3|
|SCHEMA|Level: Type should be set for Level1|
|SCHEMA|Level: Type should be set for Level2|
|DATABASE|Table: Schema must be set|
