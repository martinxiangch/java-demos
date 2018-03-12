package org.validationDemo;

import java.util.logging.Logger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner
{
	 private static final Logger LOG = Logger.getLogger(App.class.getName());
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		LOG.info("begin to running!");
	}
}
