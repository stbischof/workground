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

### Database :"_position_":

|position_id|position_title|pay_type|min_scale|max_scale|management_role|
|---|---|---|---|---|---|
|INTEGER|STRING|STRING|null|null|STRING|

"account":

|account_id|account_parent|account_description|account_type|account_rollup|Custom_Members|
|---|---|---|---|---|---|
|INTEGER|INTEGER|STRING|STRING|STRING|STRING|

"agg_c_10_sales_fact_1997":

|month_of_year|quarter|the_year|store_sales|store_cost|unit_sales|customer_count|fact_count|
|---|---|---|---|---|---|---|---|
|INTEGER|STRING|INTEGER|null|null|null|INTEGER|INTEGER|

"agg_c_14_sales_fact_1997":

|product_id|customer_id|store_id|promotion_id|month_of_year|quarter|the_year|store_sales|store_cost|unit_sales|fact_count|
|---|---|---|---|---|---|---|---|---|---|---|
|INTEGER|INTEGER|INTEGER|INTEGER|INTEGER|STRING|INTEGER|null|null|null|INTEGER|

"agg_c_special_sales_fact_1997":

|product_id|promotion_id|customer_id|store_id|time_month|time_quarter|time_year|store_sales_sum|store_cost_sum|unit_sales_sum|fact_count|
|---|---|---|---|---|---|---|---|---|---|---|
|INTEGER|INTEGER|INTEGER|INTEGER|INTEGER|STRING|INTEGER|null|null|null|INTEGER|

"agg_g_ms_pcat_sales_fact_1997":

|gender|marital_status|product_family|product_department|product_category|month_of_year|quarter|the_year|store_sales|store_cost|unit_sales|customer_count|fact_count|
|---|---|---|---|---|---|---|---|---|---|---|---|---|
|STRING|STRING|STRING|STRING|STRING|INTEGER|STRING|INTEGER|null|null|null|INTEGER|INTEGER|

"agg_l_03_sales_fact_1997":

|time_id|customer_id|store_sales|store_cost|unit_sales|fact_count|
|---|---|---|---|---|---|
|INTEGER|INTEGER|null|null|null|INTEGER|

"agg_l_04_sales_fact_1997":

|time_id|store_sales|store_cost|unit_sales|customer_count|fact_count|
|---|---|---|---|---|---|
|INTEGER|null|null|null|INTEGER|INTEGER|

"agg_l_05_sales_fact_1997":

|product_id|customer_id|promotion_id|store_id|store_sales|store_cost|unit_sales|fact_count|
|---|---|---|---|---|---|---|---|
|INTEGER|INTEGER|INTEGER|INTEGER|null|null|null|INTEGER|

"agg_lc_06_sales_fact_1997":

|time_id|city|state_province|country|store_sales|store_cost|unit_sales|fact_count|
|---|---|---|---|---|---|---|---|
|INTEGER|STRING|STRING|STRING|null|null|null|INTEGER|

"agg_lc_100_sales_fact_1997":

|product_id|customer_id|quarter|the_year|store_sales|store_cost|unit_sales|fact_count|
|---|---|---|---|---|---|---|---|
|INTEGER|INTEGER|STRING|INTEGER|null|null|null|INTEGER|

"agg_ll_01_sales_fact_1997":

|product_id|time_id|customer_id|store_sales|store_cost|unit_sales|fact_count|
|---|---|---|---|---|---|---|
|INTEGER|INTEGER|INTEGER|null|null|null|INTEGER|

"agg_pl_01_sales_fact_1997":

|product_id|time_id|customer_id|store_sales_sum|store_cost_sum|unit_sales_sum|fact_count|
|---|---|---|---|---|---|---|
|INTEGER|INTEGER|INTEGER|null|null|null|INTEGER|

"category":

|category_id|category_parent|category_description|category_rollup|
|---|---|---|---|
|STRING|STRING|STRING|STRING|

"currency":

|currency_id|date|currency|conversion_ratio|
|---|---|---|---|
|INTEGER|DATE|STRING|null|

"customer":

|customer_id|account_num|lname|fname|mi|address1|address2|address3|address4|city|state_province|postal_code|country|customer_region_id|phone1|phone2|birthdate|marital_status|yearly_income|gender|total_children|num_children_at_home|education|date_accnt_opened|member_card|occupation|houseowner|num_cars_owned|fullname|
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
|INTEGER|STRING|STRING|STRING|STRING|STRING|STRING|STRING|STRING|STRING|STRING|STRING|STRING|INTEGER|STRING|STRING|DATE|STRING|STRING|STRING|INTEGER|INTEGER|STRING|DATE|STRING|STRING|STRING|INTEGER|STRING|

"days":

|day|week_day|
|---|---|
|INTEGER|STRING|

"department":

|department_id|department_description|
|---|---|
|INTEGER|STRING|

"employee":

|employee_id|full_name|first_name|last_name|position_id|position_title|store_id|department_id|birth_date|hire_date|end_date|salary|supervisor_id|education_level|marital_status|gender|management_role|
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
|INTEGER|STRING|STRING|STRING|INTEGER|STRING|INTEGER|INTEGER|DATE|STRING|STRING|null|INTEGER|STRING|STRING|STRING|STRING|

"employee_closure":

|employee_id|supervisor_id|distance|
|---|---|---|
|INTEGER|INTEGER|INTEGER|

"expense_fact":

|store_id|account_id|exp_date|time_id|category_id|currency_id|amount|
|---|---|---|---|---|---|---|
|INTEGER|INTEGER|TIMESTAMP|INTEGER|STRING|INTEGER|null|

"inventory_fact_1997":

