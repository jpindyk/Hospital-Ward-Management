## Hospital Ward Management (Backend)

Project of an application for managing the hospital wards, which allows logged user to register patients, conduct surveys on past diseases, add daily observations or assign the attending doctor and the hospital room.
The availability of the above-mentioned options depends on the permissions of user (available: Registrar, Nurse, Ward Nurse, Admin)

### Technologies

Project are done in Java 11 with Spring Boot version 2.6.8, using Maven, Spring Data JPA, Hibernate, Spring Security and Lombok.
Application and database based on MySQL and launched on Amazon Web Services.
### How To Use

Application is deployed on AWS Cloud using Elastic Beanstalk Service, address: http://hospitalwardmanagement-env.eba-zacfez6f.eu-central-1.elasticbeanstalk.com/

To test the application you can use e.g. Postman, ready-made request for Postman set are available here:
https://drive.google.com/file/d/1iTBq25BZVRe_72IaaHLRbG4kHUoyemzr/view?usp=sharing

In database is added one user with admin role, sign in is available with data below:
| Email         | Password      |
| ------------- |:-------------:|
| admin@hwm.pl  | admin123      | 

