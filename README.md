**My implementation of a Dependency Injection Container** \
_(yes, like that one we are used to using in Spring Framework)_

_To demonstrate the container's work I chose a starship area domain.\
I want to simulate the working process of a cosmodrome that manages launches of space rockets._

**Implemented annotations:**
- @Injection - inject a bean from a container to the field (like @Autowired in Spring Framework)
- @GetFromConfig - inject a value from a config file to the field (like @Value in Spring Framework)
- @Singleton - construct this bean as a singleton. 
  Every time we will inject this bean -  the container will return the same instance (like @Service in Spring Framework)

**How to run**
1. Open terminal
2. cd to **../di-container**
2. Execute **./gradlew run**


_The service is going to perform the next steps:_ 

`1. Notify spaceports staff that we are performing launch` \
`2. Security check that the launch will be safe` \
`3. Execute launch` \
`4. Define launch successfulness` \
`5. Notify spaceports staff about launch results` 
