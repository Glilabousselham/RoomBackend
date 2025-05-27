package org.glila.room;

import org.glila.room.utils.wrappers.DotEnvWrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RoomApplication {

	public static void main(String[] args) {

		// Load .env file from the project root
		DotEnvWrapper.load();

		SpringApplication.run(RoomApplication.class, args);
	}

}
