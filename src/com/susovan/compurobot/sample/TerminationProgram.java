package com.susovan.compurobot.sample;

import java.io.IOException;

public class TerminationProgram {
    private static volatile boolean isRunning = true;

    public static void main(String[] args) {
        Thread inputListenerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (System.in.available() > 0) {
                            int key = System.in.read();
                            if (key == 'x' || key == 'X') { // ASCII value of Escape key
                                isRunning = false;
                                break;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        inputListenerThread.start();

        for (int i = 0; i < 100 && isRunning; i++) {
            System.out.println("Printing "+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!isRunning) {
            System.out.println("x key pressed, terminating program");
            System.exit(0);
        }
    }
}
