package br.edu.ifpb.foodstore.service.log;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogHandler logHandler;

    public void debug(String message) {
        logHandler.criandoLog("debug");
        logHandler.criandoLog(message);
    }

    public void info(String message){
        logHandler.criandoLog(message);
    }
    public void error(String message){
        logHandler.criandoLog("error");
        logHandler.criandoLog(message);
    }

}
