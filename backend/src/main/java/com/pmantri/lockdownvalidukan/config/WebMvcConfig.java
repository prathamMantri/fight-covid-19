package com.pmantri.lockdownvalidukan.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	// private String[] RESOURCES = {"classpath:/META-INF/resources/", "classpath:/resources/","classpath:/static/*"};
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/public/**").addResourceLocations("classpath:/public/");
		//registry.addResourceHandler("/public/views/**").addResourceLocations("classpath:/public/views/");
	}

	@Bean
    CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
			//.addMapping("/api/**")
			.addMapping("/**")
			.allowedMethods("GET", "POST", "OPTIONS")
			.allowedOrigins(
				"*"
			)
			.allowCredentials(true)
			.allowedHeaders("*")
			.exposedHeaders(
				"Access-Control-Allow-Origin",
				"Access-Control-Allow-Credentials",
				"Accept",
				"Accept-Encoding",
				"Accept-Language",
				"Accept-Ranges",
				"Cache-Control",
				"Connection",
				"Content-Language",
				"Content-Length",
				"Content-Type",
				"Cookie",
				"Date",
				"ETag",
				"Expires",
				"Generated-By",
				"Host",
				"Keep-Alive",
				"Last-Modified",
				"Location",
				"Pragma",
				"Referer",
				"Server",
				"Set-Cookie",
				"Transfer-Encoding",
				"Upgrade-Insecure-Requests",
				"User-Agent");

	}

	@Bean
	public ShallowEtagHeaderFilter shallowEtagHeaderFilter() {
		return new ShallowEtagHeaderFilter();
	}

	/**
	 * Since java/spring have no sane way to handle serving a default "index.html" page
	 * in a subdirectory, we have to configure a viewcontroller for any directory we
	 * want to serve a default index.html page in. Sigh. This is why people who
	 * care about their sanity don't build web sites with Java.
	 */


	@Bean
	public ITemplateResolver testTemplateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setPrefix("classpath:/public/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode("HTML");
		resolver.setCacheable(false);
		resolver.setOrder(1);
		return resolver;
	}

	@Bean
	public HttpFirewall defaultHttpFirewall() {
		return new DefaultHttpFirewall();
	}



}
