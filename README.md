# oms-api
Order Management Service

Requirements:
Develop an Order Management System with Rest API’s where API clients will be able
to add orders, edit orders, delete orders and view orders.
- An order contains buyer information, order status, shipping address. An order can have
many order items. Each order item has a quantity and is associated with a product. A
product has properties name, weight, height, image, Stock Keeping Unit (SKU), barcode
and available quantity.
- Order can have statuses: Placed, Approved, Cancelled, In Delivery, Completed
- There are two user roles: Administrator and Buyer. Buyer should be able to see only
their orders, Administrator should be able to see all orders in the system
- Once order has been placed, buyer should receive an email.
- Develop Web API’s where API clients can add orders update orders, delete orders, view
orders.