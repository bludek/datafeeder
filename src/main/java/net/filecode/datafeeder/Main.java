package net.filecode.datafeeder;

import java.util.List;

import net.filecode.datafeeder.model.Data;
import net.filecode.datafeeder.service.DataService;

import org.apache.commons.lang.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private DataService dataService;

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private void addSomeDatas() {
        Data starWars = getFirstData();
        dataService.addData(starWars);

        Data princessBride = getSecondData();
        dataService.addData(princessBride);
    }

    private Data getSecondData() {
        Data secondData = new Data();
        secondData.setId("3");
        secondData.setDescription("The Princess Bride");
        return secondData;
    }

    private Data getFirstData() {
        Data firstData = new Data();
        firstData.setId("1");
        firstData.setDescription("Star Wars");
        return firstData;
    }

    public void run(String... args) throws Exception {
        addSomeDatas();
        // We indexed star wars and pricess bride to our data
        // listing in elastic search

        List<Data> starWarsNameQuery = dataService.getByDescription("Star Wars");
        logger.info("Content of star wars name query is {}", starWarsNameQuery);

        List<Data> brideQuery = dataService.getByDescription("The Princess Bride");
        logger.info("Content of princess bride name query is {}", brideQuery);
    }

    public static void main(String[] args) throws Exception {
        StopWatch stopWatch = new StopWatch();
        logger.debug("STARTING STOPWATCH");
        stopWatch.start();
        @SuppressWarnings("unused")
        ApplicationContext ctx = SpringApplication.run(Main.class, args);
        logger.debug("STOPPING STOPWATCH");
        stopWatch.stop();
        logger.debug("Stopwatch time: " + stopWatch);
    }
}
