package org.glila.room.utils.wrappers;

import io.github.cdimascio.dotenv.Dotenv;

public class DotEnvWrapper {

    public static void load(){
        // Load .env file from the project root
        Dotenv dotenv = Dotenv.configure()
                .filename(".env") // optional if file is named `.env` in root
                .ignoreIfMissing() // optional safety
                .load();

        // Set environment variables as system properties so Spring can read them
        dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue())
        );

    }

}
