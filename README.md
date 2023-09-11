# Login-test
Example of Sing Up and Sign In local 

## :scroll: Motivation and Context

This project simulates the Sign Up and Sign in of a user, things to consider: 
- All information is saved locally with Sqlite. 
- For Sign Up and Sing in, there is validation of the fields, like email, passord, etc. 
- There is also a validation if a user already exists in the local database.
- In the same way, there is a validation in the Sign In if a user does not yet exist.



## :paperclip:  Technologies and topics used

### Technologies 
- Kotlin
- Corrutines like Flow
- Hilt
- Room
- Navigation Component
- Material Design 3

#### For testing: 
- JUnit
- Mockito
- Hamcrest
- Corrutines test 
- Expresso

### Topics

- Clean Architecture
- Clean Code
- SOLID
- MVVM patten
- Repository pattern
- Dependency injection

## :art: UI Test

### Auth

<img src="https://github.com/salvadormaurilio/Login-test/assets/4513422/9dc8ed4f-8d3a-4bbc-bf25-9c71e569cf4a" width="320">

### Sign Up

Sign Up Success | Some Fiel is Wrong | User al readey Exist
--- | ---  | --- 
<img src="https://github.com/salvadormaurilio/Login-test/assets/4513422/8b82777c-f4ca-467e-bf55-84f72a9a2703" width="320"> | <img src="https://github.com/salvadormaurilio/Login-test/assets/4513422/4531d148-e2f3-4d2f-87d0-3116322c68d8" width="320"> | <img src="https://github.com/salvadormaurilio/Login-test/assets/4513422/b31d75d2-8d84-453b-b08b-c4ff758d077b" width="320">

### Sign In

Sign In Success | Some Fiel is Wrong | User Invalid
--- | ---  | --- 
<img src="https://github.com/salvadormaurilio/Login-test/assets/4513422/330d0978-7797-48b3-85a7-2f135fdaa9e2" width="320"> | <img src="https://github.com/salvadormaurilio/Login-test/assets/4513422/f3a1a7c5-c0f3-4a05-a3fb-1412535968da" width="320"> | <img src="https://github.com/salvadormaurilio/Login-test/assets/4513422/77944d9d-22cf-404d-92d0-d4bc43bbb54a" width="320">

## :green_heart: How did you test it?

Install the application or run the Unit Tests and Instrumental Tests, recommended command:


### Instrumental Tests: 
```
./gradlew connectedAndroidTest
```

### Unit Tests: 

```
/gradlew test
```





