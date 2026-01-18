package team.weero.app.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories(basePackages = "team.weero.app.adapter.out.auth.repository")
public class RedisConfig {}
