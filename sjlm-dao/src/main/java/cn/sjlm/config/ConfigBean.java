package cn.sjlm.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:application-pool.properties")
public class ConfigBean {

    //日志打印
    private static Logger logger = LoggerFactory.getLogger(ConfigBean.class);

    @Value("${spring.mybatis.Driver}")
    private String driver;

    @Value("${spring.mybatis.Url}")
    private String url;

    @Value("${spring.mybatis.Username}")
    private String username;

    @Value("${spring.mybatis.Password}")
    private String password;

    @Value("${spring.mybatis.InitialSize}")
    private Integer initialSize;

    @Value("${spring.mybatis.MaxActive}")
    private Integer maxActive;

    @Value("${spring.mybatis.MinIdle}")
    private Integer minIdle;

    @Value("${spring.mybatis.MaxWait}")
    private Integer maxWait;

    @Value("${spring.mybatis.Filters}")
    private String filter;

    @Value("${spring.mybatis.TimeBetweenLogStatsMillis}")
    private Integer TimeBetweenLogStatsMillis;

    //mybatis扫描mapper路径
    @Value("${spring.mybatis.scannerMapper}")
    private String scannerMapperPath;

    @Bean
    public DataSource dataSource(){
        DruidDataSource source = new DruidDataSource();
        try {
            source.setDriverClassName(this.driver);
            source.setUrl(this.url);
            source.setUsername(this.username);
            source.setPassword(this.password);
            source.setInitialSize(this.initialSize);
            source.setMaxActive(this.maxActive);
            source.setMinIdle(this.minIdle);
            source.setMaxWait(this.maxWait);
            source.setFilters(this.filter);
            source.setTimeBetweenLogStatsMillis(this.TimeBetweenLogStatsMillis);
            source.setUseUnfairLock(true);
            source.setDefaultReadOnly(false);
            source.setTestWhileIdle(true);
            source.setTestOnBorrow(false);
            source.setTestOnReturn(false);
            source.setValidationQuery("SELECT 1 from DUAL");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return source;
    }

    /**
     * 配置sqlSessionFactoryBean
     * */
    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
            throws Exception{
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:" + this.scannerMapperPath));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public MapperScannerConfigurer createSingleMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("cn.sjlm.sjlmdao.**");
        return mapperScannerConfigurer;
    }

    /**
     * 配置dataSource事务
     * */
    @Bean
    @Primary
    public DataSourceTransactionManager txManager(@Qualifier("dataSource") DataSource dataSource){
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return  manager;
    }
}
