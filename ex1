/*
* File: DumboController.java
* Created: 17 September 2002, 00:34
* Author: Stephen Jarvis
*/

// Basically just "looked" at every direction to not crash
// From testing, it became apparent that the probability of going LEFT and BEHIND was less likely than the other 2 due to .round
// probability: for LEFT and BEHIND => 0.5/3 = 1/6, for AHEAD and RIGHT => 1/3

import uk.ac.warwick.dcs.maze.logic.IRobot;

public class Ex1
{

	public void controlRobot(IRobot robot) {

		int randno;
		int direction;

		// Checks all 4 sides, and randomly selects "non-wall" side

		boolean left_ = false;
		boolean ahead_ = false;
		boolean right_ = false;
		boolean behind_ = false;

		if (robot.look(IRobot.LEFT) != IRobot.WALL) {
			left_ = true;
		}

		if (robot.look(IRobot.AHEAD) != IRobot.WALL) {
			ahead_ = true;
		}

		if (robot.look(IRobot.RIGHT) != IRobot.WALL) {
			right_ = true;
		}

		if (robot.look(IRobot.BEHIND) != IRobot.WALL) {
			behind_ = true;
		}
		
		boolean done = false;
		direction = IRobot.RIGHT;


		while (done == false) {
			randno = (int) Math.round(Math.random()*3);
			if (randno == 0 && left_ == true) {
				direction = IRobot.LEFT;
				System.out.println("Gone left");
				done = true;
			}

			else if (randno == 1 && ahead_ == true) {
				direction = IRobot.AHEAD;
				System.out.println("Gone ahead");
				done = true;
			}

			else if (randno == 2 && right_ == true) {
				direction = IRobot.RIGHT;
				System.out.println("Gone right");
				done = true;
			}

			else if (randno == 3 && behind_ == true) {
				direction = IRobot.BEHIND;
				System.out.println("Gone behind");
				done = true;
			}

		}

		robot.face(direction); /* Face the robot in this direction */ 
	}

}
