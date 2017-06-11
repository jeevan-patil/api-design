package xyz.jeevan.api.config;

import com.monitorjbl.json.JsonViewSupportFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by jeevan on 11/6/17.
 */

@Configuration
public class ContextConfig extends WebMvcConfigurerAdapter {

  @Bean
  public JsonViewSupportFactoryBean views() {
    return new JsonViewSupportFactoryBean();
  }
}
