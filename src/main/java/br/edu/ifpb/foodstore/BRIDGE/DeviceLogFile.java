package br.edu.ifpb.foodstore.BRIDGE;

import lombok.RequiredArgsConstructor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@RequiredArgsConstructor
public class DeviceLogFile implements DeviceLog {

    private static final String LOG_FILE_NAME = "/tmp/log.log";

    @Override
    public void criarLog(String message) {
        try {
            FileWriter fileWriter = new FileWriter(LOG_FILE_NAME, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            printWriter.printf("%s: %s\n", format.format(Calendar.getInstance().getTime()), message);
            printWriter.close();
        } catch (IOException ioException) {
            System.out.println("Fail to write to log file");
        }
    }

}
