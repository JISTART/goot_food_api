package boot.set.test.sik4.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(value="boot.set.test.sik4.mapper", sqlSessionFactoryRef="sik4SqlSessionFactory")
@EnableTransactionManagement
public class Sik4DatabaseConfig {
	@Bean(name = "sik4Datasource", destroyMethod= "close")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource sik4DataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "sik4SqlSessionFactory")
	public SqlSessionFactory sik4SqlSessionFactory(@Qualifier("sik4Datasource") DataSource sik4Datasource,
			ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(sik4Datasource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:sql/mapper/*.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("boot.set.test.sik4.domain");
		sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
		return sqlSessionFactoryBean.getObject();
	}

	@Bean(name = "sik4SqlSessionTemplat1e")
	public SqlSessionTemplate sik4SqlSessionTemplate(SqlSessionFactory omsSqlSessionFactory) {
		return new SqlSessionTemplate(omsSqlSessionFactory);
	}

	@Bean
	public PlatformTransactionManager sik4TransactionManager(@Qualifier("sik4Datasource") DataSource sik4Datasource) {
		return new DataSourceTransactionManager(sik4Datasource);
	}
}
