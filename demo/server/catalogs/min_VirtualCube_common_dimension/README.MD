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

---
#### Cube "Cube2":

    

##### Table: "C2_Fact"

##### Dimensions:
##### Dimension: "Dimension1 -> Dimension1":

### Database :"C1_Fact":

|DIM_1_KEY|VALUE|
|---|---|
|INTEGER|INTEGER|

"C2_Fact":

|DIM_1_KEY|VALUE|
|---|---|
|INTEGER|INTEGER|

"Dim_1":

|KEY|NAME|
|---|---|
|INTEGER|STRING|

