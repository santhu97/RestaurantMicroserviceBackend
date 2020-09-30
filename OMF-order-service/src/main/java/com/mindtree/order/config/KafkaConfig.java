/**
 * 
 */
package com.mindtree.order.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;


/**
 * @author M1056182
 *
 */
@Configuration
@EnableKafka
public class KafkaConfig {
	
	
	private static final String BOOT_STRAP_ADDRESS = "localhost:9092";
	private static final String GROUP_ID = "group_id";

	@Bean
	public ConsumerFactory<String, Object> ConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOT_STRAP_ADDRESS);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>());
	}
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaLogListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(ConsumerFactory());
		return factory;
	}

}
