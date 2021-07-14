package com.spirit.tba.task;

import static com.spirit.tba.task.TaskType.*;

public class TbaAsynTask {
    private AsynStartRunner startRunable;
    private AsynStopRunner stopRunable;
    private AsynRestartRunner restartRunable;
    private TaskType type;

    public TbaAsynTask(ProcessInfo processInfo, StatusChangeObserver ob) {
        type = processInfo.getType();
        if (type == START) {
            startRunable = new AsynStartRunner(processInfo, ob);
        }
        else if (type == STOP) {
            stopRunable = new AsynStopRunner(processInfo);
        }
        else if (type == RESTART) {
            restartRunable = new AsynRestartRunner(processInfo);
        }
    }
    public void post() {
        switch (type) {
            case START:
            {
                new Thread(startRunable).start();
            }
            break;

            case STOP:
            {
                new Thread(stopRunable).start();
            }
            break;

            case RESTART:
            {
                new Thread(restartRunable).start();
            }
            break;

            default:
                break;
        }

    }

}
