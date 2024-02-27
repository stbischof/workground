# Documentation
### CatalogName : min_Level_parentColumn_parent_child_
## Olap Context Details:
## Schemas:
### Schema Minimal_Parent_Child_Hierarchy : 
### Public Dimensions:

    Dimension1

##### Dimension "Dimension1":

Hierarchies:

    Hierarchy1

##### Hierarchy Hierarchy1:

Tables: "Hier_One_Top_Member"

Levels: "Level"

###### Level "Level" :

    column(s): KEY

---
### Cubes :

    CubeParentChildOneTopMember

---
#### Cube "CubeParentChildOneTopMember":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "Dimension1 -> Dimension1":

### Cube "CubeParentChildOneTopMember" diagram:

---

```mermaid
erDiagram
"cube:CubeParentChildOneTopMember"{}

"cube:CubeParentChildOneTopMember" ||--o{ "dim:Dimension1" : "DIM_KEY-KEY"
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
Hier_One_Top_Member{
int KEY
string NAME
int PARENT_KEY
}

"Fact" ||--o{ "Hier_One_Top_Member" : "DIM_KEY-KEY"
```
---
## Validation result for schema Minimal_Parent_Child_Hierarchy
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|SCHEMA|Level: Type should be set for Level|
|DATABASE|Table: Schema must be set|
