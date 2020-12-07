package ch.mlz.axon.todo.app.tenancy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@Slf4j
@RequiredArgsConstructor
public class TenantConnectionProvider implements MultiTenantConnectionProvider {

    public final static String DEFAULT_SCHEMA = "public";
    final private DataSource datasource;


    @Override
    public Connection getAnyConnection() throws SQLException {
        final Connection connection = datasource.getConnection();
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
        try {
            connection.setSchema(tenantIdentifier);
        }catch(SQLException exception){
            throw new HibernateException("could not alter JDBC connection to set schema to '"+ tenantIdentifier+"'");
        }
        return connection;
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        log.info("Release connection for tenant {}", tenantIdentifier);
        try {
            connection.setSchema(DEFAULT_SCHEMA);
        }catch(SQLException exception){
            throw new HibernateException("could not alter JDBC connection to set schema to '"+ DEFAULT_SCHEMA+"'");
        }finally {
           connection.close();
        }
    }

    @Override
    public boolean supportsAggressiveRelease() {
        // TODO or true??
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

