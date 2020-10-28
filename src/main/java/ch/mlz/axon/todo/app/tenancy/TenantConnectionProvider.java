package ch.mlz.axon.todo.app.tenancy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
@Component
@Slf4j
@RequiredArgsConstructor
public class TenantConnectionProvider implements  MultiTenantConnectionProvider{

        public final static String DEFAULT_SCHEMA = "nottiswil";
        final private DataSource datasource;

        @Override
        public Connection getAnyConnection() throws SQLException {
            final Connection connection = datasource.getConnection();
            connection.setSchema(DEFAULT_SCHEMA);
            return connection;
        }

        @Override
        public void releaseAnyConnection(Connection connection) throws SQLException {
            connection.close();
        }

        @Override
        public Connection getConnection(String tenantIdentifier) throws SQLException {
            log.info("Get connection for tenant {}", tenantIdentifier);
            final Connection connection = getAnyConnection();
            connection.setSchema(tenantIdentifier);
            return connection;
        }

        @Override
        public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
            log.info("Release connection for tenant {}", tenantIdentifier);
            connection.setSchema(DEFAULT_SCHEMA);
            releaseAnyConnection(connection);
        }

        @Override
        public boolean supportsAggressiveRelease() {
            return false;
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

