package com.phamluu;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.faces.application.ProjectStage;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

//import org.acme.sample.jsf.FacesViewScope;
import org.apache.catalina.Context;
import org.primefaces.util.Constants;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.NonEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.phamluu.guru.springframework.autowiringdemo.Employee;
import com.phamluu.guru.springframework.autowiringdemo.EmployeeBean;
import com.phamluu.sample.jsf.FacesViewScope;
import com.sun.faces.config.FacesInitializer;

//uyen added
//@ImportResource(value = { "" }) 

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class SpringBootFacesApplication extends SpringBootServletInitializer {
    
    public static void main(String[] args) throws Exception {
//        SpringApplication.run(SpringBootFacesApplication.class, args);
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootFacesApplication.class, args);
        
        
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("*** Example Using byName autowiring mode ***");
		Employee emp = context.getBean("employeeByName", Employee.class);
		emp.setEid(101);
		emp.setEname("Spring Framework Guru");
		emp.showEployeeDetails();

		System.out.println("\n*** Example Using byType autowiring mode ***");
		Employee emp1 = context.getBean("employeeByType", Employee.class);
		emp1.setEid(102);
		emp1.setEname("Spring Framework Guru");
		emp1.showEployeeDetails();

		System.out.println("\n*** Example Using constructor autowiring mode ***");
		Employee emp2 = context.getBean("employeeConstructor", Employee.class);
		emp2.setEid(103);
		emp2.setEname("Spring Framework Guru");
		emp2.showEployeeDetails();

		System.out.println("\n*** Example Using @Autowire annotation on property ***");
		EmployeeBean employeeBean = ctx.getBean(EmployeeBean.class);
		employeeBean.setEid(104);
		employeeBean.setEname("Spring Framework Guru");
		employeeBean.showEployeeDetails();
        
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootFacesApplication.class);
    }
    
	@Bean
	public static CustomScopeConfigurer customScopeConfigurer() {
		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        configurer.setScopes(Collections.<String, Object>singletonMap(
                FacesViewScope.NAME, new FacesViewScope()));
		return configurer;
	}
	
	@Bean
	public ServletContextInitializer servletContextCustomizer() {
	    return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext sc) throws ServletException {
                sc.setInitParameter(Constants.ContextParams.THEME, "bootstrap");
                sc.setInitParameter(Constants.ContextParams.FONT_AWESOME, "true");
                sc.setInitParameter(ProjectStage.PROJECT_STAGE_PARAM_NAME, ProjectStage.Development.name());
            }
	    };
	}
    
	/**
	 * This bean is only needed when running with embedded Tomcat.
	 */
    @Bean
    @ConditionalOnMissingBean(NonEmbeddedServletContainerFactory.class)
    public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        
        tomcat.addContextCustomizers(new TomcatContextCustomizer() {
            @Override
            public void customize(Context context) {
                // register FacesInitializer
                context.addServletContainerInitializer(new FacesInitializer(),
                        getServletContainerInitializerHandlesTypes(FacesInitializer.class));
                
                // add configuration from web.xml
                context.addWelcomeFile("index.jsf");
                
                // register additional mime-types that Spring Boot doesn't register
                context.addMimeMapping("eot", "application/vnd.ms-fontobject");
                context.addMimeMapping("ttf", "application/x-font-ttf");
                context.addMimeMapping("woff", "application/x-font-woff");
            }
        });
        
        return tomcat;
    }
    
    @SuppressWarnings("rawtypes")
    private Set<Class<?>> getServletContainerInitializerHandlesTypes(Class<? extends ServletContainerInitializer> sciClass) {
        HandlesTypes annotation = sciClass.getAnnotation(HandlesTypes.class);
        if (annotation == null) {
            return Collections.emptySet();
        }
        
        Class[] classesArray = annotation.value();
        Set<Class<?>> classesSet = new HashSet<Class<?>>(classesArray.length);
        for (Class clazz: classesArray) {
            classesSet.add(clazz);
        }
        
        return classesSet;
    }
	
}
