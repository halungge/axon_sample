package ch.mlz.axon.todo.app.configuration;

import liquibase.integration.spring.MultiTenantSpringLiquibase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class LiquibaseConfiguration {
    private static final String defaultChangeLog =  "classpath:/db/changelog/db.changelog-master.yaml";

    @Bean
    public MultiTenantSpringLiquibase liquibaseMultitenant(DataSource dataSource)
            throws SQLException {
        MultiTenantSpringLiquibase multiTenantSpringLiquibase = new MultiTenantSpringLiquibase();
        multiTenantSpringLiquibase.setDataSource(dataSource);

        Statement stmt = dataSource.getConnection().createStatement();

        ResultSet rs = stmt
                .executeQuery("SELECT sc.schema_name FROM customer_schemata sc");
        List<String> schemas = new ArrayList<>();
        while (rs.next()) {
            String schemaName = rs.getString("schema_name");
            log.info(schemaName);
            dataSource.getConnection().createStatement()
                    .executeUpdate("CREATE SCHEMA IF NOT EXISTS " + schemaName);
            schemas.add(schemaName);
        }

        multiTenantSpringLiquibase.setSchemas(schemas);
        multiTenantSpringLiquibase.setChangeLog(
                "classpath:db/changelog/schemata/db.changelog-schemata-master.yaml");
        multiTenantSpringLiquibase.setShouldRun(true);

        return multiTenantSpringLiquibase;
    }
}
