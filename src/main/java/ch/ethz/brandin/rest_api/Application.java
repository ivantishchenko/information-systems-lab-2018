package ch.ethz.brandin.rest_api;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.ethz.brandin.data_service.ComparisonManager;

@SpringBootApplication
public class Application {

    private final static Logger log = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        log.debug("BrandIn started!");
        ComparisonManager.INSTANCE = new ComparisonManager();
    }

}
