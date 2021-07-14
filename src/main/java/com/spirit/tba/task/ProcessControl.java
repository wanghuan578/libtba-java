package com.spirit.tba.task;

import com.spirit.tba.exception.TbaException;
import com.spirit.tba.tools.TbaSystemUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import java.io.*;
import static com.spirit.tba.exception.ErrorType.*;

@Slf4j
public class ProcessControl {

    public static int open(ProcessInfo processInfo, StatusChangeObserver ob) throws TbaException {
        Process process = null;
        int exitVal = 0;
        String command = null;
        if (TbaSystemUtils.win32()) {
            command = StringUtils.join(new String [] {
                    "cmd.exe /k start",
                    processInfo.getProcessName() + ".exe",
                    "--roomid=" + processInfo.getRoomId()
            }, " ");
        }
        else {
            command = StringUtils.join(new String [] {
                    //processInfo.getPath() + "/" + processInfo.getProcessName(),
                    processInfo.getProcessName(),
                    "--roomid=" + processInfo.getRoomId()
            }, " ");
            //throw new RuntimeException("system not available");
        }

        log.info("cmd task command: {}", command);

        try {
            process = Runtime.getRuntime().exec(command, new String[]{""}, new File(processInfo.getPath()));
        } catch (IOException exception) {
            throw new TbaException(USER_DEFINED_EXCEPTION.SetText(exception.getMessage()));
        }
        //process = Runtime.getRuntime().exec("cmd.exe /k start " + "WebrtcRdp.exe --roomid=1234", new String[]{""}, new File(path));

        //new RedirCmdStreamThread(process.getInputStream(), "INFO").start();
        //new RedirCmdStreamThread(process.getErrorStream(),"ERR").start();
        ob.onOpenSucceed(true);
        try {
            exitVal = process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return exitVal;
    }


    public static void close(ProcessInfo processInfo) throws TbaException {
        Process process = null;
        int exitVal = 0;
        try {
            String command = null;
            if (TbaSystemUtils.win32()) {
                command = StringUtils.join(new String [] {
                        "taskkill /F /im",
                        "WebrtcRdp.exe"
                }, " ");
            }
            else {
                command = StringUtils.join(new String [] {
                        "/bin/sh kill -9",
                        processInfo.getPid()}, " ");
            }

            log.info("cmd task command: {}", command);
            process = Runtime.getRuntime().exec(command);
            //process = Runtime.getRuntime().exec("cmd.exe /k start " + "WebrtcRdp.exe --roomid=1234", new String[]{""}, new File(path));

            new RedirCmdStreamThread(process.getInputStream(), "INFO").start();
            new RedirCmdStreamThread(process.getErrorStream(),"ERR").start();

            exitVal = process.waitFor();
        } catch (InterruptedException | IOException e) {
            throw new TbaException(USER_DEFINED_EXCEPTION.SetText(e.getMessage()));
        }

        if (exitVal != 0){
            if (processInfo.getType() != TaskType.RESTART) {
                throw new TbaException(TERMINAL_PROCESS_EXCEPTION);
            }
        }
    }

    public static void reopen(ProcessInfo processInfo, StatusChangeObserver ob) throws TbaException {
        close(processInfo);
        open(processInfo, ob);
    }

    static class RedirCmdStreamThread extends Thread {
        InputStream is;
        String printType;

        RedirCmdStreamThread(InputStream is, String printType) {
            this.is = is;
            this.printType = printType;
        }

        public void run() {
            try {
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String line = null;
                while ( (line = br.readLine()) != null) {
                    log.info(printType + "->" + line);
                }

            } catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
    }
}
