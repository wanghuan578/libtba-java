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

//        Process process = null;
//        int exitVal = 0;
//        try {
//            String command = null;
//            if (TbaSystemUtils.win32()) {
//                command = StringUtils.join(new String [] {
//                        "taskkill /F /im",
//                        "WebrtcRdp.exe"
//                }, " ");
//            }
//            else {
//                command = StringUtils.join(new String [] {
//                        "/bin/sh kill -9",
//                        processInfo.getPid()}, " ");
//            }
//
//            log.info("cmd task command: {}", command);
//            process = Runtime.getRuntime().exec(command);
//            //process = Runtime.getRuntime().exec("cmd.exe /k start " + "WebrtcRdp.exe --roomid=1234", new String[]{""}, new File(path));
//
//            new RedirCmdStreamThread(process.getInputStream(), "INFO").start();
//            new RedirCmdStreamThread(process.getErrorStream(),"ERR").start();
//
//            exitVal = process.waitFor();
//        } catch (InterruptedException | IOException e) {
//            e.printStackTrace();
//        }
//
//        if (exitVal != 0){
//            throw new RuntimeException("cmd任务执行失败");
//        }

        //todo
    }
}
