# Documentation
### CatalogName : complex_FoodMart
### Schema FoodMart : 
### Public Dimensions:

    Store, Store Size in SQFT, Store Type, Time, Product, Warehouse

##### Dimension "Store":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "store"

Levels: "Store Country, Store State, Store City, Store Name"

###### Level "Store Country" :

    column(s): store_country

###### Level "Store State" :

    column(s): store_state

###### Level "Store City" :

    column(s): store_city

###### Level "Store Name" :

    column(s): store_name

##### Dimension "Store Size in SQFT":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "store"

Levels: "Store Sqft"

###### Level "Store Sqft" :

    column(s): store_sqft

##### Dimension "Store Type":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "store"

Levels: "Store Type"

###### Level "Store Type" :

    column(s): store_type

##### Dimension "Time":

Hierarchies:

    Hierarchy0, Weekly

##### Hierarchy Hierarchy0:

Tables: "time_by_day"

Levels: "Year, Quarter, Month"

###### Level "Year" :

    column(s): the_year

###### Level "Quarter" :

    column(s): quarter

###### Level "Month" :

    column(s): month_of_year

##### Hierarchy Weekly:

Tables: "time_by_day"

Levels: "Year, Week, Day"

###### Level "Year" :

    column(s): the_year

###### Level "Week" :

    column(s): week_of_year

###### Level "Day" :

    column(s): day_of_month

##### Dimension "Product":

Hierarchies:

    Hierarchy0, Product Family, Product Department, Product Category, Product Subcategory, Brand Name, Product Name

##### Hierarchy Hierarchy0:

Tables: "product,product_class"

Levels: "Product Family, Product Department, Product Category, Product Subcategory, Brand Name, Product Name"

###### Level "Product Family" :

    column(s): product_family

###### Level "Product Department" :

    column(s): product_department

###### Level "Product Category" :

    column(s): product_category

###### Level "Product Subcategory" :

    column(s): product_subcategory

###### Level "Brand Name" :

    column(s): brand_name

###### Level "Product Name" :

    column(s): product_name

##### Hierarchy Product Family:

Tables: "product,product_class"

Levels: "Product Family"

###### Level "Product Family" :

    column(s): product_family

##### Hierarchy Product Department:

Tables: "product,product_class"

Levels: "Product Department"

###### Level "Product Department" :

    column(s): product_department

##### Hierarchy Product Category:

Tables: "product,product_class"

Levels: "Product Category"

###### Level "Product Category" :

    column(s): product_category

##### Hierarchy Product Subcategory:

Tables: "product,product_class"

Levels: "Product Subcategory"

###### Level "Product Subcategory" :

    column(s): product_subcategory

##### Hierarchy Brand Name:

Tables: "product,product_class"

Levels: "Brand Name"

###### Level "Brand Name" :

    column(s): brand_name

##### Hierarchy Product Name:

Tables: "product,product_class"

Levels: "Product Name"

###### Level "Product Name" :

    column(s): product_name

##### Dimension "Warehouse":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "warehouse"

Levels: "Country, State Province, City, Warehouse Name"

###### Level "Country" :

    column(s): warehouse_country

###### Level "State Province" :

    column(s): warehouse_state_province

###### Level "City" :

    column(s): warehouse_city

###### Level "Warehouse Name" :

    column(s): warehouse_name

---
### Cubes :

    Sales, Warehouse, Store, HR, Sales Ragged, Sales 2

---
#### Cube "Sales":

    

##### Table: "sales_fact_1997"

##### Dimensions:
##### Dimension: "Store -> Store":

##### Dimension: "Store Size in SQFT -> Store Size in SQFT":

##### Dimension: "Store Type -> Store Type":

##### Dimension: "Time -> Time":

##### Dimension: "Product -> Product":

##### Dimension "Promotion Media":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "promotion"

Levels: "Media Type"

###### Level "Media Type" :

    column(s): media_type

##### Dimension "Promotions":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "promotion"

Levels: "Promotion Name"

###### Level "Promotion Name" :

    column(s): promotion_name

##### Dimension "Customers":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "customer"

Levels: "Country, State Province, City, Name"

###### Level "Country" :

    column(s): country

###### Level "State Province" :

    column(s): state_province

###### Level "City" :

    column(s): city

###### Level "Name" :

    column(s): customer_id

##### Dimension "Education Level":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "customer"

Levels: "Education Level"

###### Level "Education Level" :

    column(s): education

##### Dimension "Gender":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "customer"

Levels: "Gender"

###### Level "Gender" :

    column(s): gender

##### Dimension "Marital Status":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "customer"

Levels: "Marital Status"

###### Level "Marital Status" :

    column(s): marital_status

##### Dimension "Yearly Income":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "customer"

Levels: "Yearly Income"

###### Level "Yearly Income" :

    column(s): yearly_income

---
#### Cube "Warehouse":

    

##### Table: "inventory_fact_1997"

##### Dimensions:
##### Dimension: "Store -> Store":

##### Dimension: "Store Size in SQFT -> Store Size in SQFT":

##### Dimension: "Store Type -> Store Type":

##### Dimension: "Time -> Time":

##### Dimension: "Product -> Product":

##### Dimension: "Warehouse -> Warehouse":

---
#### Cube "Store":

    

##### Table: "store"

##### Dimensions:
##### Dimension "Store Type":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: ""

Levels: "Store Type"

###### Level "Store Type" :

    column(s): store_type

##### Dimension: "Store -> Store":

##### Dimension "Has coffee bar":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: ""

Levels: "Has coffee bar"

###### Level "Has coffee bar" :

    column(s): coffee_bar

---
#### Cube "HR":

    

##### Table: "salary"

##### Dimensions:
##### Dimension "Time":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "time_by_day"

Levels: "Year, Quarter, Month"

###### Level "Year" :

    column(s): the_year

###### Level "Quarter" :

    column(s): quarter

###### Level "Month" :

    column(s): month_of_year

##### Dimension "Store":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "employee,store"

Levels: "Store Country, Store State, Store City, Store Name"

###### Level "Store Country" :

    column(s): store_country

###### Level "Store State" :

    column(s): store_state

###### Level "Store City" :

    column(s): store_city

###### Level "Store Name" :

    column(s): store_name

##### Dimension "Pay Type":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "employee,position"

Levels: "Pay Type"

###### Level "Pay Type" :

    column(s): pay_type

##### Dimension "Store Type":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "employee,store"

Levels: "Store Type"

###### Level "Store Type" :

    column(s): store_type

##### Dimension "Position":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "employee"

