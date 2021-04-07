package edu.neu.cs6510.pnnl.hunting.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class H2DatabaseRunner implements ApplicationRunner, Ordered {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("h2Template")
    private JdbcTemplate h2Template;

    @Value("${invoke.schema.location}")
    private String schema;

    @Value("${invoke.data.location}")
    private String data;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String schemaContent = this.getFileContent(schema);
//        String dataContent = this.getFileContent(data);
        h2Template.execute(schemaContent);
        logger.info("Built H2 DB");
//        h2Template.execute(dataContent);
    }

    private String getFileContent(String filePath) {
        BufferedReader bufferedReader = null;
        String string;
        StringBuilder data = new StringBuilder();
        try {
            ClassPathResource classPathResource = new ClassPathResource(filePath);
            bufferedReader = new BufferedReader(new InputStreamReader(classPathResource.getInputStream()));
            while ((string = bufferedReader.readLine()) != null) {
                data.append(string);
            }
        } catch (IOException e) {
           e.printStackTrace();
        }finally {
            if(null != bufferedReader){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data.toString();
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
