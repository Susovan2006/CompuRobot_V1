package com.susovan.compurobot.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class GenerateRuleSetMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.5f)); // Translucent white background
        
        // List to store x and y coordinates of mouse clicks
        List<Map<String, Object>> points = new ArrayList<>();

     
        
        // Add mouse listener to store x and y coordinates of mouse clicks
        frame.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Map<String, Object> point = new LinkedHashMap<>();
                point.put("actionId", points.size() + 1);
                point.put("xaxis", e.getX());
                point.put("yaxis", e.getY());


                // Popup to get action and delay
                String[] actions = {Constants.MOUSE_LEFT_CLICK, 
                		Constants.HIT_ENTER,
                		Constants.MOUSE_RIGHT_CLICK, 
                		Constants.MOUSE_DOUBLE_CLICK, 
                		Constants.KEYBOARD_ENTRY,
                		Constants.MOUSE_SCROLL_UP,
                		Constants.MOUSE_SCROLL_DOWN,
                		Constants.CUSTOM_KEY_PRESS};
                
                JComboBox actionField = new JComboBox(actions);
                JTextField delayField = new JTextField(Constants.DEFAULT_DELAY_IN_SCRIPT_GEN_MS+"");
                JTextField customTxtField = new JTextField();
                //JLabel customLabel = new JLabel("Custom Input");
                JLabel customLabel = new JLabel("Custome Value");
                
                Object[] message = {
                        "Action:", actionField,
                        "Delay:", delayField,
                        customLabel, customTxtField
                    };
             // Add item listener to JComboBox
                
                actionField.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            if (e.getItem().equals(Constants.KEYBOARD_ENTRY)) {
                            	customTxtField.setEnabled(true);
                            	customLabel.setEnabled(true);
                            	customLabel.setText("Word to Type");
                            	//System.out.println("Word to Type");
                            } else if(e.getItem().equals(Constants.HIT_ENTER)){
                            	customTxtField.setEnabled(false);
                            	customLabel.setEnabled(false);
                            	customLabel.setText("none");
                            }else if(e.getItem().equals(Constants.MOUSE_SCROLL_UP)){
                            	customTxtField.setEnabled(true);
                            	customLabel.setEnabled(true);
                            	customLabel.setText("Scroll Notch Count");
                            	//System.out.println("Scroll Notch Count");
                            }else if(e.getItem().equals(Constants.MOUSE_SCROLL_DOWN)){
                            	customTxtField.setEnabled(true);
                            	customLabel.setEnabled(true);
                            	customLabel.setText("Scroll Notch Count");
                            	//System.out.println("Scroll Notch Count");
                            }else if(e.getItem().equals(Constants.CUSTOM_KEY_PRESS)){
                            	customTxtField.setEnabled(true);
                            	customLabel.setEnabled(true);
                            	customLabel.setText("Custom Key / Shotcut");
                            	//System.out.println("Custom Key / Shotcut");
                            }
                        }
                    }
                });
                
                /*Object[] message = {
                        "Action:", actionField,
                        "Delay:", delayField,
                        customLabel.getText() +":",customTxtField
                    };*/
                
                
                // Determine the type of mouse click
                String selectedAction = "";
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (e.getClickCount() == 2) {
                    	selectedAction = Constants.MOUSE_DOUBLE_CLICK;
                    	customTxtField.setEnabled(false);
                    	customLabel.setEnabled(false);
                    } else {
                    	selectedAction = Constants.MOUSE_LEFT_CLICK;
                    	customTxtField.setEnabled(false);
                    	customLabel.setEnabled(false);
                    }
                } else if (SwingUtilities.isRightMouseButton(e)) {
                	selectedAction = Constants.MOUSE_RIGHT_CLICK;
                	customTxtField.setEnabled(false);
                	customLabel.setEnabled(false);
                }else {
                	customTxtField.setEnabled(true);
                	customLabel.setEnabled(true);
                }
                actionField.setSelectedItem(selectedAction);
                
                int option = JOptionPane.showConfirmDialog(null, message, "Enter Action and Delay", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    point.put("action", actionField.getSelectedItem().toString());
                    point.put("delay", delayField.getText());

                    if (actionField.getSelectedItem().toString().equals(Constants.KEYBOARD_ENTRY)) {
                    	point.put("wordToType", customTxtField.getText());
                    }
                    else if (actionField.getSelectedItem().toString().equals(Constants.MOUSE_SCROLL_DOWN) ||
                    		actionField.getSelectedItem().toString().equals(Constants.MOUSE_SCROLL_UP)) {
                    	point.put("scrollNotchCount", customTxtField.getText());
                    }
                    else if (actionField.getSelectedItem().toString().equals(Constants.CUSTOM_KEY_PRESS)) {
                    	point.put("shortcuts", customTxtField.getText());
                    }
                    points.add(point);
                } else {
                    //point.put("action", "");
                    //point.put("delay", "");
                }

                
            }
        });

        
        
        // Add key listener to generate JSON file and terminate program when escape key is pressed
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    generateJsonFile(points);
                    System.exit(0);
                }
            }
        });
        
        

        frame.setFocusable(true); // To receive key events
        frame.setVisible(true);
    }

    private static void generateJsonFile(List<Map<String, Object>> points) {
    	System.out.println("Generate JSON File........");
        Map<String, Object> obj = new LinkedHashMap<>();
        obj.put("rulesetVersion", "1.0");
        obj.put("rulesetAuthor", "@Author");
        obj.put("rulesetUpdateDate", new SimpleDateFormat("MMM-dd-yyyy").format(new Date()));
        obj.put("rulesetDescription", "Ruleset for _______");
        obj.put("ruleset", points);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(obj);

        try (FileWriter file = new FileWriter("RULESET_" + System.currentTimeMillis() + ".json")) {
            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
