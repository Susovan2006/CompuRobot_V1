package com.susovan.compurobot.main;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyboardShortcutUtil {
    private static final Map<String, Integer> KEY_MAP = new HashMap<>();

    static {
        KEY_MAP.put("CNTL", KeyEvent.VK_CONTROL);
        KEY_MAP.put("SHIFT", KeyEvent.VK_SHIFT);
        KEY_MAP.put("ALT", KeyEvent.VK_ALT);
        KEY_MAP.put("DEL", KeyEvent.VK_DELETE);
        KEY_MAP.put("C", KeyEvent.VK_C);
        KEY_MAP.put("V", KeyEvent.VK_V);
        KEY_MAP.put("X", KeyEvent.VK_X);
        KEY_MAP.put("A", KeyEvent.VK_A);
        KEY_MAP.put("Z", KeyEvent.VK_Z);
        KEY_MAP.put("F1", KeyEvent.VK_F1);
        KEY_MAP.put("F2", KeyEvent.VK_F2);
        KEY_MAP.put("F3", KeyEvent.VK_F3);
        KEY_MAP.put("F4", KeyEvent.VK_F4);
        KEY_MAP.put("F5", KeyEvent.VK_F5);
        KEY_MAP.put("F6", KeyEvent.VK_F6);
        KEY_MAP.put("F7", KeyEvent.VK_F7);
        KEY_MAP.put("F8", KeyEvent.VK_F8);
        KEY_MAP.put("F9", KeyEvent.VK_F9);
        KEY_MAP.put("F10", KeyEvent.VK_F10);
        KEY_MAP.put("F11", KeyEvent.VK_F11);
        KEY_MAP.put("F12", KeyEvent.VK_F12);
        // Add more keys as needed
    }

    public static void performShortcut(Robot robot, String shortcut) {
        String[] keys = shortcut.split("\\+");
        try {
            for (String key : keys) {
                robot.keyPress(KEY_MAP.get(key.toUpperCase()));
            }
            for (String key : keys) {
                robot.keyRelease(KEY_MAP.get(key.toUpperCase()));
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
