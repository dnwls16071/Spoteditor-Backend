package com.spoteditor.backend.config;

import com.spoteditor.backend.config.redis.RedisProperties;
import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
@RequiredArgsConstructor
public class RedisConfiguration {

	private final RedisProperties redisProperties;

	@Bean
	public RedissonClient redissonClient() {
		Config config = new Config();

		config.useMasterSlaveServers()
				.setMasterAddress("redis://" + redisProperties.getMaster().getHost() + ":" + redisProperties.getMaster().getPort())
				.setReadMode(ReadMode.SLAVE)
				.setTimeout(3000);

		redisProperties.getSlaves().forEach(slave ->
				config.useMasterSlaveServers().addSlaveAddress(
						"redis://" + slave.getHost() + ":" + slave.getPort()
				)
		);

		return Redisson.create(config);
	}
}
