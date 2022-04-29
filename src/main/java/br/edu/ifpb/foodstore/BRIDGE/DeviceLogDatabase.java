package br.edu.ifpb.foodstore.BRIDGE;

import br.edu.ifpb.foodstore.domain.LogRegister;
import br.edu.ifpb.foodstore.repository.LogRegisterRepository;
import lombok.RequiredArgsConstructor;

import java.util.Calendar;

@RequiredArgsConstructor
public class DeviceLogDatabase implements DeviceLog {

    private LogRegisterRepository logRegisterRepository;

    public DeviceLogDatabase(LogRegisterRepository logRegisterRepository){
        this.logRegisterRepository = logRegisterRepository;
    }

    @Override
    public void criarLog(String message) {
        LogRegister logRegister = new LogRegister();
        logRegister.setMessage(message);
        logRegister.setDate(Calendar.getInstance());
        logRegisterRepository.save(logRegister);
    }
}
