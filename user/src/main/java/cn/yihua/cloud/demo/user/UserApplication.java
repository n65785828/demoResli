package cn.yihua.cloud.demo.user;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class UserApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


/*    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(
                id -> new Resilience4JConfigBuilder(id)
                        .timeLimiterConfig(TimeLimiterConfig.custom()
                                .timeoutDuration(Duration.ofSeconds(12)).build())
                        .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                        .build());
    }

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> activeFirstRegistryErrorTimeOutCustomizer() {
        return factory -> factory.configure(builder -> builder.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build()), "timeoutCb");
    }
*/

/*
    @Bean
    public Customizer<HystrixCircuitBreakerFactory> defaultConfig() {
        return factory -> factory.configureDefault(id -> HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(id))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(3000)));
    }
*/



    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }







}
