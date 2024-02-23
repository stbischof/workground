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

### Database :"Closure":

|PARENT_KEY|CHILD_KEY|DIST|
|---|---|---|
|INTEGER|INTEGER|INTEGER|

"Fact":

|DIM_KEY|VALUE|
|---|---|
|INTEGER|INTEGER|

"Hierarchy":

|KEY|NAME|PARENT_KEY|
|---|---|---|
|INTEGER|STRING|INTEGER|

## Validation result for schema Minimal_Parent_Child_With_Closure
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|SCHEMA|Level: Type should be set for Level|
|DATABASE|Table: Schema must be set|
