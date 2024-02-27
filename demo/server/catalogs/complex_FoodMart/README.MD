# Documentation
### CatalogName : complex_FoodMart
## Olap Context Details:
## Schemas:
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

    Hierarchy0

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

### Cube "Sales" diagram:

---

```mermaid
erDiagram
"cube:Sales"{}

"cube:Sales" ||--o{ "dim:Store" : "store_id-store_id"
"cube:Sales" ||--o{ "dim:Store Size in SQFT" : "store_id-store_id"
"cube:Sales" ||--o{ "dim:Store Type" : "store_id-store_id"
"cube:Sales" ||--o{ "dim:Time" : "time_id-time_id"
"cube:Sales" ||--o{ "dim:Time" : "time_id-time_id"
"cube:Sales" ||--o{ "dim:Product" : "product_id-product_id"
"cube:Sales" ||--o{ "dim:Promotion Media" : "promotion_id-promotion_id"
"cube:Sales" ||--o{ "dim:Promotions" : "promotion_id-promotion_id"
"cube:Sales" ||--o{ "dim:Customers" : "customer_id-customer_id"
"cube:Sales" ||--o{ "dim:Education Level" : "customer_id-customer_id"
"cube:Sales" ||--o{ "dim:Gender" : "customer_id-customer_id"
"cube:Sales" ||--o{ "dim:Marital Status" : "customer_id-customer_id"
"cube:Sales" ||--o{ "dim:Yearly Income" : "customer_id-customer_id"
```
---
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

### Cube "Warehouse" diagram:

---

```mermaid
erDiagram
"cube:Warehouse"{}

"cube:Warehouse" ||--o{ "dim:Store" : "store_id-store_id"
"cube:Warehouse" ||--o{ "dim:Store Size in SQFT" : "store_id-store_id"
"cube:Warehouse" ||--o{ "dim:Store Type" : "store_id-store_id"
"cube:Warehouse" ||--o{ "dim:Time" : "time_id-time_id"
"cube:Warehouse" ||--o{ "dim:Time" : "time_id-time_id"
"cube:Warehouse" ||--o{ "dim:Product" : "product_id-product_id"
"cube:Warehouse" ||--o{ "dim:Warehouse" : "warehouse_id-warehouse_id"
```
---
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

### Cube "Store" diagram:

---

```mermaid
erDiagram
"cube:Store"{}

"cube:Store" ||--o{ "dim:Store Type" : ""
"cube:Store" ||--o{ "dim:Store" : "store_id"
"cube:Store" ||--o{ "dim:Has coffee bar" : ""
```
---
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

### Cube "HR" diagram:

---

```mermaid
erDiagram
"cube:HR"{}

"cube:HR" ||--o{ "dim:Time" : "pay_date-the_date"
"cube:HR" ||--o{ "dim:Store" : "employee_id-employee_id"
"cube:HR" ||--o{ "dim:Pay Type" : "employee_id-employee_id"
"cube:HR" ||--o{ "dim:Store Type" : "employee_id-employee_id"
"cube:HR" ||--o{ "dim:Position" : "employee_id-employee_id"
"cube:HR" ||--o{ "dim:Department" : "department_id-department_id"
"cube:HR" ||--o{ "dim:Employees" : "employee_id-employee_id"
```
---
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

    column(s): null

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

### Cube "Sales Ragged" diagram:

---

```mermaid
erDiagram
"cube:Sales Ragged"{}

"cube:Sales Ragged" ||--o{ "dim:Store" : "store_id-store_id"
"cube:Sales Ragged" ||--o{ "dim:Geography" : "store_id-store_id"
"cube:Sales Ragged" ||--o{ "dim:Store Size in SQFT" : "store_id-store_id"
"cube:Sales Ragged" ||--o{ "dim:Store Type" : "store_id-store_id"
"cube:Sales Ragged" ||--o{ "dim:Time" : "time_id-time_id"
"cube:Sales Ragged" ||--o{ "dim:Time" : "time_id-time_id"
"cube:Sales Ragged" ||--o{ "dim:Product" : "product_id-product_id"
"cube:Sales Ragged" ||--o{ "dim:Promotion Media" : "promotion_id-promotion_id"
"cube:Sales Ragged" ||--o{ "dim:Promotions" : "promotion_id-promotion_id"
"cube:Sales Ragged" ||--o{ "dim:Customers" : "customer_id-customer_id"
"cube:Sales Ragged" ||--o{ "dim:Education Level" : "customer_id-customer_id"
"cube:Sales Ragged" ||--o{ "dim:Gender" : "customer_id-customer_id"
"cube:Sales Ragged" ||--o{ "dim:Marital Status" : "customer_id-customer_id"
"cube:Sales Ragged" ||--o{ "dim:Yearly Income" : "customer_id-customer_id"
```
---
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

