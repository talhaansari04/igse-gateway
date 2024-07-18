//package com.igse.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.config.CorsRegistry;
//import org.springframework.web.reactive.config.WebFluxConfigurer;
//
//@Configuration
//public class GlobalConfig implements WebFluxConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:4200")
//                .allowedOrigins("Access-Control-Allow-Origin", "http://localhost:4200")
//                .allowedHeaders("*")
//                .allowedMethods("*");
//    }
//
//}
