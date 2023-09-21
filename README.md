**My implementation of a Dependency Injection Container** \
_(yes, like that one we are used to using in Spring Framework)_

**Implemented annotations:**
- @Injection - inject a bean from a container to the field (similar to the @Autowired in Spring Framework)
- @GetFromConfig - inject a value from a config file to the field (similar to the @Value in Spring Framework)
- @Singleton - construct this bean as a singleton. 
  Every time we inject this bean -  the container will return the same instance (similar to the use of the @Service annotation in Spring Framework)

**How to run**
1. Open terminal
2. cd to **../di-container**
2. Execute **./gradlew run**

_To demonstrate the container's work I chose a starship area domain.\
I want to simulate the working process of a cosmodrome that manages the launches of space rockets._

_The service is going to perform the next steps:_ 

`1. Notify spaceports staff that we are performing launch` \
`2. Security check that the launch will be safe` \
`3. Execute launch` \
`4. Define launch successfulness` \
`5. Notify the command center about launch results` 
