# Fetch Strategy and Cascade Configuration

1. Introduction

In this project, I used different JPA relationships to connect the entities in the Rent a Car application. I also had to decide how related data should be loaded and what should happen when an entity is saved or deleted.

This document explains the choices I made and why I used them.

2. Relationships and Their Configurations

| Relationship           | Mapping                   | Fetch Type | Cascade                           | Reason                                                                                                                 |
|------------------------|---------------------------|------------|-----------------------------------|------------------------------------------------------------------------------------------------------------------------|
| Agency – Vehicule      | One-to-Many / Many-to-One | LAZY       | None                              | A vehicle should not be deleted just because its agency is deleted.                                                    |
| Agency – Employee      | One-to-Many / Many-to-One | LAZY       | None                              | Employees are managed separately from agencies.                                                                        |
| Vehicule – Equipment   | Many-to-Many              | LAZY       | None                              | Different vehicles can use the same equipment, so equipment should not be deleted when a vehicle is deleted.           |
| Client – Reservation   | One-to-Many / Many-to-One | LAZY       | PERSIST                           | When a new client is saved, their new reservations can also be saved.                                                  |
| Reservation – Vehicule | Many-to-One               | LAZY       | None                              | A vehicle already exists independently of a reservation.                                                               |
| Reservation – Contract | One-to-One                | LAZY       | ALL on Reservation                | A contract is linked to a reservation, so operations on the reservation can also be applied to its contract.           |
| Contract – Payment     | One-to-Many / Many-to-One | LAZY       | ALL and orphanRemoval on Contract | Payments belong to a contract. If a payment is removed from the contract, it should also be removed from the database. |
| Vehicule – Maintenance | One-to-Many / Many-to-One | LAZY       | PERSIST                           | When a new vehicle is saved, its new maintenance records can also be saved.                                            |

3. Why I Used LAZY Fetching

I used LAZY fetching for the relationships because I don't always need the related information when I retrieve an entity.

For example, when I retrieve a vehicle, I may only need its basic information. I don't necessarily need to load all its equipment and maintenance records immediately.

I also considered the difference between LAZY and EAGER fetching for the Contract–Payment relationship. EAGER would load the payments immediately with the contract, while LAZY loads them only when needed. I chose LAZY to avoid loading unnecessary information.

4. Why I Used Cascade

I did not use cascade on every relationship because some entities can exist independently of others.

For example, deleting an agency should not automatically delete its vehicles or employees. Also, equipment can be shared between different vehicles.

I used PERSIST for Client–Reservation and Vehicule–Maintenance because saving a new parent can also save its new related records.

For Reservation–Contract, I used ALL on the Reservation side because the contract is linked to that reservation.

For Contract–Payment, I used ALL together with orphanRemoval. This is because payments are dependent on their contract. If a payment is removed from the contract's collection, it should also be deleted from the database.

5. Managing Both Sides of Relationships

Some of the relationships in my project are bidirectional. This means that both entities have a reference to each other.

For example, a contract has a reference to its reservation, while a reservation also has a reference to its contract.

When creating these relationships, both sides should be updated properly to avoid inconsistencies.

6. Conclusion

After implementing the relationships, I checked that the corresponding foreign keys and join table were created in the database.

The application also started successfully after the mappings were added.

The choices of LAZY fetching and cascade operations were made based on how the different entities are related and whether they depend on each other.