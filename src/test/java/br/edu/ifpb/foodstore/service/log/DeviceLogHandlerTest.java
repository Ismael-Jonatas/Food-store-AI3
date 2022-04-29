package br.edu.ifpb.foodstore.service.log;

import br.edu.ifpb.foodstore.BRIDGE.DeviceLog;
import br.edu.ifpb.foodstore.BRIDGE.DeviceLogDatabase;
import br.edu.ifpb.foodstore.BRIDGE.DeviceLogFile;
import br.edu.ifpb.foodstore.domain.LogRegister;
import br.edu.ifpb.foodstore.repository.LogRegisterRepository;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.nio.file.Files;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DeviceLogHandlerTest {

    @SpyBean
    private LogHandler logHandler;

    @MockBean
    private LogRegisterRepository logRegisterRepository;

    @BeforeEach
    public void init() {
    }

    @SneakyThrows
    @Test
    void logTest_database() {
        DeviceLog logFileDatabase = new DeviceLogDatabase(logRegisterRepository);
        // Atribui a propriedade type em LogHandler a ter o valor DATABASE
        ReflectionTestUtils.setField(logHandler, // injeta propriedade nesse objeto
                "logDevice", // campo a ser sobrescrito
                logFileDatabase); // objeto a ser injetado

        Calendar calendar = mock(Calendar.class);
        // É necessário fazer mock da chamada estática Calendar.getInstance() para que corresponda ao objeto criado no teste
        try (MockedStatic<Calendar> mocked = mockStatic(Calendar.class)) {
            mocked.when(Calendar::getInstance).thenReturn(calendar);
            LogRegister logRegister = new LogRegister();
            logRegister.setMessage("test message");
            logRegister.setDate(calendar);
            logHandler.criandoLog("test message");
            InOrder orderVerifier = Mockito.inOrder(logRegisterRepository);
            orderVerifier.verify(logRegisterRepository).save(logRegister);
        }
    }

    @SneakyThrows
    @Test
    void logTest_file() {
        DeviceLog logFile = new DeviceLogFile();
        // Atribui a propriedade type em LogHandler a ter o valor FILE
        ReflectionTestUtils.setField(logHandler, // injeta propriedade nesse objeto
                "logDevice", // campo a ser sobrescrito
                logFile); // objeto a ser injetado

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Necessário fazer mock da chamada estática do Calendar.getInstance().getTime() para forçar data exata e verificar formato do log
        try (MockedStatic<Calendar> mocked = mockStatic(Calendar.class, Mockito.CALLS_REAL_METHODS)) {
            Calendar calendar = mock(Calendar.class);
            mocked.when(Calendar::getInstance).thenReturn(calendar);
            Date date = format.parse("2022-04-16 07:47:31");
            when(calendar.getTime()).thenReturn(date);
            logHandler.criandoLog("test message");
            File file = new File("/tmp/log.log");
            assertTrue(file.exists());
            assertThat(Files.readString(file.toPath()), Matchers.equalTo(String.format("%s: %s\n", "2022-04-16 07:47:31", "test message")));
            file.delete();
        }


    }

}
