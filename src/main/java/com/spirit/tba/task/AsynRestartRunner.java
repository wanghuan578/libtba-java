package com.spirit.tba.task;

import com.alibaba.fastjson.JSON;
import com.spirit.tba.exception.TbaException;
import com.spirit.tba.tools.TbaSystemUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Slf4j
public class AsynRestartRunner implements Runnable {
    private ProcessInfo processInfo;

    public AsynRestartRunner(ProcessInfo parma) {
        this.processInfo = parma;
    }

    @Override
    public void run() {
        try {
            ProcessControl.reopen(processInfo, null);
            log.info("reopen process: [{}]", JSON.toJSONString(processInfo));
        } catch (TbaException e) {
            log.error("ProcessControl reopen error: [{}]", e.getMessage());
        }
    }


}
