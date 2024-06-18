package com.susovan.compurobot.main;

import java.util.List;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;


public class ProcessInstruction {
	
  public static void processJsonInstruction(List<Rule> ruleList) throws Exception {
	  Robot robot = new Robot();
	  minimizeAllRunningApps(robot);
	  for(Rule rule : ruleList) {
		  performAction(robot, rule.getAction(), 
				  rule.getXaxis(), 
				  rule.getYaxis(), 
				  rule.getDelay(), 
				  rule.getWordToType(),
				  rule.getScrollNotchCount(),
				  rule.getShortcuts());
	  }
  }
  
  
  
  private static void performAction(Robot robot, String action, 
		  						int x, int y, int delay, 
		  						String value, int notchCount,
		  						String shortcuts) throws Exception {
Map<String, Integer> initialPosition;
	  
	  switch (action) {
          case "Mouse Move":
              robot.mouseMove(x, y);
              addDelayInMs(robot,delay);
              break;
          case Constants.MOUSE_LEFT_CLICK:
        	  initialPosition = getMousePosition();
        	  moveCursorWithDelay(robot,
        			  	initialPosition.get("X"),
        			  	initialPosition.get("Y"),
        			  	x, y);
              robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
              robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
              addDelayInMs(robot,delay);
              break;
          case Constants.MOUSE_RIGHT_CLICK:
        	  initialPosition = getMousePosition();
        	  moveCursorWithDelay(robot,
        			  	initialPosition.get("X"),
        			  	initialPosition.get("Y"),
        			  	x, y);
              
              robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
              robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
              addDelayInMs(robot,delay);
              break;
          case Constants.MOUSE_DOUBLE_CLICK:
        	  initialPosition = getMousePosition();
        	  moveCursorWithDelay(robot,
        			  	initialPosition.get("X"),
        			  	initialPosition.get("Y"),
        			  	x, y);
              
              
              robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
              robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
              robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
              robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
              addDelayInMs(robot,delay);
              break;
          case Constants.KEYBOARD_ENTRY:
        	  
        	  initialPosition = getMousePosition();
        	  moveCursorWithDelay(robot,
        			  	initialPosition.get("X"),
        			  	initialPosition.get("Y"),
        			  	x, y);
        	  
        	  
        	  performTypeAction(robot,x,y,value);
        	  addDelayInMs(robot,delay);
              break;
          case Constants.HIT_ENTER:
        	  
        	  initialPosition = getMousePosition();
        	  moveCursorWithDelay(robot,
        			  	initialPosition.get("X"),
        			  	initialPosition.get("Y"),
        			  	x, y);
        	  robot.keyPress(KeyEvent.VK_ENTER);
              robot.keyRelease(KeyEvent.VK_ENTER); 
              addDelayInMs(robot,delay);
              break;
          case Constants.MOUSE_SCROLL_DOWN:
        	  
        	  initialPosition = getMousePosition();
        	  moveCursorWithDelay(robot,
        			  	initialPosition.get("X"),
        			  	initialPosition.get("Y"),
        			  	x, y);
        	  mouseScrollDown(robot, notchCount); 
              addDelayInMs(robot,delay);
              break;
          case Constants.MOUSE_SCROLL_UP:
        	  
        	  initialPosition = getMousePosition();
        	  moveCursorWithDelay(robot,
        			  	initialPosition.get("X"),
        			  	initialPosition.get("Y"),
        			  	x, y);
        	  mouseScrollUp(robot, notchCount);
              addDelayInMs(robot,delay);
              break;
          case Constants.CUSTOM_KEY_PRESS:
        	  
        	  initialPosition = getMousePosition();
        	  moveCursorWithDelay(robot,
        			  	initialPosition.get("X"),
        			  	initialPosition.get("Y"),
        			  	x, y);
        	  KeyboardShortcutUtil.performShortcut(robot, shortcuts);
              addDelayInMs(robot,delay);
              break;
          default:
              System.out.println("Invalid action");
      }
  }
  
  private static Map<String, Integer> getMousePosition() {
      Point point = MouseInfo.getPointerInfo().getLocation();
      Map<String, Integer> position = new HashMap<>();
      position.put("X", point.x);
      position.put("Y", point.y);
      return position;
  }
  
  private static void moveCursorWithDelay(Robot robot, int sourceX, int sourceY, int targetX, int targetY) {
      int currentX = sourceX;
      int currentY = sourceY;

      while (currentX != targetX || currentY != targetY) {
          if (currentX < targetX) {
              currentX = Math.min(currentX + Constants.MIN_PIXEL_MOVE, targetX);
          } else if (currentX > targetX) {
              currentX = Math.max(currentX - Constants.MIN_PIXEL_MOVE, targetX);
          }

          if (currentY < targetY) {
              currentY = Math.min(currentY + Constants.MIN_PIXEL_MOVE, targetY);
          } else if (currentY > targetY) {
              currentY = Math.max(currentY - Constants.MIN_PIXEL_MOVE, targetY);
          }

          robot.mouseMove(currentX, currentY);
          robot.setAutoDelay(Constants.VISIVILITY_DELAY_IN_MS); // Delay for visibility
      }
  }
  
  
  private static void performTypeAction(Robot robot, int x, int y, String text) {
      // Move the cursor to the specified coordinates
      robot.mouseMove(x, y);

      // Perform a click
      robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
      robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

      // Type the string
      for (char c : text.toCharArray()) {
          int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
          if (KeyEvent.CHAR_UNDEFINED == keyCode) {
              throw new RuntimeException(
                  "Key code not found for character '" + c + "'");
          }
          robot.keyPress(keyCode);
          robot.keyRelease(keyCode);
      }
  }
  
  private static void mouseScrollUp(Robot robot, int notch) throws Exception {
      robot.mouseWheel(-notch);
  }
  
  private static void mouseScrollDown(Robot robot, int notch) throws Exception {
      robot.mouseWheel(notch);
  }
  
  private static void addDelayInMs(Robot robot, int delay) {
	  robot.setAutoDelay(delay);
  }
  
  private static void minimizeAllRunningApps(Robot robot) {
      robot.keyPress(KeyEvent.VK_WINDOWS);
      robot.keyPress(KeyEvent.VK_D);

      robot.keyRelease(KeyEvent.VK_D);
      robot.keyRelease(KeyEvent.VK_WINDOWS);
  }

}
