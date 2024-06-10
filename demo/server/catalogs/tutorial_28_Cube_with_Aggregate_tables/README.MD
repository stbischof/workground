# Documentation
### CatalogName : tutorial_28_Cube_with_Aggregate_tables
### Schema Cube_with_Aggregate_tables : 

    Aggregate tables are a way to improve Mondrian's performance when the fact table contains
    a huge number of rows: a million or more. An aggregate table is essentially a pre-computed
    summary of the data in the fact table.
  
### Public Dimensions:

    Product

##### Dimension "Product":

Hierarchies:

    Product Family

##### Hierarchy Product Family:

Tables: "PRODUCT,PRODUCT_CLASS"

Levels: "Product Family"

###### Level "Product Family" :

    column(s): PRODUCT_FAMILE

---
### Cubes :

    Sales, Sales_Pattern

---
#### Cube "Sales":

    

##### Table: "SALES_FACT_1997"

##### Dimensions:
##### Dimension: "Product -> Product":

---
#### Cube "Sales_Pattern":

    

##### Table: "SALES_FACT_1997"

##### Dimensions:
##### Dimension: "Product -> Product":

### Cube "Sales" diagram:

---

```mermaid
%%{init: {
"theme": "default",
"themeCSS": [
    ".er.entityBox {stroke: black;}",
    ".er.attributeBoxEven {stroke: black;}",
    ".er.attributeBoxOdd {stroke: black;}",
    "[id^=entity-c] .er.entityBox { fill: lightgreen;} ",
    "[id^=entity-d] .er.entityBox { fill: powderblue;} ",
    "[id^=entity-h] .er.entityBox { fill: pink;} "
]
}}%%
erDiagram
c0["Sales"]{
M Store_Cost ""
D Product ""
}
d00["Product"] {
H Product_Family ""
}
h000["Product_Family"] {
L Product_Family ""
}
"c0" ||--|| "d00" : "PRODUCT_ID-PRODUCT_ID"
"d00" ||--|| "h000" : "PRODUCT_ID-PRODUCT_FAMILE"
```
---
### Cube "Sales_Pattern" diagram:

---

```mermaid
%%{init: {
"theme": "default",
"themeCSS": [
    ".er.entityBox {stroke: black;}",
    ".er.attributeBoxEven {stroke: black;}",
    ".er.attributeBoxOdd {stroke: black;}",
    "[id^=entity-c] .er.entityBox { fill: lightgreen;} ",
    "[id^=entity-d] .er.entityBox { fill: powderblue;} ",
    "[id^=entity-h] .er.entityBox { fill: pink;} "
]
}}%%
erDiagram
c1["Sales_Pattern"]{
M Store_Cost ""
D Product ""
}
d10["Product"] {
H Product_Family ""
}
h100["Product_Family"] {
L Product_Family ""
}
"c1" ||--|| "d10" : "PRODUCT_ID-PRODUCT_ID"
"d10" ||--|| "h100" : "PRODUCT_ID-PRODUCT_FAMILE"
```
---
### Cube Matrix for Cube_with_Aggregate_tables:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube Sales: [0.0500, 0.0000]
Cube Sales_Pattern: [0.0500, 0.0000]
```
---
### Database :
---
```mermaid
---
title: Diagram;
---
erDiagram
"AGG_C_14_SALES_FACT_1997✔"{
int PRODUCT_ID "✔"
double STORE_COST "✔"
int FACT_COUNT "✔"
}
"AGG_C_SPECIAL_SALES_FACT_1997✔"{
int PRODUCT_ID "✔"
double STORE_COST_SUM "✔"
int FACT_COUNT "✔"
}
"AGG_LC_100_SALES_FACT_1997✔"{
int PRODUCT_ID "✔"
double STORE_COST "✔"
int FACT_COUNT "✔"
}
"PRODUCT✔"{
int PRODUCT_CLASS_ID "✔"
int PRODUCT_ID "✔"
string brand_name "✔"
string product_name "✔"
}
"PRODUCT_CLASS✔"{
int PRODUCT_CLASS_ID "✔"
string product_subcategory "✔"
string PRODUCT_CATEGORY "✔"
string PRODUCT_DEPARTMENT "✔"
string PRODUCT_FAMILE "✔"
}
"SALES_FACT_1997✔"{
int PRODUCT_ID "✔"
double STORE_COST "✔"
}
"aGG_PL_01_SALES_FACT_1997✔"{
int PRODUCT_ID "✔"
double STORE_COST_SUM "✔"
int FACT_COUNT "✔"
}

"SALES_FACT_1997✔" ||--o{ "PRODUCT✔" : "PRODUCT_ID-PRODUCT_ID"
"PRODUCT✔" ||--o{ "PRODUCT_CLASS✔" : "PRODUCT_CLASS_ID-PRODUCT_CLASS_ID"
"SALES_FACT_1997✔" ||--o{ "PRODUCT✔" : "PRODUCT_ID-PRODUCT_ID"
"PRODUCT✔" ||--o{ "PRODUCT_CLASS✔" : "PRODUCT_CLASS_ID-PRODUCT_CLASS_ID"
```
---
## Validation result for schema Cube_with_Aggregate_tables
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|DATABASE|Table: Schema must be set|
