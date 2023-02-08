package com.bjpowernode.masterslave.config;
import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages={"com.bjpowernode.masterslave.master.mapper"},sqlSessionFactoryRef="sqlSessionFactoryBean")
public class DateSourceConfig {
     @Value("${master.datasource.driver-class-name}")
     private String masterDriverClassName;
     @Value("${master.datasource.url}")
     private String masterUrl;
     @Value("${master.datasource.username}")
     private String masterUsername;
     @Value("${master.datasource.password}")
     private String masterPassword;



     @Value("${slave.datasource.driver-class-name}")
     private String slaveDriverClassName;
     @Value("${slave.datasource.url}")
     private String slaveUrl;
     @Value("${slave.datasource.username}")
     private String slaveUsername;
     @Value("${slave.datasource.password}")
     private String slavePassword;



     //      <!--配置主库3307的数据源 -->
//    <bean id="masterDruidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
//        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
//        <property name="url" value="jdbc:mysql://192.168.115.129:3307/workdb"/>
//        <property name="username" value="root"/>
//        <property name="password" value="123456"/>
//    </bean>
     @Bean
     public DruidDataSource masterDruidDataSource (){
          DruidDataSource dataSource=new DruidDataSource();
          dataSource.setDriverClassName(masterDriverClassName);
          dataSource.setUrl(masterUrl);
          dataSource.setUsername(masterUsername);
          dataSource.setPassword(masterPassword);
          return dataSource;
     }

     @Bean
     public DruidDataSource slaveDruidDataSource(){
          DruidDataSource dataSource=new DruidDataSource();
          dataSource.setDriverClassName(slaveDriverClassName);
          dataSource.setUrl(slaveUrl);
          dataSource.setUsername(slaveUsername);
          dataSource.setPassword(slavePassword);
          return dataSource;
     }

     @Bean
     public MyDataSource myDataSource(DruidDataSource masterDruidDataSource,DruidDataSource slaveDruidDataSource){
          MyDataSource myDataSource = new MyDataSource();
          myDataSource.setDefaultTargetDataSource(masterDruidDataSource);
          Map dataSourceMap = new HashMap();
          dataSourceMap.put("master",masterDruidDataSource);
          dataSourceMap.put("slave",slaveDruidDataSource);
          myDataSource.setTargetDataSources(dataSourceMap);
          return myDataSource;
     }

//    <!--配置主库3307的连接工厂 -->
//    <bean id="masterSqlSessionFactoryBean" class="org.mybatis.spring.SqlSessionFactoryBean">
//        <property name="dataSource" ref="masterDruidDataSource"/>
//    </bean>
     @Bean
     public SqlSessionFactoryBean sqlSessionFactoryBean(MyDataSource myDataSource){
          SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
          sqlSessionFactoryBean.setDataSource(myDataSource);
          return sqlSessionFactoryBean;
     }
//    <!--配置主库3307的包扫描 -->
//    <bean id="masterMapperScannerConfigurer" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
//        <property name="sqlSessionFactoryBeanName" value="masterSqlSessionFactoryBean"/>
//        <property name="basePackage" value="com.bjpowernode.masterslave.master.mapper"/>
//    </bean>
//     @Bean
//     public MapperScannerConfigurer masterMapperScannerConfigurer(){
//          MapperScannerConfigurer mapperScannerConfigurer=new MapperScannerConfigurer();
//          mapperScannerConfigurer.setBasePackage("com.bjpowernode.masterslave.master.mapper");
//          mapperScannerConfigurer.setSqlSessionFactoryBeanName("masterSqlSessionFactoryBean");
//          return mapperScannerConfigurer;
//     }
}
