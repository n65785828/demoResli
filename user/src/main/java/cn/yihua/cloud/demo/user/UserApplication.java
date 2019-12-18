package cn.yihua.cloud.demo.user;


import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class UserApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(
                id -> new Resilience4JConfigBuilder(id)
                        .timeLimiterConfig(TimeLimiterConfig.custom()
                                .timeoutDuration(Duration.ofSeconds(4)).build())
                        .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                        .build());
    }

//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
//
//        Customizer<Resilience4JCircuitBreakerFactory> aa = new Customizer<Resilience4JCircuitBreakerFactory>(){
//            @Override
//            public void customize(Resilience4JCircuitBreakerFactory tocustomize){
//                tocustomize.configureDefault(
//                        id -> new Resilience4JConfigBuilder(id)
//                                .timeLimiterConfig(TimeLimiterConfig.custom()
//                                        .timeoutDuration(Duration.ofSeconds(4)).build())
//                                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
//                                .build());
//            }
//        };
//        return aa;
//    }



    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }



//    @Bean
//    public Customizer<HystrixCircuitBreakerFactory> defaultConfig() {
//        return factory -> factory.configureDefault(id -> HystrixCommand.Setter
//                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(id))
//                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(3000)));
//    }



}
