# Documentation
###CatalogName : min_Dimension_multiple

## Olap Context Details:

## Schemas:

### Schema Minimal_Two_Dimensions:

### Public Dimensions:
D1, D2

##### Public Dimension D1:

Hierarchies:
D1H1

##### Hierarchy D1H1:

Table: DIM_1

Levels: D1H1L1

##### Public Dimension D2:

Hierarchies:
D1H1

##### Hierarchy D1H1:

Table: DIM_1

Levels: D1H1L1

___

### Cubes :

CubeTwoDimensions

#### Cube CubeTwoDimensions:

Description:

##### Table: Fact

##### Dimensions:

Dimension1 -> D1

Dimension2 -> D2

##### Measures
Measure1 -> sum(VALUE)

___
### Roles:

---

### Database :

Fact:

|DIM1_KEY|DIM2_KEY|VALUE|
|---|---|---|
|INTEGER|INTEGER|INTEGER|
|1|1|65|
|2|1|61|
|3|1|64|
|1|2|38|
|2|2|32|
|3|2|36|
|1|3|42|
|2|3|40|

DIM_1 :

|KEY|NAME|
|---|---|
|INTEGER|STRING|
|1|Hamburg|
|2|München|
|3|Berlin|

DIM_2 :

|KEY|NAME|
|---|---|
|INTEGER|STRING|
|1|QOTSA|
|2|NBT|
|3|RB|
