# Documentation
### CatalogName : min_Level_Join
## Olap Context Details:
## Schemas:
### Schema Minimal_Single_Hierarchy : 
### Public Dimensions:

    Dimension1

##### Dimension "Dimension1":

Hierarchies:

    Hierarchy1

##### Hierarchy Hierarchy1:

Tables: "Level_2,Level_1"

Levels: "Level1, Level2"

###### Level "Level1" :

    column(s): KEY

###### Level "Level2" :

    column(s): KEY

---
### Cubes :

    Cube1

---
#### Cube "Cube1":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "Dimension1 -> Dimension1":

### Database :"Fact":

|DIM_KEY|VALUE|
|---|---|
|INTEGER|INTEGER|

"Level_1":

|KEY|NAME|
|---|---|
|INTEGER|STRING|

"Level_2":

|KEY|NAME|L1_KEY|
|---|---|---|
|INTEGER|STRING|INTEGER|

## Validation result for schema Minimal_Single_Hierarchy
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|SCHEMA|Level: Type should be set for Level1|
|SCHEMA|Level: Type should be set for Level2|
|DATABASE|Table: Schema must be set|