|product_id|time_id|warehouse_id|store_id|units_ordered|units_shipped|warehouse_sales|warehouse_cost|supply_time|store_invoice|
|---|---|---|---|---|---|---|---|---|---|
|INTEGER|INTEGER|INTEGER|INTEGER|INTEGER|INTEGER|null|null|INTEGER|null|

"inventory_fact_1998":

|product_id|time_id|warehouse_id|store_id|units_ordered|units_shipped|warehouse_sales|warehouse_cost|supply_time|store_invoice|
|---|---|---|---|---|---|---|---|---|---|
|INTEGER|INTEGER|INTEGER|INTEGER|INTEGER|INTEGER|null|null|INTEGER|null|

"position":

|position_id|position_title|pay_type|min_scale|max_scale|management_role|
|---|---|---|---|---|---|
|INTEGER|STRING|STRING|null|null|STRING|

"product":

|product_class_id|product_id|brand_name|product_name|SKU|SRP|gross_weight|net_weight|recyclable_package|low_fat|units_per_case|cases_per_pallet|shelf_width|shelf_height|shelf_depth|
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
|INTEGER|INTEGER|STRING|STRING|STRING|null|null|null|BOOLEAN|BOOLEAN|INTEGER|INTEGER|null|null|null|

"product_class":

|product_class_id|product_subcategory|product_category|product_department|product_family|
|---|---|---|---|---|
|INTEGER|STRING|STRING|STRING|STRING|

"promotion":

|promotion_id|promotion_district_id|promotion_name|media_type|cost|start_date|end_date|
|---|---|---|---|---|---|---|
|INTEGER|INTEGER|STRING|STRING|null|TIMESTAMP|TIMESTAMP|

"region":

|region_id|sales_city|sales_state_province|sales_district|sales_region|sales_country|sales_district_id|
|---|---|---|---|---|---|---|
|INTEGER|STRING|STRING|STRING|STRING|STRING|INTEGER|

"reserve_employee":

|employee_id|full_name|first_name|last_name|position_id|position_title|store_id|department_id|birth_date|hire_date|end_date|salary|supervisor_id|education_level|marital_status|gender|
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
|INTEGER|STRING|STRING|STRING|INTEGER|STRING|INTEGER|INTEGER|TIMESTAMP|TIMESTAMP|TIMESTAMP|null|INTEGER|STRING|STRING|STRING|

"salary":

|pay_date|employee_id|department_id|currency_id|salary_paid|overtime_paid|vacation_accrued|vacation_used|
|---|---|---|---|---|---|---|---|
|TIMESTAMP|INTEGER|INTEGER|INTEGER|null|null|null|null|

"sales_fact_1997":

|product_id|time_id|customer_id|promotion_id|store_id|store_sales|store_cost|unit_sales|
|---|---|---|---|---|---|---|---|
|INTEGER|INTEGER|INTEGER|INTEGER|INTEGER|null|null|null|

"sales_fact_1998":

|product_id|time_id|customer_id|promotion_id|store_id|store_sales|store_cost|unit_sales|
|---|---|---|---|---|---|---|---|
|INTEGER|INTEGER|INTEGER|INTEGER|INTEGER|null|null|null|

"sales_fact_dec_1998":

|product_id|time_id|customer_id|promotion_id|store_id|store_sales|store_cost|unit_sales|
|---|---|---|---|---|---|---|---|
|INTEGER|INTEGER|INTEGER|INTEGER|INTEGER|null|null|null|

"store":

|store_id|store_type|region_id|store_name|store_number|store_street_address|store_city|store_state|store_postal_code|store_country|store_manager|store_phone|store_fax|first_opened_date|last_remodel_date|store_sqft|grocery_sqft|frozen_sqft|meat_sqft|coffee_bar|video_store|salad_bar|prepared_food|florist|
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
|INTEGER|STRING|INTEGER|STRING|INTEGER|STRING|STRING|STRING|STRING|STRING|STRING|STRING|STRING|TIMESTAMP|TIMESTAMP|INTEGER|INTEGER|INTEGER|INTEGER|BOOLEAN|BOOLEAN|BOOLEAN|BOOLEAN|BOOLEAN|

"store_ragged":

|store_id|store_type|region_id|store_name|store_number|store_street_address|store_city|store_state|store_postal_code|store_country|store_manager|store_phone|store_fax|first_opened_date|last_remodel_date|store_sqft|grocery_sqft|frozen_sqft|meat_sqft|coffee_bar|video_store|salad_bar|prepared_food|florist|
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
|INTEGER|STRING|INTEGER|STRING|INTEGER|STRING|STRING|STRING|STRING|STRING|STRING|STRING|STRING|TIMESTAMP|TIMESTAMP|INTEGER|INTEGER|INTEGER|INTEGER|BOOLEAN|BOOLEAN|BOOLEAN|BOOLEAN|BOOLEAN|

"time_by_day":

|time_id|the_date|the_day|the_month|the_year|day_of_month|week_of_year|month_of_year|quarter|fiscal_period|
|---|---|---|---|---|---|---|---|---|---|
|INTEGER|TIMESTAMP|STRING|STRING|INTEGER|INTEGER|INTEGER|INTEGER|STRING|STRING|

"warehouse":

|warehouse_id|warehouse_class_id|stores_id|warehouse_name|wa_address1|wa_address2|wa_address3|wa_address4|warehouse_city|warehouse_state_province|warehouse_postal_code|warehouse_country|warehouse_owner_name|warehouse_phone|warehouse_fax|
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
|INTEGER|INTEGER|INTEGER|STRING|STRING|STRING|STRING|STRING|STRING|STRING|STRING|STRING|STRING|STRING|STRING|

"warehouse_class":

|warehouse_class_id|description|
|---|---|
|INTEGER|STRING|