### Cube "Sales 2" diagram:

---

```mermaid
erDiagram
"cube:Sales 2"{}

"cube:Sales 2" ||--o{ "dim:Time" : "time_id-time_id"
"cube:Sales 2" ||--o{ "dim:Time" : "time_id-time_id"
"cube:Sales 2" ||--o{ "dim:Product" : "product_id-product_id"
"cube:Sales 2" ||--o{ "dim:Gender" : "customer_id-customer_id"
```
---
### Roles :##### Role: "California manager"

##### Role: "No HR Cube"

##### Role: "Administrator"

### Database :
---
```mermaid
---
title: Diagram;
---
erDiagram
account{
int account_id
int account_parent
string account_description
string account_type
string account_rollup
string Custom_Members
}
agg_c_10_sales_fact_1997{
int month_of_year
string quarter
int the_year
null store_sales
null store_cost
null unit_sales
int customer_count
int fact_count
}
agg_c_14_sales_fact_1997{
int product_id
int customer_id
int store_id
int promotion_id
int month_of_year
string quarter
int the_year
null store_sales
null store_cost
null unit_sales
int fact_count
}
agg_c_special_sales_fact_1997{
int product_id
int promotion_id
int customer_id
int store_id
int time_month
string time_quarter
int time_year
null store_sales_sum
null store_cost_sum
null unit_sales_sum
int fact_count
}
agg_g_ms_pcat_sales_fact_1997{
string gender
string marital_status
string product_family
string product_department
string product_category
int month_of_year
string quarter
int the_year
null store_sales
null store_cost
null unit_sales
int customer_count
int fact_count
}
agg_l_03_sales_fact_1997{
int time_id
int customer_id
null store_sales
null store_cost
null unit_sales
int fact_count
}
agg_l_04_sales_fact_1997{
int time_id
null store_sales
null store_cost
null unit_sales
int customer_count
int fact_count
}
agg_l_05_sales_fact_1997{
int product_id
int customer_id
int promotion_id
int store_id
null store_sales
null store_cost
null unit_sales
int fact_count
}
agg_lc_06_sales_fact_1997{
int time_id
string city
string state_province
string country
null store_sales
null store_cost
null unit_sales
int fact_count
}
agg_lc_100_sales_fact_1997{
int product_id
int customer_id
string quarter
int the_year
null store_sales
null store_cost
null unit_sales
int fact_count
}
agg_ll_01_sales_fact_1997{
int product_id
int time_id
int customer_id
null store_sales
null store_cost
null unit_sales
int fact_count
}
agg_pl_01_sales_fact_1997{
int product_id
int time_id
int customer_id
null store_sales_sum
null store_cost_sum
null unit_sales_sum
int fact_count
}
category{
string category_id
string category_parent
string category_description
string category_rollup
}
currency{
int currency_id
date date
string currency
null conversion_ratio
}
customer{
int customer_id
string account_num
string lname
string fname
string mi
string address1
string address2
string address3
string address4
string city
string state_province
string postal_code
string country
int customer_region_id
string phone1
string phone2
date birthdate
string marital_status
string yearly_income
string gender
int total_children
int num_children_at_home
string education
date date_accnt_opened
string member_card
string occupation
string houseowner
int num_cars_owned
string fullname
}
days{
int day
string week_day
}
department{
int department_id
string department_description
}
employee{
int employee_id
string full_name
string first_name
string last_name
int position_id
string position_title
int store_id
int department_id
date birth_date
string hire_date
string end_date
null salary
int supervisor_id
string education_level
string marital_status
string gender
string management_role
}
employee_closure{
int employee_id
int supervisor_id
int distance
}
expense_fact{
int store_id
int account_id
timestamp exp_date
int time_id
string category_id
int currency_id
null amount
}
inventory_fact_1997{
int product_id
int time_id
int warehouse_id
int store_id
int units_ordered
int units_shipped
null warehouse_sales
null warehouse_cost
int supply_time
null store_invoice
}
inventory_fact_1998{
int product_id
int time_id
int warehouse_id
int store_id
int units_ordered
int units_shipped
null warehouse_sales
null warehouse_cost
int supply_time
null store_invoice
}
position{
int position_id
string position_title
string pay_type
null min_scale
null max_scale
string management_role
}
position_{
int position_id
string position_title
string pay_type
null min_scale
null max_scale
string management_role
}
product{
int product_class_id
int product_id
string brand_name
string product_name
string SKU
null SRP
null gross_weight
null net_weight
boolean recyclable_package
boolean low_fat
int units_per_case
int cases_per_pallet
null shelf_width
null shelf_height
null shelf_depth
}
product_class{
int product_class_id
string product_subcategory
string product_category
string product_department
string product_family
}
promotion{
int promotion_id
int promotion_district_id
string promotion_name
string media_type
null cost
timestamp start_date
timestamp end_date
}
region{
int region_id
string sales_city
string sales_state_province
string sales_district
string sales_region
string sales_country
int sales_district_id
}
reserve_employee{
int employee_id
string full_name
string first_name
string last_name
int position_id
string position_title
int store_id
int department_id
timestamp birth_date
timestamp hire_date
timestamp end_date
null salary
int supervisor_id
string education_level
string marital_status
string gender
}
salary{
timestamp pay_date
int employee_id
int department_id
int currency_id
null salary_paid
null overtime_paid
null vacation_accrued
null vacation_used
}
sales_fact_1997{
int product_id
int time_id
int customer_id
int promotion_id
int store_id
null store_sales
null store_cost
null unit_sales
}
sales_fact_1998{
int product_id
int time_id
int customer_id
int promotion_id
int store_id
null store_sales
null store_cost
null unit_sales
}
sales_fact_dec_1998{
int product_id
int time_id
int customer_id
int promotion_id
int store_id
null store_sales
null store_cost
null unit_sales
}
store{
int store_id
string store_type
int region_id
string store_name
int store_number
string store_street_address
string store_city
string store_state
string store_postal_code
string store_country
string store_manager
string store_phone
string store_fax
timestamp first_opened_date
timestamp last_remodel_date
int store_sqft
int grocery_sqft
int frozen_sqft
int meat_sqft
boolean coffee_bar
boolean video_store
boolean salad_bar
boolean prepared_food
boolean florist
}
store_ragged{
int store_id
string store_type
int region_id
string store_name
int store_number
string store_street_address
string store_city
string store_state
string store_postal_code
string store_country
string store_manager
string store_phone
string store_fax
timestamp first_opened_date
timestamp last_remodel_date
int store_sqft
int grocery_sqft
int frozen_sqft
int meat_sqft
boolean coffee_bar
boolean video_store
boolean salad_bar
boolean prepared_food
boolean florist
}
time_by_day{
int time_id
timestamp the_date
string the_day
string the_month
int the_year
int day_of_month
int week_of_year
int month_of_year
string quarter
string fiscal_period
}
warehouse{
int warehouse_id
int warehouse_class_id
int stores_id
string warehouse_name
string wa_address1
string wa_address2
string wa_address3
string wa_address4
string warehouse_city
string warehouse_state_province
string warehouse_postal_code
string warehouse_country
string warehouse_owner_name
string warehouse_phone
string warehouse_fax
}
warehouse_class{
int warehouse_class_id
string description
}

"sales_fact_1997" ||--o{ "store" : "store_id-store_id"
"sales_fact_1997" ||--o{ "store" : "store_id-store_id"
"sales_fact_1997" ||--o{ "store" : "store_id-store_id"
"sales_fact_1997" ||--o{ "time_by_day" : "time_id-time_id"
"sales_fact_1997" ||--o{ "time_by_day" : "time_id-time_id"
"sales_fact_1997" ||--o{ "product" : "product_id-product_id"
"product" ||--o{ "product_class" : "product_class_id-product_class_id"
"sales_fact_1997" ||--o{ "promotion" : "promotion_id-promotion_id"
"sales_fact_1997" ||--o{ "promotion" : "promotion_id-promotion_id"
"sales_fact_1997" ||--o{ "customer" : "customer_id-customer_id"
"sales_fact_1997" ||--o{ "customer" : "customer_id-customer_id"
"sales_fact_1997" ||--o{ "customer" : "customer_id-customer_id"
"sales_fact_1997" ||--o{ "customer" : "customer_id-customer_id"
"sales_fact_1997" ||--o{ "customer" : "customer_id-customer_id"
"inventory_fact_1997" ||--o{ "store" : "store_id-store_id"
"inventory_fact_1997" ||--o{ "store" : "store_id-store_id"
"inventory_fact_1997" ||--o{ "store" : "store_id-store_id"
"inventory_fact_1997" ||--o{ "time_by_day" : "time_id-time_id"
"inventory_fact_1997" ||--o{ "time_by_day" : "time_id-time_id"
"inventory_fact_1997" ||--o{ "product" : "product_id-product_id"
"product" ||--o{ "product_class" : "product_class_id-product_class_id"
"inventory_fact_1997" ||--o{ "warehouse" : "warehouse_id-warehouse_id"
"salary" ||--o{ "time_by_day" : "pay_date-the_date"
"salary" ||--o{ "employee" : "employee_id-employee_id"
"employee" ||--o{ "store" : "store_id-store_id"
"salary" ||--o{ "employee" : "employee_id-employee_id"
"employee" ||--o{ "position" : "position_id-position_id"
"salary" ||--o{ "employee" : "employee_id-employee_id"
"employee" ||--o{ "store" : "store_id-store_id"
"salary" ||--o{ "employee" : "employee_id-employee_id"
"salary" ||--o{ "department" : "department_id-department_id"
"salary" ||--o{ "employee" : "employee_id-employee_id"
"sales_fact_1997" ||--o{ "store_ragged" : "store_id-store_id"
"sales_fact_1997" ||--o{ "store_ragged" : "store_id-store_id"
"sales_fact_1997" ||--o{ "store" : "store_id-store_id"
"sales_fact_1997" ||--o{ "store" : "store_id-store_id"
"sales_fact_1997" ||--o{ "time_by_day" : "time_id-time_id"
"sales_fact_1997" ||--o{ "time_by_day" : "time_id-time_id"
"sales_fact_1997" ||--o{ "product" : "product_id-product_id"
"product" ||--o{ "product_class" : "product_class_id-product_class_id"
"sales_fact_1997" ||--o{ "promotion" : "promotion_id-promotion_id"
"sales_fact_1997" ||--o{ "promotion" : "promotion_id-promotion_id"
"sales_fact_1997" ||--o{ "customer" : "customer_id-customer_id"
"sales_fact_1997" ||--o{ "customer" : "customer_id-customer_id"
"sales_fact_1997" ||--o{ "customer" : "customer_id-customer_id"
"sales_fact_1997" ||--o{ "customer" : "customer_id-customer_id"
"sales_fact_1997" ||--o{ "customer" : "customer_id-customer_id"
"sales_fact_1997" ||--o{ "time_by_day" : "time_id-time_id"
"sales_fact_1997" ||--o{ "time_by_day" : "time_id-time_id"
"sales_fact_1997" ||--o{ "product" : "product_id-product_id"
"product" ||--o{ "product_class" : "product_class_id-product_class_id"
"sales_fact_1997" ||--o{ "customer" : "customer_id-customer_id"
```
---
## Validation result for schema FoodMart
## <span style='color: blue;'>WARNING</span> : 
|Type|   |
|----|---|
|SCHEMA|Level: Type should be set for Promotion Name|
|SCHEMA|Level: Type should be set for City|
|SCHEMA|Level: Type should be set for Week|
|SCHEMA|Level: Type should be set for Education Level|
|SCHEMA|Level: Type should be set for State|
|SCHEMA|Level: Type should be set for Year|
|SCHEMA|Level: Type should be set for Store Name|
|SCHEMA|Level: Type should be set for Product Department|
|SCHEMA|Level: Type should be set for Quarter|
|SCHEMA|Level: Type should be set for State Province|
|SCHEMA|Level: Type should be set for Product Name|
|SCHEMA|Level: Type should be set for Day|
|SCHEMA|Level: Type should be set for Store Country|
|SCHEMA|Level: Type should be set for Store City|
|SCHEMA|Level: Type should be set for Brand Name|
|SCHEMA|Level: Type should be set for Department Description|
|SCHEMA|Level: Type should be set for Pay Type|
|SCHEMA|Level: Type should be set for Position Title|
|SCHEMA|Level: Type should be set for Warehouse Name|
|SCHEMA|Level: Type should be set for Yearly Income|
|SCHEMA|Level: Type should be set for Media Type|
|SCHEMA|Level: Type should be set for Product Subcategory|
|SCHEMA|Level: Type should be set for Product Category|
|SCHEMA|Level: Type should be set for Store Sqft|
|SCHEMA|Level: Type should be set for Employee Id|
|SCHEMA|Level: Type should be set for Marital Status|
|SCHEMA|Level: Type should be set for Has coffee bar|
|SCHEMA|Level: Type should be set for Product Family|
|SCHEMA|Level: Type should be set for Gender|
|SCHEMA|Level: Type should be set for Management Role|
|SCHEMA|Level: Type should be set for Country|
|SCHEMA|Level: Type should be set for Name|
|SCHEMA|Level: Type should be set for Month|
|SCHEMA|Level: Type should be set for Store Type|
|SCHEMA|Level: Type should be set for Store State|
|DATABASE|Table: Schema must be set|
