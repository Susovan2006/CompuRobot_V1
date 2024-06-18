package com.susovan.compurobot.main;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.util.Random;

public class TestRobot {
	public static void main(String[] args) {
    	try {
            Robot robot = new Robot();
            Random random = new Random();
            while (true) {
                int x = random.nextInt(Toolkit.getDefaultToolkit().getScreenSize().width);
                int y = random.nextInt(Toolkit.getDefaultToolkit().getScreenSize().height);
                System.out.println("Mouse will be moved to "+x+"--"+y);
                robot.mouseMove(x, y);
                Thread.sleep(120000); // Sleep for 2 minutes
            }
        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
