# Documentation
### CatalogName : tutorial_01-03_Cube_InlineTable
### Schema Minimal_Cube_With_Inline_Tables : 

	
		A minimal cube based on an inline table
	
    	An inline table is a virtual table which is completely defined within the schema file instead of using an existing database table. Inline tables should only be used exceptionally.
		The <InlineTable> element consists of 2 main components:
		- the column definition (<ColumnDefs>) and
		- the table data held in the table rows (<Rows>).
		Within the <ColumnDefs> tag, every table column is represented by a <ColumnDef> element which defines the column name (name attribute) and  datatype (type attribute).
		Each row containing the data must be defined separately by a <Row> element within the <Rows> tag of the inline table. 
		For each table cell within a row, a separate <Value> element must be created which contains the column name in the name attribute and the value as the element's content. 
		
		In this example schema, an inline table named "Fact" is used as the fact table of the cube. Therefore, the <InlineTable> element is used instead of the <Table> element.
		It consists of the string column "KEY" and the numeric column "VALUE" (<ColumnDef> within <ColumnDefs>).
		The table has one row which holds the text "A" in the "KEY" column and the value 100.5 in the "VALUE" column (<Row> & <Value> within <Rows>)
		As the inline table is the only dataset the cube refers to, no external database is needed.	
	
---
### Cubes :

    CubeOneMeasureInlineTable

---
#### Cube "CubeOneMeasureInlineTable":

    

##### Table: ""

### Cube "CubeOneMeasureInlineTable" diagram:

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
c0["CubeOneMeasureInlineTable"]{
M Measure-Sum ""
}
```
---
### Cube Matrix for Minimal_Cube_With_Inline_Tables:
```mermaid
quadrantChart
title Cube Matrix
x-axis small level number --> high level number
y-axis Low row count --> High row count
quadrant-1 Complex
quadrant-2 Deep
quadrant-3 Simple
quadrant-4 Wide

Cube CubeOneMeasureInlineTable: [0.0000, 0.0001]
```
---
