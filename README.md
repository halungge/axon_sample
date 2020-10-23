# axon_sample

Sample axon projects for trying things out. 
## origin

Original code forked from https://github.com/avthart/spring-boot-axon-sample but I somehow lost the original remote.

## tasks

### run without Axon Server in Axon 4.4.3. Use Spring Data Jpa event store only

for the source code to be able to **ignore Axon Server** you have to exclude the axon-server-connector in build.gradle: 
```
implementation 'org.axonframework:axon-spring-boot-starter:4.4.3', {
		exclude group:'org.axonframework', module: 'axon-server-connector'
	}
  
 ```

### checkout replaying mechano: replay up to given point in time

### run event store with liquibase
- TODO add liquibase files

### Multitenancy
-TODO run multitenant application (schema per tenant)
