package main.backend.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    @Value("${cloudinary.api-key}")
    private String cloudinaryApiKey;

    @Value("${cloudinary.api-secret}")
    private String cloudinaryApiSecret;

    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name" , cloudName ,
                        "api_key" , cloudinaryApiKey,
                        "api_secret" , cloudinaryApiSecret,
                        "secure" , true
                )
        );
    }

    // this bean is related to dealing with external api services
    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
