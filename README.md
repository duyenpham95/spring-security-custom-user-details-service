# spring-security-basic-using-jdbc-user-details
This is my spring security learning udemy project notes 

### Simple default login
- Step 1: create 1 controller with 1 /welcome api, Then added spring-security dep into pom -> The login auth is provided by default when entering localhost:8080/welcome
- Step 2: add username & password in application.properties file instead of using default generated username, password

### Changing the default Security Configuration
- Since `WebSecurityConfigurerAdapter` deprecated, Create `SecurityConfiguration` using a `SecurityFilterChain` Bean to configure HttpSecurity or a WebSecurityCustomizer Bean to configure WebSecurity instead

### Modifying SecurityConfiguration as per our custom requirements
- using antMatcher("secured-api").authenticated(): tested with no auth in postman return 401 
- using antMatcher("not-secured-api").permitAll(): tested with no auth in postman return 200

### Implement custom UserDetailsService
- add postgres driver, jdbc api dependency
- create datasource
- Instead of default user table as JDBC of spring security using, create custom_user table instead
- application.properties
- Using JPA to mapping and query entity from DB
- Create `CustomUser` (map with custom table) that implements `UserDetails`
- Create `CustomUserDetailsService` implement `UserDetailsService`: SpringSecurity will auto detect `UserDetailsService` bean to use for secure
- Notethat need to create `PasswordEncoder` bean
- When testing in Postman using basic auth with username and password values in DB (find values in `db.sql` in source code)
- Add testcase using `@WebMvcTest`

### Implement custom AuthenticationProvider
- Create `CustomAuthenticationProvider` implements `AuthenticationProvider`
- `CustomAuthenticationProvider` override `authenticate` and `supports` method
  - `authenticate`: implements logic to authenticate
  - `supports`: indicates which type of Authentication classes use for this `CustomAuthenticationProvider`

- Note that: we can have a number of `AuthenticationProvider` and `ProviderManger` (get list of all providers) will take responsibility to call each of provider to we get successful authentication
- `AuthenticationProvider` leverages `Authentication` and `Pricipal` interfaces instead of `UserDetails` in `UserDetailsService`
- When testing in Postman using basic auth with username and password values in DB (find values in `db.sql` in source code)
- When debugging, can see that break point would not jump into `CustomUserDetailsService` any more because it jumps into `CustomAuthenticationProvider`
- Look into the pdf again for knowledge revision that: Spring Security flow goes to `AuthenticationProvider` before going to `UserDetailsService`