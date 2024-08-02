package com.ctw.workstation;

import io.quarkus.logging.Log;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.Map;

public class DatabaseTestResource implements QuarkusTestResourceLifecycleManager {

//    PostgreSQLContainer postgres;

    @Override
    public Map<String, String> start() {
        Log.infov("About to start", "DatabaseTestResource");
        return Map.of("quarkus.log.console.json", "false");
    }

    @Override
    public void stop() {
        Log.infov("About to stop", "DatabaseTestResource");
    }
}
