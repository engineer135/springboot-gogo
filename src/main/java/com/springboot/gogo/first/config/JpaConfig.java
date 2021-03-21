package com.springboot.gogo.first.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // jpa auditing 활성화
public class JpaConfig {
}
