package com.spirit.tba.task;

import com.alibaba.fastjson.JSON;
import com.spirit.tba.exception.TbaException;
import com.spirit.tba.tools.TbaSystemUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;

@Slf4j
public class AsynStopRunner implements Runnable {
    private ProcessInfo processInfo;

    public AsynStopRunner(ProcessInfo parma) {
        this.processInfo = parma;
    }

    @Override
    public void run() {
        try {
            ProcessControl.close(processInfo);
            log.info("terminal process: [{}]", JSON.toJSONString(processInfo));
        } catch (TbaException e) {
            log.error("ProcessControl close error: [{}]", e.getMessage());
        }
    }
}
