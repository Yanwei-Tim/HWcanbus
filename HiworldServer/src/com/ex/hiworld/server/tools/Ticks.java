package com.ex.hiworld.server.tools;

import android.os.Handler;
import android.os.HandlerThread;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Ticks implements Runnable {
    private static ArrayList<Runnable> $100msRunList = new ArrayList<Runnable>();
    private static ArrayList<Runnable> $1sRunList = new ArrayList<Runnable>();
    private static ArrayList<Runnable> $500msRunList = new ArrayList<Runnable>();
    private Handler mHander;

    public Ticks() {
        HandlerThread handlerThread = new HandlerThread("ticks");
        handlerThread.start();
        mHander = new Handler(handlerThread.getLooper());
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        service.scheduleAtFixedRate(this, 0, 1, TimeUnit.MILLISECONDS);
    }

    public static void addTicks100ms(Runnable runnable) {
        $100msRunList.add(runnable);
    }

    public static void removeTicks100ms(Runnable runnable) {
        $100msRunList.remove(runnable);
    }

    public static void addTicks500ms(Runnable runnable) {
        $500msRunList.add(runnable);
    }

    public static void removeTicks500ms(Runnable runnable) {
        $500msRunList.remove(runnable);
    }

    public static void addTicks1s(Runnable r) {
        $1sRunList.add(r);
    }

    public static void removeTicks1s(Runnable r) {
        $1sRunList.remove(r);
    }

    private int T = 0;
    @Override
    public void run() {
        T++;
        if (T % 100 == 0) {
            for (Runnable runnable : $100msRunList) {
                mHander.post(runnable);
            }
        }
        if (T % 500 == 0) {
            for (Runnable runnable : $500msRunList) {
                mHander.post(runnable);
            }
        }

        if (T % 1000 == 0) {
            T = 0;
            for (Runnable runnable : $1sRunList) {
                mHander.post(runnable);
            }
        }
    }

}
