package com.ir.learning.springbootpoc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;



@SpringBootApplication//(exclude=DataSourceAutoConfiguration.class) // if you want to disable auto-configure
//@EnableJpaRepositories(basePackageClasses={ProductRepository.class, Product.class}) // not required if @SpringBootApplication is there with default settings
//@EnableTransactionManagement// not required if @SpringBootApplication is there
//@EnableWebMvc // not required if @SpringBootApplication is there
@ComponentScan(basePackages="com.ir.learning")
@PropertySource("Test.properties") //for loading different properties other than default application.properties
@EnableAsync
public class SpringBooter implements CommandLineRunner/*extends SpringBootServletInitializer*/ /*extends WebMvcConfigurerAdapter*/ {

	private static final Log LOGGER = LogFactory.getLog(SpringBooter.class); //Using JCL
	
	public static void main(String[] args) {
		//SpringApplication.run(SpringBooter.class, args);
		new SpringApplicationBuilder(SpringBooter.class).run(args);
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				LOGGER.debug("*****called when program is terminated.....");
			}
		});
		
	}

	// To immediately run the app just after the spring context is up
	//It is called before SpringApplication.run()
	@Override
	public void run(String... args) throws Exception {
		LOGGER.debug("I am inside the CommandLineRunner");
		
	}
	
	/*@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("Home");
	}*/
	
	/*@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}*/
	
	/*@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		irvr.setPrefix("/WEB-INF/jsp/");
		irvr.setSuffix(".jsp");
		return irvr;
	}*/
	
	/*Note that a WebApplicationInitializer is only needed if you are building a war file and deploying it.
	If you prefer to run an embedded container then you won't need this at all.
*/	
	/*@Override
    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(SpringBooter.class);
    }*/
}
