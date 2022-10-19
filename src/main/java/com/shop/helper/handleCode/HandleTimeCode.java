package com.shop.helper.handleCode;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
public class HandleTimeCode {
    private static final TimeUnit UNITS = TimeUnit.SECONDS; // your time unit
    private final Date now = new Date();
    private final Map<Path, ScheduledFuture<?>> futures = new HashMap<>();
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    public TimeCode timeCodeExCode;

    public HandleTimeCode() {
    }

    private void writeToFileJson(TimeCode timeCode) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("timeCode.json"), timeCode);
    }

    private TimeCode readCode() {
        TimeCode timeCode = null;
        try {
            File file = new File("timeCode.json");
            if (file.exists()) {
                ObjectMapper objectMapper = new ObjectMapper();
                timeCode = objectMapper.readValue(file, TimeCode.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return timeCode;
    }

    public boolean fileNotFound() {
        boolean filsExists = false;
        File file = new File("timeCode.json");
        if (file.exists()) {
            filsExists = true;
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                this.timeCodeExCode = objectMapper.readValue(file, TimeCode.class);
//                Date code = new Date(this.timeCodeExCode.getTimeExpired());
//                if (this.now.after(code)) {
//                    this.timeCodeExCode.setCode("");
//                    new File("timeCode.json").deleteOnExit();
//                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return filsExists;
    }

    public TimeCode timeCode() {
        TimeCode timeCode;
        if (this.readCode() == null) {
            timeCode = new TimeCode();
            timeCode.setTimeExpired(System.currentTimeMillis() + (1000L * 60L * 5L));
            timeCode.setCode(this.ramdomCode());
            try {
                this.writeToFileJson(timeCode);
                Date code = new Date(timeCode.getTimeExpired());
                if (this.now.after(code)) {
                    timeCode.setCode("");
                    new File("timeCode.json").deleteOnExit();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            timeCode = this.readCode();
            Date code = new Date(timeCode.getTimeExpired());
            if (this.now.after(code)) {
                timeCode.setCode("");
                new File("timeCode.json").deleteOnExit();
            }
        }
        this.onFileAccess();
        return timeCode;
    }

    private void deleteWhenTimeExpired() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                new File("timeCode.json").delete();
//                try {
//                    Files.delete(new File("timeCode.json").toPath());
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
                System.out.println("Deleted the file abc");
            }
        };
        long delay = (1000L * 60L * 5L);
        Timer timer = new Timer("Timer");
        timer.schedule(timerTask, delay);
    }

    private String ramdomCode() {
        String code = null;
        for (int i = 0; i < 100000; i++) {
            code = String.valueOf(100000 + Math.round(Math.random() * 899900));
        }
        return code;
    }

    public void scheduleForDeletion(Path path, long delay) {
        ScheduledFuture<?> future = executor.schedule(() -> {
            try {
                Files.delete(path);
            } catch (IOException e) {
                // failed to delete
            }
        }, delay, UNITS);

        futures.put(path, future);
    }

    public void onFileAccess() {
        File file = new File("timeCode.json");
        Path path = file.toPath();
        ScheduledFuture<?> future = futures.get(path);
        if (future != null) {

            boolean result = future.cancel(false);
            if (result) {
                // reschedule the task
                futures.remove(path);
                scheduleForDeletion(path, 1000L * 60L * 5L);
                this.timeCodeExCode.setCode("");
            } else {
                // too late, task was already running
            }
        }
    }
}

