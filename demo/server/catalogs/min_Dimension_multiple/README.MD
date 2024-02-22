# Documentation
### CatalogName : min_Dimension_multiple
## Olap Context Details:
## Schemas:
### Schema Minimal_Two_Dimensions : 
### Public Dimensions:

    D1, D2

##### Dimension "D1":

Hierarchies:

    D1H1

##### Hierarchy D1H1:

Tables: "DIM_1"

Levels: "D1H1L1"

###### Level "D1H1L1" :

    column(s): KEY

##### Dimension "D2":

Hierarchies:

    D2H1

##### Hierarchy D2H1:

Tables: "DIM_2"

Levels: "D2H1L1"

###### Level "D2H1L1" :

    column(s): KEY

---
### Cubes :

    CubeTwoDimensions

---
#### Cube "CubeTwoDimensions":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "Dimension1 -> D1":

##### Dimension: "Dimension2 -> D2":

### Database :"DIM_1":

|KEY|NAME|
|---|---|
|INTEGER|STRING|

"DIM_2":

|KEY|NAME|
|---|---|
|INTEGER|STRING|

"Fact":

|DIM1_KEY|DIM2_KEY|VALUE|
|---|---|---|
|INTEGER|INTEGER|INTEGER|

