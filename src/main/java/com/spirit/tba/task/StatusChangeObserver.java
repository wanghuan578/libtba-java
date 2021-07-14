package com.spirit.tba.task;

public interface StatusChangeObserver {
    void onOpenSucceed(boolean flag);
    void onCloseSucceed(boolean flag);
    void onReOpenSucceed(boolean flag);
}
