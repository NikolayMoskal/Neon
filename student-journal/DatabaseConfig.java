
/**
 * Database Configuration via Hibernate
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages =
{ "" })
@PropertySource("classpath:database.properties")
public class DatabaseConfig
{
	@Value("${hibernate.driver}")
	private String driverClass;
	@Value("${hibernate.password}")
	private String password;
	@Value("${hibernate.url}")
	private String url;
	@Value("${hibernate.username}")
	private String user;

	/**
	 * @return DataSource
	 */
	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		return dataSource;
	}

	/**
	 * @return LocalContainerEntityManagerFactoryBean
	 */
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory()
	{
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setJpaProperties(createJpaProperties());
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("");
		return entityManagerFactoryBean;
	}

	/**
	 * @return Properties
	 */
	private Properties createJpaProperties()
	{
		Properties result = new Properties();
		result.setProperty("hibernate.hbm2ddl.auto", "validate");
		result.setProperty("hibernate.default_schema", "public");
		return result;
	}

	/**
	 * @param entityManagerFactory
	 * @return JpaTransactionManager
	 */
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory)
	{
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	/**
	 * @return PropertySourcesPlaceholderConfigurer
	 */
	@Bean
	public PropertySourcesPlaceholderConfigurer propertyConfigInDev()
	{
		return new PropertySourcesPlaceholderConfigurer();
	}
}
