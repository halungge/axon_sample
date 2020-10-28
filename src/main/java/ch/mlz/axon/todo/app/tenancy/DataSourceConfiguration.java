package ch.mlz.axon.todo.app.tenancy;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfiguration {
    private final TenantDataSourceProperties dataSourceProperties;

    @Bean
    public DataSource dataSource() {
        TenantRoutingDataSource tenantAwareDataSource = new TenantRoutingDataSource();
        tenantAwareDataSource.setTargetDataSources(dataSourceProperties.getDataSources());
        return tenantAwareDataSource;
    }
}