Levels: "Management Role, Position Title"

###### Level "Management Role" :

    column(s): management_role

###### Level "Position Title" :

    column(s): position_title

##### Dimension "Department":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "department"

Levels: "Department Description"

###### Level "Department Description" :

    column(s): department_id

##### Dimension "Employees":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "employee"

Levels: "Employee Id"

###### Level "Employee Id" :

    column(s): employee_id

---
#### Cube "Sales Ragged":

    

##### Table: "sales_fact_1997"

##### Dimensions:
##### Dimension "Store":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "store_ragged"

Levels: "Store Country, Store State, Store City, Store Name"

###### Level "Store Country" :

    column(s): store_country

###### Level "Store State" :

    column(s): store_state

###### Level "Store City" :

    column(s): store_city

###### Level "Store Name" :

    column(s): store_name

##### Dimension "Geography":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "store_ragged"

Levels: "Country, State, City"

###### Level "Country" :

    column(s): store_country

###### Level "State" :

    column(s): store_state

###### Level "City" :

    column(s): store_city

##### Dimension: "Store Size in SQFT -> Store Size in SQFT":

##### Dimension: "Store Type -> Store Type":

##### Dimension: "Time -> Time":

##### Dimension: "Product -> Product":

##### Dimension "Promotion Media":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "promotion"

Levels: "Media Type"

###### Level "Media Type" :

    column(s): media_type

##### Dimension "Promotions":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "promotion"

Levels: "Promotion Name"

###### Level "Promotion Name" :

    column(s): promotion_name

##### Dimension "Customers":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "customer"

Levels: "Country, State Province, City, Name"

###### Level "Country" :

    column(s): country

###### Level "State Province" :

    column(s): state_province

###### Level "City" :

    column(s): city

###### Level "Name" :

    column(s): 

##### Dimension "Education Level":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "customer"

Levels: "Education Level"

###### Level "Education Level" :

    column(s): education

##### Dimension "Gender":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "customer"

Levels: "Gender"

###### Level "Gender" :

    column(s): gender

##### Dimension "Marital Status":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "customer"

Levels: "Marital Status"

###### Level "Marital Status" :

    column(s): marital_status

##### Dimension "Yearly Income":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "customer"

Levels: "Yearly Income"

###### Level "Yearly Income" :

    column(s): yearly_income

---
#### Cube "Sales 2":

    

##### Table: "sales_fact_1997"

##### Dimensions:
##### Dimension: "Time -> Time":

##### Dimension: "Product -> Product":

##### Dimension "Gender":

Hierarchies:

    Hierarchy0

##### Hierarchy Hierarchy0:

Tables: "customer"

Levels: "Gender"

###### Level "Gender" :

    column(s): gender

### Roles :##### Role: "California manager"

##### Role: "No HR Cube"

