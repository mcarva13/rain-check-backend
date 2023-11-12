//package rain.check.backend.database;
//
//import liquibase.integration.cdi.CDILiquibaseConfig;
//import liquibase.integration.cdi.annotations.LiquibaseType;
//import liquibase.resource.ClassLoaderResourceAccessor;
//import liquibase.resource.ResourceAccessor;
//
//import javax.annotation.Resource;
//import javax.enterprise.inject.Produces;
//import javax.sql.DataSource;
//
///**
// * Liquid base producer.
// */
//public class LiquibaseProducer {
//
//    /**
//     * Data source.
//     */
//    @Resource(lookup = "java:global/PostgresDB")
//    private DataSource myDataSource;
//
//    /**
//     * Create configuration.
//     * @return {@link CDILiquibaseConfig}
//     */
//    @Produces
//    @LiquibaseType
//    public CDILiquibaseConfig createConfig() {
//        final CDILiquibaseConfig config = new CDILiquibaseConfig();
//        config.setDefaultSchema("rain_check");
//        config.setChangeLog("liquibase/db.changelog.xml");
//        return config;
//    }
//
//    /**
//     * Get sql Data source.
//     * @return {@link DataSource}
//     */
//    @Produces
//    @LiquibaseType
//    public DataSource createDataSource() {
//        return myDataSource;
//    }
//
//    /**
//     * Create Resource accessor.
//     * @return {@link ResourceAccessor}
//     */
//    @Produces
//    @LiquibaseType
//    public ResourceAccessor create() {
//        return new ClassLoaderResourceAccessor(getClass().getClassLoader());
//    }
//
//}
