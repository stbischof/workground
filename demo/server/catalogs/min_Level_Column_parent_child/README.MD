# Documentation
### CatalogName : min_Level_Column_parent_child
## Olap Context Details:
## Schemas:
### Schema Minimal_Parent_Child_DifferentValueLevels : 
### Public Dimensions:

    Dimension1

##### Dimension "Dimension1":

Hierarchies:

    Hierarchy1

##### Hierarchy Hierarchy1:

Tables: "Hierarchy"

Levels: "Level1, Level2, Level3"

###### Level "Level1" :

    column(s): PARENT_L1

###### Level "Level2" :

    column(s): PARENT_L2

###### Level "Level3" :

    column(s): KEY

---
### Cubes :

    CubeParentChild

---
#### Cube "CubeParentChild":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "Dimension1 -> Dimension1":

### Database :"Fact":

|DIM_KEY|VALUE|
|---|---|
|INTEGER|INTEGER|

"Hierarchy":

|KEY|NAME|PARENT_L1|PARENT_L2|
|---|---|---|---|
|INTEGER|STRING|INTEGER|INTEGER|

