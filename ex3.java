/*
 * File:    Broken	.java
 * Created: 7 September 2001
 * Author:  Stephen Jarvis
 */

// Algorithm works by checking which horizontal & vertical direction will take the sprite closer to the finish
// It then checks whether either of the optimal directions are available and chooses randomly between them (if both available)
// If not, chooses randomly out of remaining (valid) directions 

// Algorithm is incredibly flawed as sprite can easily get stuck in loop

import uk.ac.warwick.dcs.maze.logic.IRobot;

public class Ex3 
{
     // determines whether target is North or South of the sprite
     private int isRobotNorth(IRobot robot) {
          int curY = robot.getLocation().y; 
          int finY = robot.getTargetLocation().y; 

          if (curY-finY > 0) {
               return 1;
          }

          else if (curY-finY < 0) {
               return -1;
          }

          else {
               return 0;
          }
     }  

     // determines whether target East or West of sprite
     private int isRobotEast(IRobot robot) {
          int curX = robot.getLocation().x; 
          int finX = robot.getTargetLocation().x; 

          if (curX-finX < 0) {
               return 1;
          }

          else if (curX-finX > 0) {
               return -1;
          }

          else {
               return 0;
          }
     } 

     // returns what faces given heading 
     private int lookHeading(IRobot robot, int heading) {
          robot.setHeading(heading);
          int result = robot.look(IRobot.AHEAD);

          return result;
     } 

     private int headingController(IRobot robot) {
          int[] poss_Directions = new int[2];     // array of possible directions (without crashing)
          int[] tempArr = new int[2];   // array holding "the other 2 directions" temporarily

          int randno;    // random number for random direction later

          // finding optimal headings
          int valNorth = isRobotNorth(robot);
          int valEast = isRobotEast(robot); 

          // filling poss_Directions array & tempArr with the adequate headings
          if (valNorth == 1) {
               poss_Directions[0] = IRobot.NORTH;
               tempArr[0] = IRobot.SOUTH;
          }
          else {
               poss_Directions[0] = IRobot.SOUTH;
               tempArr[0] = IRobot.NORTH;
          }

          if (valEast == 1) {
               poss_Directions[1] = IRobot.EAST;
               tempArr[1] = IRobot.WEST;
          }
          else {
               poss_Directions[1] = IRobot.WEST;
               tempArr[1] = IRobot.EAST;
          } 

          boolean good_direction = false;

          // checks whether headings in poss_Directions work (if not, they get subbed with alt. direction)
          for (int i = 0; i <= 1; i++) {
               if (good_direction == false && lookHeading(robot, poss_Directions[i]) == IRobot.WALL) {
                    poss_Directions[i] = tempArr[i];

                    if (lookHeading(robot, poss_Directions[i]) == IRobot.WALL) {
                         poss_Directions[i] = 0;
                    }
               }

               else if (good_direction == true && lookHeading(robot, poss_Directions[i]) == IRobot.WALL) {
                    poss_Directions[i] = 0;

               }

               else {
                    good_direction = true;   // stops sub-optimal direction from being a possibility
               }
          }

          // chooses direction (only proceeds when value of direction NOT zero, so please don't make any of the directions have value 0)
          int tempX = 0; 
          while (tempX == 0) {
               randno = (int) Math.floor(Math.random()*2);
               tempX = poss_Directions[randno];
          } 



          // final heading e.g. .North
          return tempX;

     }

     public void reset() {
          ControlTest.printResults();
     }


     // main code
     public void controlRobot(IRobot robot) {
          int finalDirection = headingController(robot);
          ControlTest.test(finalDirection, robot);
          robot.setHeading(finalDirection);
     }

}
