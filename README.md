# WebServicesTwo

Brian Gathright - August Meyer - Nema Nemati

This is our repository for Webservices Project 2. It is also hosted on Heroku at https://damp-reef-8180.herokuapp.com/ . 

We are missing some commits because we had to rebase from bitbucket and gradle to github and maven for heroku deployment. 
The old repositories are at: https://bitbucket.org/bgathright/webserviceszewdiemarket and https://bitbucket.org/august_of_wind/webservicezewdiemarket .

Brief Architecture Info

We used Hibernate and a Postgres Database as well as deployment on Heroku and hosted on GitHub. See below
for info on individual classes.

Main.java --> code for Heroku deployment (setting up a server and servlet)

View Package
------------
Client.java --> our "testing client" it tests most of our functions and classes and tests database functionality

Data Package
------------
HibernateDao.java --> A Data Access Object to wrap our Hibernate. Its used to add, remove, and retrieve from our Postgres Database. 

HibernateSessionHelper.java --> Handles configuring hibernate and hibernate sessions

Model Package
--------------
BillingInfo.java --> billing info to be used for customers

Customer.java --> our customer class, they can place orders on products.

IReviewable.java --> Interface for any classes that can have reviews (namely products and sellers (partners))
Order.java --> class for orders: customers place orders on products and sellers are given orders for their products from the order.

Product.java --> class that represents products, contains details, prices, seller, etc.

Review.java --> a review class that has a rating system and comment section for customers to rate products and sellers

Seller.java --> this is our "partner" class. They sell products and get information on orders placed on their products

Please see code documentation and UML for more information. Please let us know if you need anything else.

Brian, August, and Nema
