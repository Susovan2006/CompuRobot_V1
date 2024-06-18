package com.susovan.compurobot.sample;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import com.google.gson.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Action> actions = new ArrayList<>();
        JFrame frame = new JFrame();
        frame.setUndecorated(true);
        frame.setBackground(new Color(0, 0, 0, 0));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addMouseMotionListener(new MouseMotionAdapter() {
            long lastTime = System.currentTimeMillis();
            int actionId = 1;

            public void mouseMoved(MouseEvent e) {
                long currentTime = System.currentTimeMillis();
                actions.add(new Action(actionId++, "move", e.getX(), e.getY(), currentTime - lastTime));
                lastTime = currentTime;
            }
        });

        frame.addMouseListener(new MouseAdapter() {
            long lastTime = System.currentTimeMillis();
            int actionId = 1;

            public void mouseClicked(MouseEvent e) {
                long currentTime = System.currentTimeMillis();
                actions.add(new Action(actionId++, "click", e.getX(), e.getY(), currentTime - lastTime));
                lastTime = currentTime;
            }
        });

        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    Map<String, Object> map = new HashMap<>();
                    map.put("rulesetVersion", "1.0");
                    map.put("rulesetAuthor", "Susovan Sankar Gumtya");
                    map.put("rulesetUpdateDate", "May-19-2024");
                    map.put("rulesetDescription", "Ruleset for Java Application");
                    map.put("ruleset", actions);
                    String json = gson.toJson(map);

                    try (FileWriter writer = new FileWriter("output.json")) {
                        writer.write(json);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(actions);
                    System.exit(0);
                }
            }
        });

        frame.setFocusable(true);
    }
}