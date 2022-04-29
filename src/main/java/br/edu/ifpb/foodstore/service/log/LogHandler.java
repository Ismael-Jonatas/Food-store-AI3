package br.edu.ifpb.foodstore.service.log;

import br.edu.ifpb.foodstore.BRIDGE.DeviceLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class LogHandler {

    private DeviceLog logDevice;

    public LogHandler(DeviceLog logDevice){
        this.logDevice = logDevice;
    }


    public void criandoLog(String message) {
        if(logDevice != null){
            logDevice.criarLog(message);
        }else{
            log.error("Fail to write to log file");
        }
    }
}