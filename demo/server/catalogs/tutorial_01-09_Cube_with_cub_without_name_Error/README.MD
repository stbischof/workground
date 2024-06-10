# Documentation
### CatalogName : tutorial_01-09_Cube_with_cub_without_name_Error
### Schema Minimal_Cube_with_cub_without_name : 
---
### Cubes :

    null

---
#### Cube "":

    

##### Table: "Fact"

##### Dimensions:
##### Dimension "Dimension":

Hierarchies:

    HierarchyWithHasAll

##### Hierarchy HierarchyWithHasAll:

Tables: "Fact"

Levels: "Level"

###### Level "Level" :

    column(s): KEY

### Cube Matrix for Minimal_Cube_with_cub_without_name:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube _: [0.0500, 0.0000]
```
---
### Database :
---
```mermaid
---
title: Diagram;
---
erDiagram
"Fact✔"{
string KEY "✔"
int VALUE "✔"
}

```
---
## Validation result for schema Minimal_Cube_with_cub_without_name
## <span style='color: red;'>ERROR</span> : 
|Type|   |
|----|---|
|SCHEMA|Cube name must be set|
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|DATABASE|Table: Schema must be set|
