# Documentation
### CatalogName : min_Dimension_Hierarchy_multiple
## Olap Context Details:
## Schemas:
### Schema Minimal_Multiple_Hierarchy : 
### Public Dimensions:

    Dimension1

##### Dimension "Dimension1":

Hierarchies:

    Hierarchy1, Hierarchy2

##### Hierarchy Hierarchy1:

Tables: "HX_L2,H1_L1"

Levels: "H1_Level1, H1_Level2"

###### Level "H1_Level1" :

    column(s): KEY

###### Level "H1_Level2" :

    column(s): KEY

##### Hierarchy Hierarchy2:

Tables: "HX_L2,H2_L1"

Levels: "H2_Level1, H2_Level2"

###### Level "H2_Level1" :

    column(s): KEY

###### Level "H2_Level2" :

    column(s): KEY

---
### Cubes :

    CubeMultipleHierarchy

---
#### Cube "CubeMultipleHierarchy":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "Dimension1 -> Dimension1":

### Database :"Fact":

|DIM_KEY|VALUE|
|---|---|
|INTEGER|INTEGER|

"H1_L1":

|KEY|NAME|
|---|---|
|INTEGER|STRING|

"H2_L1":

|KEY|NAME|
|---|---|
|INTEGER|STRING|

"HX_L2":

|KEY|NAME|H1L1_KEY|H2L1_KEY|
|---|---|---|---|
|INTEGER|STRING|INTEGER|INTEGER|

## Validation result for schema Minimal_Multiple_Hierarchy
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|SCHEMA|Level: Type should be set for H1_Level2|
|SCHEMA|Level: Type should be set for H1_Level1|
|SCHEMA|Level: Type should be set for H2_Level1|
|SCHEMA|Level: Type should be set for H2_Level2|
|DATABASE|Table: Schema must be set|
