package config.datasource;

import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {

    @Bean("distributionLockDataSource")
    public DataSource DistributionLockDataSource(Environment environment){
        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        buildProperties(environment, "spring.datasource.distributionLock.", dataSource);
        dataSource.setIdleConnectionTestPeriodInMinutes(5);
        dataSource.setIdleMaxAgeInMinutes(30);
        dataSource.setMaxConnectionsPerPartition(20);
        dataSource.setMinConnectionsPerPartition(1);
        dataSource.setPartitionCount(10);
        dataSource.setAcquireIncrement(5);
        dataSource.setStatementsCacheSize(150);
        dataSource.setConnectionTestStatement("select 1 from dual");
        return dataSource;
    }


    //获取配置文件配置
    public void buildProperties(Environment environment, String prefix, BoneCPDataSource securityBoneCPDataSource) {
        securityBoneCPDataSource.setJdbcUrl(environment.getProperty(prefix + "URL", "default-url"));
        securityBoneCPDataSource.setUsername(environment.getProperty(prefix + "username", "default-url"));
        securityBoneCPDataSource.setPassword(environment.getProperty(prefix + "password", "default-url"));
    }
}
