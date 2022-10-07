# pdp-spring-data

project for resolving tasks from _**Spring Data Access**_ module in scope of preparing to assessment.

## Task
### Create a Spring-based module, which handles event ticket booking. 

Rules:
- Using the Hibernate ORM framework, update existing models.
- Add a new model to the application – UserAccount, it should store the amount of prepaid money the user has in the system, which should be used during the booking procedure. Add methods for refilling the account to the BookingFacade class. Add DAO and service objects for the new entities. Add ticketPrice field to Event entity.
- Create a database schema for storing application entities. Provide SQL script with schema creation DDL for DBMS of your choice.
- Update DAO objects so that they inherit from one of the Spring Data interfaces, for example – CrudRepository. They would store and retrieve application entities from the database. Use transaction management to perform actions in a transaction where it necessary (define the approach to implementing transactions with a mentor). Configure Hibernate for work with DBMS that you choose.
- Update ticket booking methods to check and withdraw money from user account according to the ticketPrice for a particular event.
- Cover code with unit tests. Code should contain proper logging.
