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

### Database :"customer_w_ter":

|CUSTOMERNUMBER|CUSTOMERNAME|CONTACTLASTNAME|CONTACTFIRSTNAME|PHONE|ADDRESSLINE1|ADDRESSLINE2|CITY|STATE|POSTALCODE|COUNTRY|EMPLOYEENUMBER|CREDITLIMIT|TERRITORY|
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
|INTEGER|STRING|STRING|STRING|STRING|STRING|STRING|STRING|STRING|STRING|STRING|INTEGER|null|STRING|

"orderfact":

|ORDERNUMBER|PRODUCTCODE|QUANTITYORDERED|PRICEEACH|ORDERLINENUMBER|TOTALPRICE|ORDERDATE|REQUIREDDATE|SHIPPEDDATE|STATUS|COMMENTS|CUSTOMERNUMBER|TIME_ID|QTR_ID|MONTH_ID|YEAR_ID|
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
|INTEGER|STRING|INTEGER|null|INTEGER|null|TIMESTAMP|TIMESTAMP|TIMESTAMP|STRING|STRING|INTEGER|STRING|INTEGER|INTEGER|INTEGER|

"products":

|PRODUCTCODE|PRODUCTNAME|PRODUCTLINE|PRODUCTSCALE|PRODUCTVENDOR|PRODUCTDESCRIPTION|QUANTITYINSTOCK|BUYPRICE|MSRP|
|---|---|---|---|---|---|---|---|---|
|STRING|STRING|STRING|STRING|STRING|STRING|INTEGER|null|null|

"time":

|TIME_ID|MONTH_ID|QTR_ID|YEAR_ID|MONTH_NAME|MONTH_DESC|QTR_NAME|QTR_DESC|
|---|---|---|---|---|---|---|---|
|STRING|INTEGER|INTEGER|INTEGER|STRING|STRING|STRING|STRING|

