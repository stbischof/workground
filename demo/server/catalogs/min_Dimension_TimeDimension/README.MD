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

### Cube "CubeTimeDimension" diagram:

---

```mermaid
erDiagram
"cube:CubeTimeDimension"{}

"cube:CubeTimeDimension" ||--o{ "dim:Time" : "DATE_KEY-DATE_KEY"
```
---
### Database :
---
```mermaid
---
title: Diagram;
---
erDiagram
Fact{
timestamp DATE_KEY
int VALUE
int YEAR_ID
string QTR_ID
string QTR_NAME
string MONTH_ID
string MONTH_NAME
}

```
---
## Validation result for schema Minimal_Time_Dimension
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|SCHEMA|Level: Type should be set for Years|
|SCHEMA|Level: Type should be set for Months|
|SCHEMA|Level: Type should be set for Quarters|
|DATABASE|Table: Schema must be set|
