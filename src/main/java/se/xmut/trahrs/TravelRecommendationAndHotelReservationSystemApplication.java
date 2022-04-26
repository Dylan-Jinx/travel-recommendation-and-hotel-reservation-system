package se.xmut.trahrs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@ComponentScan(basePackages = {"se.xmut"})
@MapperScan(basePackages = {"se.xmut.trahrs.mapper"})
@SpringBootApplication
public class TravelRecommendationAndHotelReservationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelRecommendationAndHotelReservationSystemApplication.class, args);
    }

}
