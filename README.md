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
added a simple logging event handler (ch.mlz.axon.todo.app.query.log) that logs creation events up to a given point in time to the console.
It runs only in replay mode.

### run event store with liquibase
- Axon tables are created by liquibase. For the changeset see `src/main/resources/db/changelog/db.changelog_20201028_axon_tables.yml`
The changeset files was created with liquibase from existing tables: see command in `liquibase_initial` 

### Multitenancy
-TODO run multitenant application (schema per tenant)
