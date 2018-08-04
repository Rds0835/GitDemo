package config.webConfig;

import Interceptors.MyInterceptor;
import Interceptors.MyInterceptor2;
import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import util.Config;

import java.lang.management.ManagementFactory;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public JettyServletWebServerFactory jettyServletWebServerFactory() {
        System.err.println("jetty Servlet....");
        JettyServletWebServerFactory jettyContainer = new JettyServletWebServerFactory();
        //jettyContainer.setPort(9000);
        jettyContainer.setPort(Config.getJettyPort());
        //默认jetty配置为最大最小都为100
        //jetty最大线程数
        int threads =Config.getJettyThreads();
        jettyContainer.setThreadPool(new QueuedThreadPool(threads, threads));
//        jettyContainer.addServerCustomizers(server -> {
//            MBeanContainer mbContainer = new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
////            server.addEventListener(mbContainer);
////            server.addBean(mbContainer);
//        });
        return jettyContainer;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(false).maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getMyInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(getTestInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public MyInterceptor getMyInterceptor(){
        return new MyInterceptor();
    }

    public MyInterceptor2 getTestInterceptor(){
       return new MyInterceptor2();
    }
}
