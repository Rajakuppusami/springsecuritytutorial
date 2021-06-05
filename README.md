
This sample code for understanding basic of spring security
1. Rest End point
2. Resource ( Web Page) accessing.

Used spring profile for disable spring security auto configuration
PROFILE -> non_security

Used of actuator for monitoring application status

Spring dev tool doc -> https://www.baeldung.com/spring-boot-devtools

Spring template engine doc -> https://www.baeldung.com/spring-template-engines


JDBC Authentication with default Spring Security Database Schema
Reference Link: https://docs.spring.io/spring-security/site/docs/current/reference/html5/#appendix

1. Internally creates USERS & AUTHORITIES table's
2. Create a instance of JdbcUserDetailsManager for CRUD operation on users

select * from users;
select * from authorities;