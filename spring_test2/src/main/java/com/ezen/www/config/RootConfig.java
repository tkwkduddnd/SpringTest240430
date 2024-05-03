package com.ezen.www.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@EnableScheduling
@MapperScan(basePackages = {"com.ezen.www.repository"})
@Configuration
@EnableTransactionManagement
public class RootConfig {
    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        // log4jdbc => DB의 로그를 찍을 수 있는 드라이버 설정 변경
        hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/springtest");
        hikariConfig.setUsername("springUser");
        hikariConfig.setPassword("mysql");

        hikariConfig.setMaximumPoolSize(5); // 최대 커넥션 개수
        hikariConfig.setMinimumIdle(5); // 최소 유휴 커넥션 개수

        hikariConfig.setConnectionTestQuery("SELECT 1"); // test 쿼리문
        hikariConfig.setPoolName("springHikariCP");

        // 추가설정
        // cachePrepStmts : cache 사용 여부 설정
        hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
        // mysql 드라이버가 연결당 cache 사이즈 : 250~500 사이 권장
        hikariConfig.addDataSourceProperty("datasource.prepStmtsCacheSize", "250");
        // connection 당 캐싱할 preparedStatement의 개수 지정 옵션 : default 256
        hikariConfig.addDataSourceProperty("datasource.prepStmtsCacheSqlLimit", "true");
        // mysql 서버에서 최신 이슈가 있을 경우 지원을 받을 것인지 설정
        hikariConfig.addDataSourceProperty("datasource.useServerPrepStmts", "true");

        // hikari에 대한 dataSource 생성
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);

        return hikariDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlFactoryBean = new SqlSessionFactoryBean();
        sqlFactoryBean.setDataSource(dataSource());
        sqlFactoryBean.setMapperLocations(
                applicationContext.getResources("classpath:/mappers/*.xml"));
        
        sqlFactoryBean.setConfigLocation(
        		applicationContext.getResource("classpath:/MybatisConfig.xml"));

        return sqlFactoryBean.getObject();
    }

    // 트랜잭션 매니저 설정
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
