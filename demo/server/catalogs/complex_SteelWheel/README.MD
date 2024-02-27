# Documentation
### CatalogName : complex_SteelWheel
## Olap Context Details:
## Schemas:
### Schema SteelWheels : 
### Public Dimensions:

    

---
### Cubes :

    SteelWheelsSales

---
#### Cube "SteelWheelsSales":

    

##### Table: "orderfact"

##### Dimensions:
##### Dimension "Markets":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "customer_w_ter"

Levels: "Territory, Country, State Province, City"

###### Level "Territory" :

    column(s): TERRITORY

###### Level "Country" :

    column(s): COUNTRY

###### Level "State Province" :

    column(s): STATE

###### Level "City" :

    column(s): CITY

##### Dimension "Customers":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "customer_w_ter"

Levels: "Customer"

###### Level "Customer" :

    column(s): CUSTOMERNAME

##### Dimension "Product":

Hierarchies:

    

##### Hierarchy :

Tables: "products"

Levels: "Line, Vendor, Product"

###### Level "Line" :

    column(s): PRODUCTLINE

###### Level "Vendor" :

    column(s): PRODUCTVENDOR

###### Level "Product" :

    column(s): PRODUCTNAME

##### Dimension "Time":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "time"

Levels: "Years, Quarters, Months"

###### Level "Years" :

    column(s): YEAR_ID

###### Level "Quarters" :

    column(s): QTR_NAME

###### Level "Months" :

    column(s): MONTH_NAME

##### Dimension "Order Status":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: ""

Levels: "Type"

###### Level "Type" :

    column(s): STATUS

### Cube "SteelWheelsSales" diagram:

---

```mermaid
erDiagram
"cube:SteelWheelsSales"{}

"cube:SteelWheelsSales" ||--o{ "dim:Markets" : "CUSTOMERNUMBER-CUSTOMERNUMBER"
"cube:SteelWheelsSales" ||--o{ "dim:Customers" : "CUSTOMERNUMBER-CUSTOMERNUMBER"
"cube:SteelWheelsSales" ||--o{ "dim:Product" : "PRODUCTCODE-PRODUCTCODE"
"cube:SteelWheelsSales" ||--o{ "dim:Time" : "TIME_ID-TIME_ID"
"cube:SteelWheelsSales" ||--o{ "dim:Order Status" : "STATUS-STATUS"
```
---
### Database :
---
```mermaid
---
title: Diagram;
---
erDiagram
customer_w_ter{
int CUSTOMERNUMBER
string CUSTOMERNAME
string CONTACTLASTNAME
string CONTACTFIRSTNAME
string PHONE
string ADDRESSLINE1
string ADDRESSLINE2
string CITY
string STATE
string POSTALCODE
string COUNTRY
int EMPLOYEENUMBER
null CREDITLIMIT
string TERRITORY
}
orderfact{
int ORDERNUMBER
string PRODUCTCODE
int QUANTITYORDERED
null PRICEEACH
int ORDERLINENUMBER
null TOTALPRICE
timestamp ORDERDATE
timestamp REQUIREDDATE
timestamp SHIPPEDDATE
string STATUS
string COMMENTS
int CUSTOMERNUMBER
string TIME_ID
int QTR_ID
int MONTH_ID
int YEAR_ID
}
products{
string PRODUCTCODE
string PRODUCTNAME
string PRODUCTLINE
string PRODUCTSCALE
string PRODUCTVENDOR
string PRODUCTDESCRIPTION
int QUANTITYINSTOCK
null BUYPRICE
null MSRP
}
time{
string TIME_ID
int MONTH_ID
int QTR_ID
int YEAR_ID
string MONTH_NAME
string MONTH_DESC
string QTR_NAME
string QTR_DESC
}

"orderfact" ||--o{ "customer_w_ter" : "CUSTOMERNUMBER-CUSTOMERNUMBER"
"orderfact" ||--o{ "customer_w_ter" : "CUSTOMERNUMBER-CUSTOMERNUMBER"
"orderfact" ||--o{ "products" : "PRODUCTCODE-PRODUCTCODE"
"orderfact" ||--o{ "time" : "TIME_ID-TIME_ID"
```
---
## Validation result for schema SteelWheels
## <span style='color: red;'>ERROR</span> : 
|Type|   |
|----|---|
|SCHEMA|Hierarchy: Table field must be empty in dimension with name Product|
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|SCHEMA|Level: Type should be set for Years|
|SCHEMA|Level: Type should be set for Product|
|SCHEMA|Level: Type should be set for City|
|SCHEMA|Level: Type should be set for State Province|
|SCHEMA|Level: Type should be set for Territory|
|SCHEMA|Level: Type should be set for Months|
|SCHEMA|Level: Type should be set for Line|
|SCHEMA|Level: Type should be set for Customer|
|SCHEMA|Level: Type should be set for Vendor|
|SCHEMA|Level: Type should be set for Country|
|SCHEMA|Level: Type should be set for Type|
|SCHEMA|Level: Type should be set for Quarters|
|DATABASE|Table: Schema must be set|