##### Role: "Administrator"

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
M Unit_Sales ""
M Store_Cost ""
M Store_Sales ""
M Sales_Count ""
M Customer_Count ""
M Promotion_Sales ""
D Store ""
D Store_Size_in_SQFT ""
D Store_Type ""
D Time ""
D Product ""
D Promotion_Media ""
D Promotions ""
D Customers ""
D Education_Level ""
D Gender ""
D Marital_Status ""
D Yearly_Income ""
CM Profit ""
CM Profit_last_Period ""
CM Profit_Growth ""
}
d00["Store"] {
H _ ""
}
h000["_"] {
L Store_Country ""
L Store_State ""
L Store_City ""
L Store_Name ""
}
d01["Store Size in SQFT"] {
H _ ""
}
h010["_"] {
L Store_Sqft ""
}
d02["Store Type"] {
H _ ""
}
h020["_"] {
L Store_Type ""
}
d03["Time"] {
H _ ""
H Weekly ""
}
h030["_"] {
L Year ""
L Quarter ""
L Month ""
}
h031["Weekly"] {
L Year ""
L Week ""
L Day ""
}
d04["Product"] {
H _ ""
H Product_Family ""
H Product_Department ""
H Product_Category ""
H Product_Subcategory ""
H Brand_Name ""
H Product_Name ""
}
h040["_"] {
L Product_Family ""
L Product_Department ""
L Product_Category ""
L Product_Subcategory ""
L Brand_Name ""
L Product_Name ""
}
h041["Product_Family"] {
L Product_Family ""
}
h042["Product_Department"] {
L Product_Department ""
}
h043["Product_Category"] {
L Product_Category ""
}
h044["Product_Subcategory"] {
L Product_Subcategory ""
}
h045["Brand_Name"] {
L Brand_Name ""
}
h046["Product_Name"] {
L Product_Name ""
}
d05["Promotion Media"] {
H _ ""
}
h050["_"] {
L Media_Type ""
}
d06["Promotions"] {
H _ ""
}
h060["_"] {
L Promotion_Name ""
}
d07["Customers"] {
H _ ""
}
h070["_"] {
L Country ""
L State_Province ""
L City ""
L Name ""
}
d08["Education Level"] {
H _ ""
}
h080["_"] {
L Education_Level ""
}
d09["Gender"] {
H _ ""
}
h090["_"] {
L Gender ""
}
d010["Marital Status"] {
H _ ""
}
h0100["_"] {
L Marital_Status ""
}
d011["Yearly Income"] {
H _ ""
}
h0110["_"] {
L Yearly_Income ""
}
"c0" ||--|| "d00" : "store_id-store_id"
"d00" ||--|| "h000" : "store_id-store_country"
"d00" ||--|| "h000" : "store_id-store_state"
"d00" ||--|| "h000" : "store_id-store_city"
"d00" ||--|| "h000" : "store_id-store_name"
"c0" ||--|| "d01" : "store_id-store_id"
"d01" ||--|| "h010" : "store_id-store_sqft"
"c0" ||--|| "d02" : "store_id-store_id"
"d02" ||--|| "h020" : "store_id-store_type"
"c0" ||--|| "d03" : "time_id-time_id"
"d03" ||--|| "h030" : "time_id-the_year"
"d03" ||--|| "h030" : "time_id-quarter"
"d03" ||--|| "h030" : "time_id-month_of_year"
"c0" ||--|| "d03" : "time_id-time_id"
"d03" ||--|| "h031" : "time_id-the_year"
"d03" ||--|| "h031" : "time_id-week_of_year"
"d03" ||--|| "h031" : "time_id-day_of_month"
"c0" ||--|| "d04" : "product_id-product_id"
"d04" ||--|| "h040" : "product_id-product_family"
"d04" ||--|| "h040" : "product_id-product_department"
"d04" ||--|| "h040" : "product_id-product_category"
"d04" ||--|| "h040" : "product_id-product_subcategory"
"d04" ||--|| "h040" : "product_id-brand_name"
"d04" ||--|| "h040" : "product_id-product_name"
"c0" ||--|| "d04" : "product_id-product_id"
"d04" ||--|| "h041" : "product_id-product_family"
"c0" ||--|| "d04" : "product_id-product_id"
"d04" ||--|| "h042" : "product_id-product_department"
"c0" ||--|| "d04" : "product_id-product_id"
"d04" ||--|| "h043" : "product_id-product_category"
"c0" ||--|| "d04" : "product_id-product_id"
"d04" ||--|| "h044" : "product_id-product_subcategory"
"c0" ||--|| "d04" : "product_id-product_id"
"d04" ||--|| "h045" : "product_id-brand_name"
"c0" ||--|| "d04" : "product_id-product_id"
"d04" ||--|| "h046" : "product_id-product_name"
"c0" ||--|| "d05" : "promotion_id-promotion_id"
"d05" ||--|| "h050" : "promotion_id-media_type"
"c0" ||--|| "d06" : "promotion_id-promotion_id"
"d06" ||--|| "h060" : "promotion_id-promotion_name"
"c0" ||--|| "d07" : "customer_id-customer_id"
"d07" ||--|| "h070" : "customer_id-country"
"d07" ||--|| "h070" : "customer_id-state_province"
"d07" ||--|| "h070" : "customer_id-city"
"d07" ||--|| "h070" : "customer_id-customer_id"
"c0" ||--|| "d08" : "customer_id-customer_id"
"d08" ||--|| "h080" : "customer_id-education"
"c0" ||--|| "d09" : "customer_id-customer_id"
"d09" ||--|| "h090" : "customer_id-gender"
"c0" ||--|| "d010" : "customer_id-customer_id"
"d010" ||--|| "h0100" : "customer_id-marital_status"
"c0" ||--|| "d011" : "customer_id-customer_id"
"d011" ||--|| "h0110" : "customer_id-yearly_income"
```
---
### Cube "Warehouse" diagram:

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
c1["Warehouse"]{
M Store_Invoice ""
M Supply_Time ""
M Warehouse_Cost ""
M Warehouse_Sales ""
M Units_Shipped ""
M Units_Ordered ""
M Warehouse_Profit ""
D Store ""
D Store_Size_in_SQFT ""
D Store_Type ""
D Time ""
D Product ""
D Warehouse ""
NS Top_Sellers ""
CM Average_Warehouse_Sale ""
}
d10["Store"] {
H _ ""
}
h100["_"] {
L Store_Country ""
L Store_State ""
L Store_City ""
L Store_Name ""
}
d11["Store Size in SQFT"] {
H _ ""
}
h110["_"] {
L Store_Sqft ""
}
d12["Store Type"] {
H _ ""
}
h120["_"] {
L Store_Type ""
}
d13["Time"] {
H _ ""
H Weekly ""
}
h130["_"] {
L Year ""
L Quarter ""
L Month ""
}
h131["Weekly"] {
L Year ""
L Week ""
L Day ""
}
d14["Product"] {
H _ ""
H Product_Family ""
H Product_Department ""
H Product_Category ""
H Product_Subcategory ""
H Brand_Name ""
H Product_Name ""
}
h140["_"] {
L Product_Family ""
L Product_Department ""
L Product_Category ""
L Product_Subcategory ""
L Brand_Name ""
L Product_Name ""
}
h141["Product_Family"] {
L Product_Family ""
}
h142["Product_Department"] {
L Product_Department ""
}
h143["Product_Category"] {
L Product_Category ""
}
h144["Product_Subcategory"] {
L Product_Subcategory ""
}
h145["Brand_Name"] {
L Brand_Name ""
}
h146["Product_Name"] {
L Product_Name ""
}
d15["Warehouse"] {
H _ ""
}
h150["_"] {
L Country ""
L State_Province ""
L City ""
L Warehouse_Name ""
}
"c1" ||--|| "d10" : "store_id-store_id"
"d10" ||--|| "h100" : "store_id-store_country"
"d10" ||--|| "h100" : "store_id-store_state"
"d10" ||--|| "h100" : "store_id-store_city"
"d10" ||--|| "h100" : "store_id-store_name"
"c1" ||--|| "d11" : "store_id-store_id"
"d11" ||--|| "h110" : "store_id-store_sqft"
"c1" ||--|| "d12" : "store_id-store_id"
"d12" ||--|| "h120" : "store_id-store_type"
"c1" ||--|| "d13" : "time_id-time_id"
"d13" ||--|| "h130" : "time_id-the_year"
"d13" ||--|| "h130" : "time_id-quarter"
"d13" ||--|| "h130" : "time_id-month_of_year"
"c1" ||--|| "d13" : "time_id-time_id"
"d13" ||--|| "h131" : "time_id-the_year"
"d13" ||--|| "h131" : "time_id-week_of_year"
"d13" ||--|| "h131" : "time_id-day_of_month"
"c1" ||--|| "d14" : "product_id-product_id"
"d14" ||--|| "h140" : "product_id-product_family"
"d14" ||--|| "h140" : "product_id-product_department"
"d14" ||--|| "h140" : "product_id-product_category"
"d14" ||--|| "h140" : "product_id-product_subcategory"
"d14" ||--|| "h140" : "product_id-brand_name"
"d14" ||--|| "h140" : "product_id-product_name"
"c1" ||--|| "d14" : "product_id-product_id"
"d14" ||--|| "h141" : "product_id-product_family"
"c1" ||--|| "d14" : "product_id-product_id"
"d14" ||--|| "h142" : "product_id-product_department"
"c1" ||--|| "d14" : "product_id-product_id"
"d14" ||--|| "h143" : "product_id-product_category"
"c1" ||--|| "d14" : "product_id-product_id"
"d14" ||--|| "h144" : "product_id-product_subcategory"
"c1" ||--|| "d14" : "product_id-product_id"
"d14" ||--|| "h145" : "product_id-brand_name"
"c1" ||--|| "d14" : "product_id-product_id"
"d14" ||--|| "h146" : "product_id-product_name"
"c1" ||--|| "d15" : "warehouse_id-warehouse_id"
"d15" ||--|| "h150" : "warehouse_id-warehouse_country"
"d15" ||--|| "h150" : "warehouse_id-warehouse_state_province"
"d15" ||--|| "h150" : "warehouse_id-warehouse_city"
"d15" ||--|| "h150" : "warehouse_id-warehouse_name"
```
---
### Cube "Store" diagram:

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
c2["Store"]{
M Store_Sqft ""
M Grocery_Sqft ""
D Store_Type ""
D Store ""
D Has_coffee_bar ""
}
d20["Store Type"] {
H _ ""
}
h200["_"] {
L Store_Type ""
}
d21["Store"] {
H _ ""
}
h210["_"] {
L Store_Country ""
L Store_State ""
L Store_City ""
L Store_Name ""
}
d22["Has coffee bar"] {
H _ ""
}
h220["_"] {
L Has_coffee_bar ""
}
"c2" ||--|| "d20" : ""
"d20" ||--|| "h200" : "store_type"
"c2" ||--|| "d21" : "store_id"
"d21" ||--|| "h210" : "store_id-store_country"
"d21" ||--|| "h210" : "store_id-store_state"
"d21" ||--|| "h210" : "store_id-store_city"
"d21" ||--|| "h210" : "store_id-store_name"
"c2" ||--|| "d22" : ""
"d22" ||--|| "h220" : "coffee_bar"
```
---
### Cube "HR" diagram:

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
c3["HR"]{
M Org_Salary ""
M Count ""
M Number_of_Employees ""
D Time ""
D Store ""
D Pay_Type ""
D Store_Type ""
D Position ""
D Department ""
D Employees ""
CM Employee_Salary ""
CM Avg_Salary ""
}
d30["Time"] {
H _ ""
}
h300["_"] {
L Year ""
L Quarter ""
L Month ""
}
d31["Store"] {
H _ ""
}
h310["_"] {
L Store_Country ""
L Store_State ""
L Store_City ""
L Store_Name ""
}
d32["Pay Type"] {
H _ ""
}
h320["_"] {
L Pay_Type ""
}
d33["Store Type"] {
H _ ""
}
h330["_"] {
L Store_Type ""
}
d34["Position"] {
H _ ""
}
h340["_"] {
L Management_Role ""
L Position_Title ""
}
d35["Department"] {
H _ ""
}
h350["_"] {
L Department_Description ""
}
d36["Employees"] {
H _ ""
}
h360["_"] {
L Employee_Id ""
}
"c3" ||--|| "d30" : "pay_date-the_date"
"d30" ||--|| "h300" : "the_date-the_year"
"d30" ||--|| "h300" : "the_date-quarter"
"d30" ||--|| "h300" : "the_date-month_of_year"
"c3" ||--|| "d31" : "employee_id-employee_id"
"d31" ||--|| "h310" : "employee_id-store_country"
"d31" ||--|| "h310" : "employee_id-store_state"
"d31" ||--|| "h310" : "employee_id-store_city"
"d31" ||--|| "h310" : "employee_id-store_name"
"c3" ||--|| "d32" : "employee_id-employee_id"
"d32" ||--|| "h320" : "employee_id-pay_type"
"c3" ||--|| "d33" : "employee_id-employee_id"
"d33" ||--|| "h330" : "employee_id-store_type"
"c3" ||--|| "d34" : "employee_id-employee_id"
"d34" ||--|| "h340" : "employee_id-management_role"
"d34" ||--|| "h340" : "employee_id-position_title"
"c3" ||--|| "d35" : "department_id-department_id"
"d35" ||--|| "h350" : "department_id-department_id"
"c3" ||--|| "d36" : "employee_id-employee_id"
"d36" ||--|| "h360" : "employee_id-employee_id"
```
---
### Cube "Sales Ragged" diagram:

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
c4["Sales Ragged"]{
M Unit_Sales ""
M Store_Cost ""
M Store_Sales ""
M Sales_Count ""
M Customer_Count ""
D Store ""
D Geography ""
D Store_Size_in_SQFT ""
D Store_Type ""
D Time ""
D Product ""
D Promotion_Media ""
D Promotions ""
D Customers ""
D Education_Level ""
D Gender ""
D Marital_Status ""
D Yearly_Income ""
}
d40["Store"] {
H _ ""
}
h400["_"] {
L Store_Country ""
L Store_State ""
L Store_City ""
L Store_Name ""
}
d41["Geography"] {
H _ ""
}
h410["_"] {
L Country ""
L State ""
L City ""
}
d42["Store Size in SQFT"] {
H _ ""
}
h420["_"] {
L Store_Sqft ""
}
d43["Store Type"] {
H _ ""
}
h430["_"] {
L Store_Type ""
}
d44["Time"] {
H _ ""
H Weekly ""
}
h440["_"] {
L Year ""
L Quarter ""
L Month ""
}
h441["Weekly"] {
L Year ""
L Week ""
L Day ""
}
d45["Product"] {
H _ ""
H Product_Family ""
H Product_Department ""
H Product_Category ""
H Product_Subcategory ""
H Brand_Name ""
H Product_Name ""
}
h450["_"] {
L Product_Family ""
L Product_Department ""
L Product_Category ""
L Product_Subcategory ""
L Brand_Name ""
L Product_Name ""
}
h451["Product_Family"] {
L Product_Family ""
}
h452["Product_Department"] {
L Product_Department ""
}
h453["Product_Category"] {
L Product_Category ""
}
h454["Product_Subcategory"] {
L Product_Subcategory ""
}
h455["Brand_Name"] {
L Brand_Name ""
}
h456["Product_Name"] {
L Product_Name ""
}
d46["Promotion Media"] {
H _ ""
}
h460["_"] {
L Media_Type ""
}
d47["Promotions"] {
H _ ""
}
h470["_"] {
L Promotion_Name ""
}
d48["Customers"] {
H _ ""
}
h480["_"] {
L Country ""
L State_Province ""
L City ""
L Name ""
}
d49["Education Level"] {
H _ ""
}
h490["_"] {
L Education_Level ""
}
d410["Gender"] {
H _ ""
}
h4100["_"] {
L Gender ""
}
d411["Marital Status"] {
H _ ""
}
h4110["_"] {
L Marital_Status ""
}
d412["Yearly Income"] {
H _ ""
}
h4120["_"] {
L Yearly_Income ""
}
"c4" ||--|| "d40" : "store_id-store_id"
"d40" ||--|| "h400" : "store_id-store_country"
"d40" ||--|| "h400" : "store_id-store_state"
"d40" ||--|| "h400" : "store_id-store_city"
"d40" ||--|| "h400" : "store_id-store_name"
"c4" ||--|| "d41" : "store_id-store_id"
"d41" ||--|| "h410" : "store_id-store_country"
"d41" ||--|| "h410" : "store_id-store_state"
"d41" ||--|| "h410" : "store_id-store_city"
"c4" ||--|| "d42" : "store_id-store_id"
"d42" ||--|| "h420" : "store_id-store_sqft"
"c4" ||--|| "d43" : "store_id-store_id"
"d43" ||--|| "h430" : "store_id-store_type"
"c4" ||--|| "d44" : "time_id-time_id"
"d44" ||--|| "h440" : "time_id-the_year"
"d44" ||--|| "h440" : "time_id-quarter"
"d44" ||--|| "h440" : "time_id-month_of_year"
"c4" ||--|| "d44" : "time_id-time_id"
"d44" ||--|| "h441" : "time_id-the_year"
"d44" ||--|| "h441" : "time_id-week_of_year"
"d44" ||--|| "h441" : "time_id-day_of_month"
"c4" ||--|| "d45" : "product_id-product_id"
"d45" ||--|| "h450" : "product_id-product_family"
"d45" ||--|| "h450" : "product_id-product_department"
"d45" ||--|| "h450" : "product_id-product_category"
"d45" ||--|| "h450" : "product_id-product_subcategory"
"d45" ||--|| "h450" : "product_id-brand_name"
"d45" ||--|| "h450" : "product_id-product_name"
"c4" ||--|| "d45" : "product_id-product_id"
"d45" ||--|| "h451" : "product_id-product_family"
"c4" ||--|| "d45" : "product_id-product_id"
"d45" ||--|| "h452" : "product_id-product_department"
"c4" ||--|| "d45" : "product_id-product_id"
"d45" ||--|| "h453" : "product_id-product_category"
"c4" ||--|| "d45" : "product_id-product_id"
"d45" ||--|| "h454" : "product_id-product_subcategory"
"c4" ||--|| "d45" : "product_id-product_id"
"d45" ||--|| "h455" : "product_id-brand_name"
"c4" ||--|| "d45" : "product_id-product_id"
"d45" ||--|| "h456" : "product_id-product_name"
"c4" ||--|| "d46" : "promotion_id-promotion_id"
"d46" ||--|| "h460" : "promotion_id-media_type"
"c4" ||--|| "d47" : "promotion_id-promotion_id"
"d47" ||--|| "h470" : "promotion_id-promotion_name"
"c4" ||--|| "d48" : "customer_id-customer_id"
"d48" ||--|| "h480" : "customer_id-country"
"d48" ||--|| "h480" : "customer_id-state_province"
"d48" ||--|| "h480" : "customer_id-city"
"d48" ||--|| "h480" : "customer_id-"
"c4" ||--|| "d49" : "customer_id-customer_id"
"d49" ||--|| "h490" : "customer_id-education"
"c4" ||--|| "d410" : "customer_id-customer_id"
"d410" ||--|| "h4100" : "customer_id-gender"
"c4" ||--|| "d411" : "customer_id-customer_id"
"d411" ||--|| "h4110" : "customer_id-marital_status"
"c4" ||--|| "d412" : "customer_id-customer_id"
"d412" ||--|| "h4120" : "customer_id-yearly_income"
```
---
### Cube "Sales 2" diagram:

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
c5["Sales 2"]{
M Sales_Count ""
M Unit_Sales ""
M Store_Sales ""
M Store_Cost ""
M Customer_Count ""
D Time ""
D Product ""
D Gender ""
CM Profit ""
CM Profit_last_Period ""
}
d50["Time"] {
H _ ""
H Weekly ""
}
h500["_"] {
L Year ""
L Quarter ""
L Month ""
}
h501["Weekly"] {
L Year ""
L Week ""
L Day ""
}
d51["Product"] {
H _ ""
H Product_Family ""
H Product_Department ""
H Product_Category ""
H Product_Subcategory ""
H Brand_Name ""
H Product_Name ""
}
h510["_"] {
L Product_Family ""
L Product_Department ""
L Product_Category ""
L Product_Subcategory ""
L Brand_Name ""
L Product_Name ""
}
h511["Product_Family"] {
L Product_Family ""
}
h512["Product_Department"] {
L Product_Department ""
}
h513["Product_Category"] {
L Product_Category ""
}
h514["Product_Subcategory"] {
L Product_Subcategory ""
}
h515["Brand_Name"] {
L Brand_Name ""
}
h516["Product_Name"] {
L Product_Name ""
}
d52["Gender"] {
H _ ""
}
h520["_"] {
L Gender ""
}
"c5" ||--|| "d50" : "time_id-time_id"
"d50" ||--|| "h500" : "time_id-the_year"
"d50" ||--|| "h500" : "time_id-quarter"
"d50" ||--|| "h500" : "time_id-month_of_year"
"c5" ||--|| "d50" : "time_id-time_id"
"d50" ||--|| "h501" : "time_id-the_year"
"d50" ||--|| "h501" : "time_id-week_of_year"
"d50" ||--|| "h501" : "time_id-day_of_month"
"c5" ||--|| "d51" : "product_id-product_id"
"d51" ||--|| "h510" : "product_id-product_family"
"d51" ||--|| "h510" : "product_id-product_department"
"d51" ||--|| "h510" : "product_id-product_category"
"d51" ||--|| "h510" : "product_id-product_subcategory"
"d51" ||--|| "h510" : "product_id-brand_name"
"d51" ||--|| "h510" : "product_id-product_name"
"c5" ||--|| "d51" : "product_id-product_id"
"d51" ||--|| "h511" : "product_id-product_family"
"c5" ||--|| "d51" : "product_id-product_id"
"d51" ||--|| "h512" : "product_id-product_department"
"c5" ||--|| "d51" : "product_id-product_id"
"d51" ||--|| "h513" : "product_id-product_category"
"c5" ||--|| "d51" : "product_id-product_id"
"d51" ||--|| "h514" : "product_id-product_subcategory"
"c5" ||--|| "d51" : "product_id-product_id"
"d51" ||--|| "h515" : "product_id-brand_name"
"c5" ||--|| "d51" : "product_id-product_id"
"d51" ||--|| "h516" : "product_id-product_name"
"c5" ||--|| "d52" : "customer_id-customer_id"
"d52" ||--|| "h520" : "customer_id-gender"
```
---
### Virtual Cube "Warehouse and Sales" diagram:

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
c6["Warehouse and Sales"]{
M Sales_Measures_Sales_Count ""
M Sales_Measures_Store_Cost ""
M Sales_Measures_Store_Sales ""
M Sales_Measures_Unit_Sales ""
M Sales_Measures_Profit ""
M Sales_Measures_Profit_Growth ""
M Warehouse_Measures_Store_Invoice ""
M Warehouse_Measures_Supply_Time ""
M Warehouse_Measures_Units_Ordered ""
M Warehouse_Measures_Units_Shipped ""
M Warehouse_Measures_Warehouse_Cost ""
M Warehouse_Measures_Warehouse_Profit ""
M Warehouse_Measures_Warehouse_Sales ""
M Warehouse_Measures_Average_Warehouse_Sale ""
D Customers ""
D Education_Level ""
D Gender ""
D Marital_Status ""
D Product ""
D Promotion_Media ""
D Promotions ""
D Store ""
D Time ""
D Yearly_Income ""
D Warehouse ""
CM Profit_Per_Unit_Shipped ""
}
d60["Customers"] {
H _ ""
}
h600["_"] {
L Country ""
L State_Province ""
L City ""
L Name ""
}
d61["Education Level"] {
H _ ""
}
h610["_"] {
L Education_Level ""
}
d62["Gender"] {
H _ ""
}
h620["_"] {
L Gender ""
}
d63["Marital Status"] {
H _ ""
}
h630["_"] {
L Marital_Status ""
}
d65["Promotion Media"] {
H _ ""
}
h650["_"] {
L Media_Type ""
}
d66["Promotions"] {
H _ ""
}
h660["_"] {
L Promotion_Name ""
}
d69["Yearly Income"] {
H _ ""
}
h690["_"] {
L Yearly_Income ""
}
d610["Warehouse"] {
H _ ""
}
h6100["_"] {
L Country ""
L State_Province ""
L City ""
L Warehouse_Name ""
}
"c6" ||--|| "d60" : "customer_id-customer_id"
"d60" ||--|| "h600" : "customer_id-country"
"d60" ||--|| "h600" : "customer_id-state_province"
"d60" ||--|| "h600" : "customer_id-city"
"d60" ||--|| "h600" : "customer_id-customer_id"
"c6" ||--|| "d61" : "customer_id-customer_id"
"d61" ||--|| "h610" : "customer_id-education"
"c6" ||--|| "d62" : "customer_id-customer_id"
"d62" ||--|| "h620" : "customer_id-gender"
"c6" ||--|| "d63" : "customer_id-customer_id"
"d63" ||--|| "h630" : "customer_id-marital_status"
"c6" ||--|| "d65" : "promotion_id-promotion_id"
"d65" ||--|| "h650" : "promotion_id-media_type"
"c6" ||--|| "d66" : "promotion_id-promotion_id"
"d66" ||--|| "h660" : "promotion_id-promotion_name"
"c6" ||--|| "d69" : "customer_id-customer_id"
"d69" ||--|| "h690" : "customer_id-yearly_income"
"c6" ||--|| "d610" : "warehouse_id-warehouse_id"
"d610" ||--|| "h6100" : "warehouse_id-warehouse_country"
"d610" ||--|| "h6100" : "warehouse_id-warehouse_state_province"
"d610" ||--|| "h6100" : "warehouse_id-warehouse_city"
"d610" ||--|| "h6100" : "warehouse_id-warehouse_name"
```
---
### Cube Matrix for FoodMart:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube Sales: [1, 0.0000]
Cube Warehouse: [1, 0.0000]
Cube Store: [0.3000, 0.0000]
Cube HR: [0.6500, 0.0000]
Cube Sales_Ragged: [1, 0.0000]
Cube Sales_2: [0.9500, 0.0000]
```
---
### Database :
---
```mermaid
---
title: Diagram;
---
erDiagram
"account✔"{
int account_id "✔"
int account_parent "✔"
string account_description "✔"
string account_type "✔"
string account_rollup "✔"
string Custom_Members "✔"
}
"agg_c_10_sales_fact_1997✔"{
int month_of_year "✔"
string quarter "✔"
int the_year "✔"
double store_sales "✔"
double store_cost "✔"
double unit_sales "✔"
int customer_count "✔"
int fact_count "✔"
}
"agg_c_14_sales_fact_1997✔"{
int product_id "✔"
int customer_id "✔"
int store_id "✔"
int promotion_id "✔"
int month_of_year "✔"
string quarter "✔"
int the_year "✔"
double store_sales "✔"
double store_cost "✔"
double unit_sales "✔"
int fact_count "✔"
}
"agg_c_special_sales_fact_1997✔"{
int product_id "✔"
int promotion_id "✔"
int customer_id "✔"
int store_id "✔"
int time_month "✔"
string time_quarter "✔"
int time_year "✔"
double store_sales_sum "✔"
double store_cost_sum "✔"
double unit_sales_sum "✔"
int fact_count "✔"
}
"agg_g_ms_pcat_sales_fact_1997✔"{
string gender "✔"
string marital_status "✔"
string product_family "✔"
string product_department "✔"
string product_category "✔"
int month_of_year "✔"
string quarter "✔"
int the_year "✔"
double store_sales "✔"
double store_cost "✔"
double unit_sales "✔"
int customer_count "✔"
int fact_count "✔"
}
"agg_l_03_sales_fact_1997✔"{
int time_id "✔"
int customer_id "✔"
double store_sales "✔"
double store_cost "✔"
double unit_sales "✔"
int fact_count "✔"
}
"agg_l_04_sales_fact_1997✔"{
int time_id "✔"
double store_sales "✔"
double store_cost "✔"
double unit_sales "✔"
int customer_count "✔"
int fact_count "✔"
}
"agg_l_05_sales_fact_1997✔"{
int product_id "✔"
int customer_id "✔"
int promotion_id "✔"
int store_id "✔"
double store_sales "✔"
double store_cost "✔"
double unit_sales "✔"
int fact_count "✔"
}
"agg_lc_06_sales_fact_1997✔"{
int time_id "✔"
string city "✔"
string state_province "✔"
string country "✔"
double store_sales "✔"
double store_cost "✔"
double unit_sales "✔"
int fact_count "✔"
}
"agg_lc_100_sales_fact_1997✔"{
int product_id "✔"
int customer_id "✔"
string quarter "✔"
int the_year "✔"
double store_sales "✔"
double store_cost "✔"
double unit_sales "✔"
int fact_count "✔"
}
"agg_ll_01_sales_fact_1997✔"{
int product_id "✔"
int time_id "✔"
int customer_id "✔"
double store_sales "✔"
double store_cost "✔"
double unit_sales "✔"
int fact_count "✔"
}
"agg_pl_01_sales_fact_1997✔"{
int product_id "✔"
int time_id "✔"
int customer_id "✔"
double store_sales_sum "✔"
double store_cost_sum "✔"
double unit_sales_sum "✔"
int fact_count "✔"
}
"category✔"{
string category_id "✔"
string category_parent "✔"
string category_description "✔"
string category_rollup "✔"
}
"currency✔"{
int currency_id "✔"
date date "✔"
string currency "✔"
double conversion_ratio "✔"
}
"customer✔"{
int customer_id "✔"
long account_num "✔"
string lname "✔"
string fname "✔"
string mi "✔"
string address1 "✔"
string address2 "✔"
string address3 "✔"
string address4 "✔"
string city "✔"
string state_province "✔"
string postal_code "✔"
string country "✔"
int customer_region_id "✔"
string phone1 "✔"
string phone2 "✔"
date birthdate "✔"
string marital_status "✔"
string yearly_income "✔"
string gender "✔"
int total_children "✔"
int num_children_at_home "✔"
string education "✔"
date date_accnt_opened "✔"
string member_card "✔"
string occupation "✔"
string houseowner "✔"
int num_cars_owned "✔"
string fullname "✔"
}
"days✔"{
int day "✔"
string week_day "✔"
}
"department✔"{
int department_id "✔"
string department_description "✔"
}
"employee✔"{
int employee_id "✔"
string full_name "✔"
string first_name "✔"
string last_name "✔"
int position_id "✔"
string position_title "✔"
int store_id "✔"
int department_id "✔"
date birth_date "✔"
timestamp hire_date "✔"
timestamp end_date "✔"
double salary "✔"
int supervisor_id "✔"
string education_level "✔"
string marital_status "✔"
string gender "✔"
string management_role "✔"
}
"employee_closure✔"{
int employee_id "✔"
int supervisor_id "✔"
int distance "✔"
}
"expense_fact✔"{
int store_id "✔"
int account_id "✔"
timestamp exp_date "✔"
int time_id "✔"
string category_id "✔"
int currency_id "✔"
double amount "✔"
}
"inventory_fact_1997✔"{
int product_id "✔"
int time_id "✔"
int warehouse_id "✔"
int store_id "✔"
int units_ordered "✔"
int units_shipped "✔"
double warehouse_sales "✔"
double warehouse_cost "✔"
int supply_time "✔"
double store_invoice "✔"
}
"inventory_fact_1998✔"{
int product_id "✔"
int time_id "✔"
int warehouse_id "✔"
int store_id "✔"
int units_ordered "✔"
int units_shipped "✔"
double warehouse_sales "✔"
double warehouse_cost "✔"
int supply_time "✔"
double store_invoice "✔"
}
"position✔"{
int position_id "✔"
string position_title "✔"
string pay_type "✔"
double min_scale "✔"
double max_scale "✔"
string management_role "✔"
}
"product✔"{
int product_class_id "✔"
int product_id "✔"
string brand_name "✔"
string product_name "✔"
long SKU "✔"
double SRP "✔"
double gross_weight "✔"
double net_weight "✔"
int recyclable_package "✔"
int low_fat "✔"
int units_per_case "✔"
int cases_per_pallet "✔"
double shelf_width "✔"
double shelf_height "✔"
double shelf_depth "✔"
}
"product_class✔"{
int product_class_id "✔"
string product_subcategory "✔"
string product_category "✔"
string product_department "✔"
string product_family "✔"
}
"promotion✔"{
int promotion_id "✔"
int promotion_district_id "✔"
string promotion_name "✔"
string media_type "✔"
double cost "✔"
timestamp start_date "✔"
timestamp end_date "✔"
}
"region✔"{
int region_id "✔"
string sales_city "✔"
string sales_state_province "✔"
string sales_district "✔"
string sales_region "✔"
string sales_country "✔"
int sales_district_id "✔"
}
"reserve_employee✔"{
int employee_id "✔"
string full_name "✔"
string first_name "✔"
string last_name "✔"
int position_id "✔"
string position_title "✔"
int store_id "✔"
int department_id "✔"
timestamp birth_date "✔"
timestamp hire_date "✔"
timestamp end_date "✔"
double salary "✔"
int supervisor_id "✔"
string education_level "✔"
string marital_status "✔"
string gender "✔"
}
"salary✔"{
timestamp pay_date "✔"
int employee_id "✔"
int department_id "✔"
int currency_id "✔"
double salary_paid "✔"
double overtime_paid "✔"
double vacation_accrued "✔"
double vacation_used "✔"
}
"sales_fact_1997✔"{
int product_id "✔"
int time_id "✔"
int customer_id "✔"
int promotion_id "✔"
int store_id "✔"
double store_sales "✔"
double store_cost "✔"
double unit_sales "✔"
}
"sales_fact_1998✔"{
int product_id "✔"
int time_id "✔"
int customer_id "✔"
int promotion_id "✔"
int store_id "✔"
double store_sales "✔"
double store_cost "✔"
double unit_sales "✔"
}
"sales_fact_dec_1998✔"{
int product_id "✔"
int time_id "✔"
int customer_id "✔"
int promotion_id "✔"
int store_id "✔"
double store_sales "✔"
double store_cost "✔"
double unit_sales "✔"
}
"store✔"{
int store_id "✔"
string store_type "✔"
int region_id "✔"
string store_name "✔"
int store_number "✔"
string store_street_address "✔"
string store_city "✔"
string store_state "✔"
string store_postal_code "✔"
string store_country "✔"
string store_manager "✔"
string store_phone "✔"
string store_fax "✔"
timestamp first_opened_date "✔"
timestamp last_remodel_date "✔"
int store_sqft "✔"
int grocery_sqft "✔"
int frozen_sqft "✔"
int meat_sqft "✔"
int coffee_bar "✔"
int video_store "✔"
int salad_bar "✔"
int prepared_food "✔"
int florist "✔"
}
"store_ragged✔"{
int store_id "✔"
string store_type "✔"
int region_id "✔"
string store_name "✔"
int store_number "✔"
string store_street_address "✔"
string store_city "✔"
string store_state "✔"
string store_postal_code "✔"
string store_country "✔"
string store_manager "✔"
string store_phone "✔"
string store_fax "✔"
timestamp first_opened_date "✔"
timestamp last_remodel_date "✔"
int store_sqft "✔"
int grocery_sqft "✔"
int frozen_sqft "✔"
int meat_sqft "✔"
int coffee_bar "✔"
int video_store "✔"
int salad_bar "✔"
int prepared_food "✔"
int florist "✔"
}
"time_by_day✔"{
int time_id "✔"
timestamp the_date "✔"
string the_day "✔"
string the_month "✔"
int the_year "✔"
int day_of_month "✔"
int week_of_year "✔"
int month_of_year "✔"
string quarter "✔"
string fiscal_period "✔"
}
"warehouse✔"{
int warehouse_id "✔"
int warehouse_class_id "✔"
int stores_id "✔"
string warehouse_name "✔"
string wa_address1 "✔"
string wa_address2 "✔"
string wa_address3 "✔"
string wa_address4 "✔"
string warehouse_city "✔"
string warehouse_state_province "✔"
string warehouse_postal_code "✔"
string warehouse_country "✔"
string warehouse_owner_name "✔"
string warehouse_phone "✔"
string warehouse_fax "✔"
}
"warehouse_class✔"{
int warehouse_class_id "✔"
string description "✔"
}

"sales_fact_1997✔" ||--o{ "store✔" : "store_id-store_id"
"sales_fact_1997✔" ||--o{ "store✔" : "store_id-store_id"
"sales_fact_1997✔" ||--o{ "store✔" : "store_id-store_id"
"sales_fact_1997✔" ||--o{ "time_by_day✔" : "time_id-time_id"
"sales_fact_1997✔" ||--o{ "time_by_day✔" : "time_id-time_id"
"sales_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"sales_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"sales_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"sales_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"sales_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"sales_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"sales_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"sales_fact_1997✔" ||--o{ "promotion✔" : "promotion_id-promotion_id"
"sales_fact_1997✔" ||--o{ "promotion✔" : "promotion_id-promotion_id"
"sales_fact_1997✔" ||--o{ "customer✔" : "customer_id-customer_id"
"sales_fact_1997✔" ||--o{ "customer✔" : "customer_id-customer_id"
"sales_fact_1997✔" ||--o{ "customer✔" : "customer_id-customer_id"
"sales_fact_1997✔" ||--o{ "customer✔" : "customer_id-customer_id"
"sales_fact_1997✔" ||--o{ "customer✔" : "customer_id-customer_id"
"inventory_fact_1997✔" ||--o{ "store✔" : "store_id-store_id"
"inventory_fact_1997✔" ||--o{ "store✔" : "store_id-store_id"
"inventory_fact_1997✔" ||--o{ "store✔" : "store_id-store_id"
"inventory_fact_1997✔" ||--o{ "time_by_day✔" : "time_id-time_id"
"inventory_fact_1997✔" ||--o{ "time_by_day✔" : "time_id-time_id"
"inventory_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"inventory_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"inventory_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"inventory_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"inventory_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"inventory_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"inventory_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"inventory_fact_1997✔" ||--o{ "warehouse✔" : "warehouse_id-warehouse_id"
"salary✔" ||--o{ "time_by_day✔" : "pay_date-the_date"
"salary✔" ||--o{ "employee✔" : "employee_id-employee_id"
"employee✔" ||--o{ "store✔" : "store_id-store_id"
"salary✔" ||--o{ "employee✔" : "employee_id-employee_id"
"employee✔" ||--o{ "position✔" : "position_id-position_id"
"salary✔" ||--o{ "employee✔" : "employee_id-employee_id"
"employee✔" ||--o{ "store✔" : "store_id-store_id"
"salary✔" ||--o{ "employee✔" : "employee_id-employee_id"
"salary✔" ||--o{ "department✔" : "department_id-department_id"
"salary✔" ||--o{ "employee✔" : "employee_id-employee_id"
"sales_fact_1997✔" ||--o{ "store_ragged✔" : "store_id-store_id"
"sales_fact_1997✔" ||--o{ "store_ragged✔" : "store_id-store_id"
"sales_fact_1997✔" ||--o{ "store✔" : "store_id-store_id"
"sales_fact_1997✔" ||--o{ "store✔" : "store_id-store_id"
"sales_fact_1997✔" ||--o{ "time_by_day✔" : "time_id-time_id"
"sales_fact_1997✔" ||--o{ "time_by_day✔" : "time_id-time_id"
"sales_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"sales_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"sales_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"sales_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"sales_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"sales_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"sales_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"sales_fact_1997✔" ||--o{ "promotion✔" : "promotion_id-promotion_id"
"sales_fact_1997✔" ||--o{ "promotion✔" : "promotion_id-promotion_id"
"sales_fact_1997✔" ||--o{ "customer✔" : "customer_id-customer_id"
"sales_fact_1997✔" ||--o{ "customer✔" : "customer_id-customer_id"
"sales_fact_1997✔" ||--o{ "customer✔" : "customer_id-customer_id"
"sales_fact_1997✔" ||--o{ "customer✔" : "customer_id-customer_id"
"sales_fact_1997✔" ||--o{ "customer✔" : "customer_id-customer_id"
"sales_fact_1997✔" ||--o{ "time_by_day✔" : "time_id-time_id"
"sales_fact_1997✔" ||--o{ "time_by_day✔" : "time_id-time_id"
"sales_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"sales_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"sales_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"sales_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"sales_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"sales_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"sales_fact_1997✔" ||--o{ "product✔" : "product_id-product_id"
"product✔" ||--o{ "product_class✔" : "product_class_id-product_class_id"
"sales_fact_1997✔" ||--o{ "customer✔" : "customer_id-customer_id"
```
---
## Validation result for schema FoodMart
## <span style='color: red;'>ERROR</span> : 
|Type|   |
|----|---|
|SCHEMA|Virtual Cube Dimension Cube name must be set|
|DATABASE|Syntax error in SQL statement "[*]""fname"" || ' ' || ""lname"""; SQL statement:"fname" || ' ' || "lname" [42000-224]|
|DATABASE|Syntax error in SQL statement "[*]""warehouse_sales"" - ""inventory_fact_1997"".""warehouse_cost"""; SQL statement:"warehouse_sales" - "inventory_fact_1997"."warehouse_cost" [42000-224]|
|DATABASE|Syntax error in SQL statement "([*]case when ""sales_fact_1997"".""promotion_id"" = 0 then 0 else ""sales_fact_1997"".""store_sales"" end)"; expected "(, SELECT, TABLE, VALUES"; SQL statement:(case when "sales_fact_1997"."promotion_id" = 0 then 0 else "sales_fact_1997"."store_sales" end) [42001-224]|
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|DATABASE|Agg Exclude table agg_lc_10_sales_fact_1997 does not exist in database .|
|DATABASE|Table: Schema must be set|
|DATABASE|Agg Exclude table agg_pc_10_sales_fact_1997 does not exist in database .|
