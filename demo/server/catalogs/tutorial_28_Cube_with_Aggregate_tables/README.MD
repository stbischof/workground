# Documentation
### CatalogName : tutorial_28_Cube_with_Aggregate_tables
### Schema Cube_with_Aggregate_tables : 

    Aggregate tables are a way to improve Mondrian's performance when the fact table contains
    a huge number of rows: a million or more. An aggregate table is essentially a pre-computed
    summary of the data in the fact table.
  
### Public Dimensions:

    Product, Time

##### Dimension "Product":

Hierarchies:

    Product Family

##### Hierarchy Product Family:

Tables: "PRODUCT,PRODUCT_CLASS"

Levels: "Product Family"

###### Level "Product Family" :

    column(s): PRODUCT_FAMILE

##### Dimension "Time":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "TIME_BY_DAY"

Levels: "Year, QUARTER, Month"

###### Level "Year" :

    column(s): THE_YEAR

###### Level "QUARTER" :

    column(s): QUARTER

###### Level "Month" :

    column(s): MONTH_OF_YEAR

---
### Cubes :

    Sales, Sales_Pattern

---
#### Cube "Sales":

    

##### Table: "SALES_FACT_1997"

##### Dimensions:
##### Dimension: "Product -> Product":

##### Dimension: "Time -> Time":

---
#### Cube "Sales_Pattern":

    

##### Table: "SALES_FACT_1997"

##### Dimensions:
##### Dimension: "Product -> Product":

##### Dimension: "Time -> Time":

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
M Store_Sales ""
D Product ""
D Time ""
}
d00["Product"] {
H Product_Family ""
}
h000["Product_Family"] {
L Product_Family ""
}
d01["Time"] {
H _ ""
}
h010["_"] {
L Year ""
L QUARTER ""
L Month ""
}
"c0" ||--|| "d00" : "PRODUCT_ID-PRODUCT_ID"
"d00" ||--|| "h000" : "PRODUCT_ID-PRODUCT_FAMILE"
"c0" ||--|| "d01" : "TIME_ID-TIME_ID"
"d01" ||--|| "h010" : "TIME_ID-THE_YEAR"
"d01" ||--|| "h010" : "TIME_ID-QUARTER"
"d01" ||--|| "h010" : "TIME_ID-MONTH_OF_YEAR"
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
M Store_Sales ""
D Product ""
D Time ""
}
d10["Product"] {
H Product_Family ""
}
h100["Product_Family"] {
L Product_Family ""
}
d11["Time"] {
H _ ""
}
h110["_"] {
L Year ""
L QUARTER ""
L Month ""
}
"c1" ||--|| "d10" : "PRODUCT_ID-PRODUCT_ID"
"d10" ||--|| "h100" : "PRODUCT_ID-PRODUCT_FAMILE"
"c1" ||--|| "d11" : "TIME_ID-TIME_ID"
"d11" ||--|| "h110" : "TIME_ID-THE_YEAR"
"d11" ||--|| "h110" : "TIME_ID-QUARTER"
"d11" ||--|| "h110" : "TIME_ID-MONTH_OF_YEAR"
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

Cube Sales: [0.2000, 0.0000]
Cube Sales_Pattern: [0.2000, 0.0000]
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
int MONTH_OF_YEAR "✔"
string QUARTER "✔"
int THE_YEAR "✔"
null STORE_SALES "✔"
null STORE_COST "✔"
null UNIT_SALES "✔"
int FACT_COUNT "✔"
}
"AGG_C_SPECIAL_SALES_FACT_1997✔"{
int PRODUCT_ID "✔"
int TIME_MONTH "✔"
string TIME_QUARTER "✔"
int TIME_YEAR "✔"
null STORE_SALES_SUM "✔"
null STORE_COST_SUM "✔"
int FACT_COUNT "✔"
}
"AGG_LC_100_SALES_FACT_1997✔"{
int PRODUCT_ID "✔"
string QUARTER "✔"
int THE_YEAR "✔"
null STORE_SALES "✔"
null STORE_COST "✔"
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
int TIME_ID "✔"
null STORE_SALES "✔"
null STORE_COST "✔"
}
"TIME_BY_DAY✔"{
int TIME_ID "✔"
timestamp the_date "✔"
string the_day "✔"
string the_month "✔"
int THE_YEAR "✔"
int day_of_month "✔"
int week_of_year "✔"
int MONTH_OF_YEAR "✔"
string QUARTER "✔"
string fiscal_period "✔"
}
"aGG_PL_01_SALES_FACT_1997✔"{
int PRODUCT_ID "✔"
int TIME_ID "✔"
null STORE_SALES_SUM "✔"
null STORE_COST_SUM "✔"
int FACT_COUNT "✔"
}

"SALES_FACT_1997✔" ||--o{ "PRODUCT✔" : "PRODUCT_ID-PRODUCT_ID"
"PRODUCT✔" ||--o{ "PRODUCT_CLASS✔" : "PRODUCT_CLASS_ID-PRODUCT_CLASS_ID"
"SALES_FACT_1997✔" ||--o{ "TIME_BY_DAY✔" : "TIME_ID-TIME_ID"
"SALES_FACT_1997✔" ||--o{ "PRODUCT✔" : "PRODUCT_ID-PRODUCT_ID"
"PRODUCT✔" ||--o{ "PRODUCT_CLASS✔" : "PRODUCT_CLASS_ID-PRODUCT_CLASS_ID"
"SALES_FACT_1997✔" ||--o{ "TIME_BY_DAY✔" : "TIME_ID-TIME_ID"
```
---
## Validation result for schema Cube_with_Aggregate_tables
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|DATABASE|Table: Schema must be set|
