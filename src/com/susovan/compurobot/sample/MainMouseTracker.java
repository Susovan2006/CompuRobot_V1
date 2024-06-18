package com.susovan.compurobot.sample;



/*
 Step 1 : write a javax.swing program(MainMouseTracker.java) that will open the UI window in full screen, it should have the option to minimize, mazimize and close. the background of the UI should be white. set the background as translucent of 0.5f.  
 Step 2 : The Program should Store the x-axis and y-axis of the mouse click events in a ArrayList, It will continue to add the x-axis and y-axis, unit the user press escape key.
 Step 3 : Once the user clicks on the escape Key, the program should generate an JSON file like the below. but it will based on condition mentioned below.
 Step 3.1 : use google gson for the JSON Operation.
 {
	"rulesetVersion": "1.0",
    "rulesetAuthor": "Susovan Sankar Gumtya",
    "rulesetUpdateDate": "May-19-2024",
    "rulesetDescription": "Ruleset for Java Application",
    "ruleset": [
		{
			"actionId": 1,
			"action": "move",
			"xaxis": 100,
			"yaxis": 200,
			"delay": 5000,
			"wordToType": "Hello, world!"
		},
		{
			"actionId": 2,
			"action": "move",
			"xaxis": 100,
			"yaxis": 200,
			"delay": 5000,
			"wordToType": "Hello, world!"
		}
	]
}
 Step 4 : rulesetVersion , rulesetAuthor , rulesetDescription should remain as it it.
 Step 5 : rulesetUpdateDate will be the current System date.
 Step 6 : actionId should be incremental,  xaxis and Y axis should be populated from the ArrayList.
 Step 6.1 : When ever the mouse is clicked (Left Click / Right Click/ Double Click), along with Capturing the X and Y axis, a small popup should comeup asking for the Action, delay in sec and wordToType. 
 Step 6.2 : The Popup should have a cancel and save button. Clicking cancel will ignore the click event. It should not come in the JSON file.
 Step 6.3 : The Action input should be a dropDown with these options, "Left Click" , "Right Click" , "Double Click", "KeyBoard Entry".
 Step 6.4 : The Delay should show 2 sec by Default.
 Step 6.5 : If the User Left Click, The Program should capture the Left Click and the popup should open, It should have the "left click" option selected for Action, Delay = 2 sec, The wordToType text area and label should be hidden. JSON Value for wordToType should be null.
 Step 6.6 : If the User Right Click, The Program should capture the Right Click and the popup should open, It should have the "Right click" option selected for Action, Delay = 2 sec, The wordToType text area and label should be hidden. JSON Value for wordToType should be null.
 Step 6.7 : If the User Double Click, The Program should capture the Double  Click and the popup should open, It should have the "Double click" option selected for Action, Delay = 2 sec, The wordToType text area and label should be hidden. JSON Value for wordToType should be null.
 Step 6.8 : If the user select "KeyBoard Entry" , the popup should display/visible the text area and label to take the input for  wordToType in the JSON. Of other actions, wordToType should be null in the JSON.
 Step 6.9 : Is the user changes the action DropDown, the wordToType text box and label should be visible only for "KeyBoard Entry" selection, for other selection it should be hidden.
 Step 7 : The Order of the Json Element should be maintained as actionId , action, xaxis, yaxis, wordToType.
 
Step n : Once the user click on the escape key, the program should write the json file with a name like RULESET_<CurrentTimestamp>.json and then terminate. 
 
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainMouseTracker {
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
                point.put("wordToType", null);
                
                JLabel customLabel = new JLabel("Custome Value");

                // Popup to get action and delay
                String[] actions = {"Left Click", "Right Click", "Double Click", "KeyBoard Entry"};
                JComboBox actionField = new JComboBox(actions);
                JTextField delayField = new JTextField("2");
                JTextArea wordToTypeField = new JTextArea();
                wordToTypeField.setVisible(true);
                Object[] message = {
                    "Action:", actionField,
                    "Delay:", delayField,
                    customLabel, wordToTypeField
                };
                
                
             // Add item listener to JComboBox
                actionField.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            if (e.getItem().equals("KeyBoard Entry")) {
                                wordToTypeField.setVisible(true);
                            } else {
                                wordToTypeField.setVisible(false);
                            }
                        }
                    }
                });
                
                
                // Determine the type of mouse click
                String selectedAction = "";
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (e.getClickCount() == 2) {
                    	selectedAction = "Double Click";
                    	wordToTypeField.setVisible(false);
                    } else {
                    	selectedAction = "Left Click";
                    	wordToTypeField.setVisible(false);
                    }
                } else if (SwingUtilities.isRightMouseButton(e)) {
                	selectedAction = "Right Click";
                	wordToTypeField.setVisible(false);
                }else {
                	wordToTypeField.setVisible(true);
                }
                actionField.setSelectedItem(selectedAction);
                
                int option = JOptionPane.showConfirmDialog(null, message, "Enter Action and Delay", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    point.put("action", actionField.getSelectedItem().toString());
                    point.put("delay", delayField.getText());
                    if (actionField.getSelectedItem().toString().equals("KeyBoard Entry")) {
                        point.put("wordToType", wordToTypeField.getText());
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
        
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F1) {
                    frame.setExtendedState(JFrame.ICONIFIED); // Minimize
                } else if (e.getKeyCode() == KeyEvent.VK_F2) {
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize
                }
            }
        });
    }

    private static void generateJsonFile(List<Map<String, Object>> points) {
        Map<String, Object> obj = new LinkedHashMap<>();
        obj.put("rulesetVersion", "1.0");
        obj.put("rulesetAuthor", "Susovan Sankar Gumtya");
        obj.put("rulesetUpdateDate", new SimpleDateFormat("MMM-dd-yyyy").format(new Date()));
        obj.put("rulesetDescription", "Ruleset for Java Application");
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