package com.shop.helper.handleCode;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class HandleTimeCode {
    private final Date now = new Date();
    private TimerTask timerTask;

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

    public TimeCode timeCode() {
        TimeCode timeCode;
        if (this.readCode() == null) {
            timeCode = new TimeCode();
            timeCode.setTimeExpired(System.currentTimeMillis() + (1000L * 60L * 5L));
            timeCode.setCode(this.ramdomCode());
            try {
                this.writeToFileJson(timeCode);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            timeCode = this.readCode();
//            Date code = new Date(timeCode1.getTimeExpired());
//            if (this.now.after(code)) {
//                timeCode1.setCode("");
//                new File("timeCode.json").deleteOnExit();
//            }
        }
        this.deleteWhenTimeExpired();
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


}

