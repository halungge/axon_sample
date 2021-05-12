package ch.mlz.axon.todo.app.configuration.multitenancy;

import lombok.RequiredArgsConstructor;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class HibernateConfiguration {
    final private JpaProperties jpaProperties;

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        return new HibernateJpaVendorAdapter();
    }

    @Bean(name= "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource,
                                                                           MultiTenantConnectionProvider multitenantConnectionProvider,
                                                                           CurrentTenantIdentifierResolver currentTenantResolver){
        //enhance JPA properties with multi tenancy stuff
        Map<String, Object> properties = new HashMap<>(jpaProperties.getProperties());
        properties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
        properties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantResolver);
        properties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multitenantConnectionProvider);

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan("ch.mlz.axon", "org.axonframework.eventsourcing.eventstore.jpa", "org.axonframework.eventhandling.tokenstore.jpa");
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactory.setJpaPropertyMap(properties);
        return entityManagerFactory;

    }
}
