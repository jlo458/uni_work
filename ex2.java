/*
* File: DumboController.java
* Created: 17 September 2002, 00:34
* Author: Stephen Jarvis
*/

// used .floor instead of .round for equal probabilities
// slower than ex1 on average though

import uk.ac.warwick.dcs.maze.logic.IRobot;

public class Ex2
{
	public void controlRobot(IRobot robot) {

		int randno;
		int direction;

		// randomly choosing direction on avg 1 in 8 times 
		randno = (int) Math.floor(Math.random()*7);
		if (randno == 0) {
			randno = (int) Math.floor(Math.random()*4);

			if (randno == 0) {
				robot.face(IRobot.LEFT);
			}

			else if (randno == 1) {
				robot.face(IRobot.BEHIND);
			}

			else if (randno == 2) {
				robot.face(IRobot.AHEAD);
			}

			else {
				robot.face(IRobot.RIGHT);
			}

		}

		boolean left_ = false;
		boolean ahead_ = false;
		boolean right_ = false;
		boolean behind_ = false;

		// will go forward if can

		if (robot.look(IRobot.AHEAD) != IRobot.WALL) {
			direction = IRobot.AHEAD;
			System.out.println("Gone ahead");
		}

		else {

			// code from ex1
		
			if (robot.look(IRobot.LEFT) != IRobot.WALL) {
				left_ = true;
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
				randno = (int) Math.floor(Math.random()*3);
				if (randno == 0 && left_ == true) {
					direction = IRobot.LEFT;
					System.out.println("Gone left");
					done = true;
				}

				else if (randno == 1 && behind_ == true) {
					direction = IRobot.BEHIND;
					System.out.println("Gone behind");
					done = true;
				}

				else if (randno == 2 && right_ == true) {
					direction = IRobot.RIGHT;
					System.out.println("Gone right");
					done = true;
				}

			}
		}

		robot.face(direction); 
	}

}
