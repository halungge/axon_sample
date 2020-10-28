package ch.mlz.axon.todo.app.tenancy;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.sql.SQLException;

public class TenantRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return ThreadTenantContext.getCurrentTenant().orElse(TenantConnectionProvider.DEFAULT_SCHEMA);
    }

    /**
     * there are other methods to override
     * use this for setting the schema on the connection? Hikari Pooling etc
     *
     public ConnectionBuilder createConnectionBuilder() throws SQLException
    */

    /**
     * we have no shards hence we ignore this for now
     * @return
     * @throws SQLException
    public ShardingKeyBuilder createShardingKeyBuilder() throws SQLException
     */

}
