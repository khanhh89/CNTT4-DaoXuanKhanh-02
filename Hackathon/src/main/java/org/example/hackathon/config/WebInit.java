package org.example.hackathon.config;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class};
    }
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        MultipartConfigElement multipartConfig =
                new MultipartConfigElement(
                        "uploads",
                        5 * 1024 * 1024,
                        10 * 1024 * 1024,
                        0
                );
        registration.setMultipartConfig(multipartConfig);
    }
}