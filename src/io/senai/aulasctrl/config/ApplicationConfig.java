package io.senai.aulasctrl.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("io.senai.aulasctrl")
@Import(value = {WebConfig.class, PersistenceConfig.class})
public class ApplicationConfig {

}
