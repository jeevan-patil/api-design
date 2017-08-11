package xyz.jeevan.api.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import xyz.jeevan.api.converter.AuthenticationTokenConverter;

@Configuration
public class MongoConfig {

  @Bean
  public CustomConversions customConversions() {
    List<Converter<?, ?>> converters = new ArrayList<>();
    converters.add(new AuthenticationTokenConverter());
    return new CustomConversions(converters);
  }
}
