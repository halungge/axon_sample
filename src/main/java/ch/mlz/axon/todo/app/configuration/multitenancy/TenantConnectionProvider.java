package ch.mlz.axon.todo.app.configuration.multitenancy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@Component
@RequiredArgsConstructor
public class TenantConnectionProvider implements MultiTenantConnectionProvider {
    private final DataSource dataSource;

    @Override
    public Connection getAnyConnection() throws SQLException {
        log.info("get any connection from pool");
        return dataSource.getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        log.debug("releasing any connection");
        connection.close();
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        final Connection connection = getAnyConnection();
        log.info("get connection to schema {}", tenantIdentifier);
        connection.setSchema(tenantIdentifier);
        return connection;

    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        log.info("release connection to schema {}", tenantIdentifier);
        connection.setSchema(TenantSchemaResolver.DEFAULT_SCHEMA);
        connection.close();

    }

    @Override
    public boolean supportsAggressiveRelease() {
        return true;
    }

    @Override
    public boolean isUnwrappableAs(Class unwrapType) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        return null;
    }
}
