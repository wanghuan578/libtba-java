package com.spirit.tba.task;

import com.alibaba.fastjson.JSON;
import com.spirit.tba.exception.TbaException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AsynStartRunner implements Runnable {
    private ProcessInfo processInfo;
    private StatusChangeObserver observer;
    public AsynStartRunner (ProcessInfo processInfo, StatusChangeObserver ob) {
        this.processInfo = processInfo;
        this.observer = ob;
    }

    @Override
    public void run() {
        try {
            ProcessControl.open(processInfo, observer);
            log.info("open process: [{}]", JSON.toJSONString(processInfo));
        } catch (TbaException e) {
            log.error("ProcessControl open error: [{}]", e.getMessage());
        }
    }
}
