# Documentation
### CatalogName : min_Level_hideMemberIf
## Olap Context Details:
## Schemas:
### Schema Minimal_Single_Hierarchy_Hidden_Members : 
### Public Dimensions:

    DimensionMembersHiddenIfParentsName, DimensionMembersHiddenIfBlankName, DimensionMembersHiddenMultipleLevels

##### Dimension "DimensionMembersHiddenIfParentsName":

Hierarchies:

    Hierarchy1

##### Hierarchy Hierarchy1:

Tables: "Level_2,Level_1"

Levels: "Level1, Level2"

###### Level "Level1" :

    column(s): KEY

###### Level "Level2" :

    column(s): KEY

##### Dimension "DimensionMembersHiddenIfBlankName":

Hierarchies:

    Hierarchy1

##### Hierarchy Hierarchy1:

Tables: "Level_2_NULL,Level_1"

Levels: "Level1, Level2"

###### Level "Level1" :

    column(s): KEY

###### Level "Level2" :

    column(s): KEY

##### Dimension "DimensionMembersHiddenMultipleLevels":

Hierarchies:

    Hierarchy1

##### Hierarchy Hierarchy1:

Tables: "Level_3_Multiple,Level_2_Multiple,Level_1_Multiple"

Levels: "Level1, Level2, Level3"

###### Level "Level1" :

    column(s): KEY

###### Level "Level2" :

    column(s): KEY

###### Level "Level3" :

    column(s): KEY

---
### Cubes :

    HiddenMembersIfParentName, HiddenMembersIfBlankName, HiddenMembersMultipleLevels

---
#### Cube "HiddenMembersIfParentName":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "DimensionMembersHiddenIfParentsName -> DimensionMembersHiddenIfParentsName":

---
#### Cube "HiddenMembersIfBlankName":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "DimensionMembersHiddenIfBlankName -> DimensionMembersHiddenIfBlankName":

---
#### Cube "HiddenMembersMultipleLevels":

    

##### Table: "Fact_Multiple"

##### Dimensions:
##### Dimension: "DimensionMembersHiddenMultipleLevels -> DimensionMembersHiddenMultipleLevels":

### Database :"Fact":

|DIM_KEY|VALUE|
|---|---|
|INTEGER|INTEGER|

"Fact_Multiple":

|DIM_KEY|VALUE|
|---|---|
|INTEGER|INTEGER|

"Level_1":

|KEY|NAME|
|---|---|
|INTEGER|STRING|

"Level_1_Multiple":

|KEY|NAME|
|---|---|
|INTEGER|STRING|

"Level_2":

|KEY|NAME|L1_KEY|
|---|---|---|
|INTEGER|STRING|INTEGER|

"Level_2_Multiple":

|KEY|NAME|L1_KEY|
|---|---|---|
|INTEGER|STRING|INTEGER|

"Level_2_NULL":

|KEY|NAME|L1_KEY|
|---|---|---|
|INTEGER|STRING|INTEGER|

"Level_3_Multiple":

|KEY|NAME|L2_KEY|
|---|---|---|
|INTEGER|STRING|INTEGER|

## Validation result for schema Minimal_Single_Hierarchy_Hidden_Members
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|SCHEMA|Level: Type should be set for Level3|
|SCHEMA|Level: Type should be set for Level1|
|SCHEMA|Level: Type should be set for Level2|
|DATABASE|Table: Schema must be set|
