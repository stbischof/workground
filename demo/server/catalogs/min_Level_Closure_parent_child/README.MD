# Documentation
### CatalogName : min_Level_Closure_parent_child
## Olap Context Details:
## Schemas:
### Schema Minimal_Parent_Child_With_Closure : 
### Public Dimensions:

    Dimension1

##### Dimension "Dimension1":

Hierarchies:

    Hierarchy1

##### Hierarchy Hierarchy1:

Tables: "Hierarchy"

Levels: "Level"

###### Level "Level" :

    column(s): KEY

---
### Cubes :

    CubeParentChildClosure

---
#### Cube "CubeParentChildClosure":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "Dimension1 -> Dimension1":

### Cube "CubeParentChildClosure" diagram:

---

```mermaid
erDiagram
"cube:CubeParentChildClosure"{}

"cube:CubeParentChildClosure" ||--o{ "dim:Dimension1" : "DIM_KEY-KEY"
```
---
### Database :
---
```mermaid
---
title: Diagram;
---
erDiagram
Closure{
int PARENT_KEY
int CHILD_KEY
int DIST
}
Fact{
int DIM_KEY
int VALUE
}
Hierarchy{
int KEY
string NAME
int PARENT_KEY
}

"Fact" ||--o{ "Hierarchy" : "DIM_KEY-KEY"
```
---
## Validation result for schema Minimal_Parent_Child_With_Closure
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|SCHEMA|Level: Type should be set for Level|
|DATABASE|Table: Schema must be set|
