package com.susovan.compurobot.sample;

import java.awt.Robot;

public class MouseMoveDelay {
    public static void main(String[] args) {
        try {
            Robot robot = new Robot();
            moveCursor(robot, 100, 100, 20, 900);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void moveCursor(Robot robot, int sourceX, int sourceY, int targetX, int targetY) {
        int currentX = sourceX;
        int currentY = sourceY;

        while (currentX != targetX || currentY != targetY) {
            if (currentX < targetX) {
                currentX = Math.min(currentX + 20, targetX);
            } else if (currentX > targetX) {
                currentX = Math.max(currentX - 20, targetX);
            }

            if (currentY < targetY) {
                currentY = Math.min(currentY + 20, targetY);
            } else if (currentY > targetY) {
                currentY = Math.max(currentY - 20, targetY);
            }

            robot.mouseMove(currentX, currentY);
            robot.setAutoDelay(50); // Delay for visibility
        }
    }
}