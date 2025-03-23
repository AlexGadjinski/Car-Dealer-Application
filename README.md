# Car Dealer Application

**Car Dealer Application** is a platform designed to manage the data of a car dealership, including cars, parts, suppliers, customers, and sales. The application allows for importing data from XML files, processing sales records, and generating detailed reports based on various queries. The data is organized using a **Code First** approach, ensuring seamless management and querying.

The system supports the following core functionalities:
- Importing and managing suppliers, parts, cars, customers, and sales.
- Associating cars with multiple parts and managing supplier information.
- Processing customer purchases, applying discounts, and tracking sales.
- Exporting data to XML for easy sharing and reporting.

---

## Features
### ✅ **Data Import**
- Import data from XML files:
  - `suppliers.xml` – Import suppliers.
  - `parts.xml` – Import parts and randomly assign them to suppliers.
  - `cars.xml` – Import cars and randomly assign 10–20 parts to each car.
  - `customers.xml` – Import customer information.
  - Generate sales records by randomly selecting cars, customers, and discount rates.

### ✅ **Queries and Data Export**
The system supports various queries and exports the results in structured XML format:

#### **Query 1 – Ordered Customers**  
- Get all customers ordered by birthdate (ascending).  
- Customers with the same birthdate are sorted with experienced drivers listed first.  
- Export to `ordered-customers.xml`.  

Example:
```xml
<?xml version="1.0" encoding="utf-8"?>
<customers>
  <customer>
    <id>29</id>
    <name>Louann Holzworth</name>
    <birth-date>1960-10-01T00:00:00</birth-date>
    <is-young-driver>false</is-young-driver>
  </customer>
</customers>
