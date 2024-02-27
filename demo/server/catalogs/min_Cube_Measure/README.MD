# Documentation
### CatalogName : min_Cube_Measure
## Olap Context Details:
## Schemas:
### Schema Minimal_Cubes_With_Measures : 
### Public Dimensions:

    

---
### Cubes :

    CubeOneMeasure, CubeTwoMeasures, CubeTwoMeasuresPlusOneCalculatedMeasure, CubeOneNumericMeasureDifferentDataTypes

---
#### Cube "CubeOneMeasure":

    

##### Table: "Fact"

##### Dimensions:
### Cube "CubeOneMeasure" diagram:

---

```mermaid
erDiagram
"cube:CubeOneMeasure"{}

```
---
---
#### Cube "CubeTwoMeasures":

    

##### Table: "Fact"

##### Dimensions:
### Cube "CubeTwoMeasures" diagram:

---

```mermaid
erDiagram
"cube:CubeTwoMeasures"{}

```
---
---
#### Cube "CubeTwoMeasuresPlusOneCalculatedMeasure":

    

##### Table: "Fact"

##### Dimensions:
### Cube "CubeTwoMeasuresPlusOneCalculatedMeasure" diagram:

---

```mermaid
erDiagram
"cube:CubeTwoMeasuresPlusOneCalculatedMeasure"{}

```
---
---
#### Cube "CubeOneNumericMeasureDifferentDataTypes":

    

##### Table: "Fact"

##### Dimensions:
### Cube "CubeOneNumericMeasureDifferentDataTypes" diagram:

---

```mermaid
erDiagram
"cube:CubeOneNumericMeasureDifferentDataTypes"{}

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
string KEY
int VALUE
null VALUE_NUMERIC
}

```
---
## Validation result for schema Minimal_Cubes_With_Measures
## <span style='color: red;'>ERROR</span> : 
|Type|   |
|----|---|
|SCHEMA|Cube with name CubeOneMeasure must contain Dimensions|
|SCHEMA|Cube with name CubeOneNumericMeasureDifferentDataTypes must contain Dimensions|
|SCHEMA|Cube with name CubeTwoMeasuresPlusOneCalculatedMeasure must contain Dimensions|
|SCHEMA|Cube with name CubeTwoMeasures must contain Dimensions|
