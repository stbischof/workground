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

