# pdp-spring-core

project for resolving tasks from _**Spring Core**_ module in scope of preparing to assesment.

## Task
### Create a Spring-based module, which handles event ticket booking. 

Rules:
- Implement three service classes: UserService, EventService, Ticket service.
Which should contain user, event, and booking-related functionality accordingly. Create an implementation of the BookingFacade interface which should delegate method calls to services mentioned above.
- Implement DAO objects for each of the domain model entities (User, Event, Ticket). They should store in and retrieve data from a common in-memory storage - java map. Each entity should be stored under a separate namespace, so you could list particular entity types.
- Storage should be implemented as a separate spring bean. Implement the ability to initialize storage with some prepared data from the file during the application start (use spring bean post-processing features). Path to the concrete file should be set using property placeholder and external property file.
- DAO with storage bean should be inserted into services beans using auto wiring. Services beans should be injected into the facade using constructor-based injections. The rest of the injections should be done in a setter-based way.
- Cover code with unit tests.
- Code should contain proper logging.
