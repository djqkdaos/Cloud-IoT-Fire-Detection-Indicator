package org.firedetection;

import javax.servlet.FilterRegistration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class GeneralConfig {
	@Bean
    MappingJackson2JsonView jsonView(){
        return new MappingJackson2JsonView();
    }
	@Bean
    public ObjectMapper objectMapper () {
        return new ObjectMapper();
    }


    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter (ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter
                = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);

        return mappingJackson2HttpMessageConverter;
    }
}
