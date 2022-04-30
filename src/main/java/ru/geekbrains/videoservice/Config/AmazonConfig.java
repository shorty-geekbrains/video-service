package ru.geekbrains.videoservice.Config;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.geekbrains.videoservice.repositories.AmazonRepo;

/**
 * @author Nick Musinov e-mail:n.musinov@gmail.com
 * 30.04.2022
 */
@Configuration
public class AmazonConfig {

    @Bean
    public AmazonS3 getS3() {
        return new AmazonRepo().buildS3();
    }

}
