package ru.bsu.yellowguitarbend;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.bsu.yellowguitarbend.infrastructure.persistance.repository.adapter.mapper.OrderMapper;

@SpringBootApplication
@RequiredArgsConstructor
public class YellowGuitarBendApplication {


  public static void main(String[] args) {
    SpringApplication.run(YellowGuitarBendApplication.class, args);
  }


}
