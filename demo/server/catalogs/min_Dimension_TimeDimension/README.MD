# Documentation
### CatalogName : min_Dimension_TimeDimension
## Olap Context Details:
## Schemas:
### Schema Minimal_Time_Dimension : 
### Public Dimensions:

    Time

##### Dimension "Time":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "Fact"

Levels: "Years, Quarters, Months"

###### Level "Years" :

    column(s): YEAR_ID

###### Level "Quarters" :

    column(s): QTR_NAME

###### Level "Months" :

    column(s): MONTH_NAME

---
### Cubes :

    CubeTimeDimension

---
#### Cube "CubeTimeDimension":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension: "Time -> Time":

### Database :"Fact":

|DATE_KEY|VALUE|YEAR_ID|QTR_ID|QTR_NAME|MONTH_ID|MONTH_NAME|
|---|---|---|---|---|---|---|
|TIMESTAMP|INTEGER|INTEGER|STRING|STRING|STRING|STRING|

## Validation result for schema Minimal_Time_Dimension
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|SCHEMA|Level: Type should be set for Years|
|SCHEMA|Level: Type should be set for Months|
|SCHEMA|Level: Type should be set for Quarters|
|DATABASE|Table: Schema must be set|
