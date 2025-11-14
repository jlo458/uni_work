
import uk.ac.warwick.dcs.maze.logic.IRobot;

public class Explorer {
    private static int[] directions = {IRobot.NORTH, IRobot.SOUTH, IRobot.EAST, IRobot.WEST};

    private int lookHeading(IRobot robot, int heading) {
          robot.setHeading(heading);
          int result = robot.look(IRobot.AHEAD);

          return result;
     } 

    private void deadEnd(IRobot robot) {
        robot.face(IRobot.BEHIND);
        while (robot.look(IRobot.AHEAD) == IRobot.WALL) {
            robot.face(IRobot.RIGHT);
        }
    }

    private void corridor(IRobot robot) {
        // should check both corners & corridor before going back
        robot.face(IRobot.LEFT);
        while (robot.look(IRobot.AHEAD) == IRobot.WALL) {
            robot.face(IRobot.RIGHT);
        }
    }

    private void junction_crossroad(IRobot robot) {
        int noNonChecked = notCheckedExits(robot);
        int randno = (int) Math.floor(Math.random()*4);
        robot.setHeading(directions[randno]); // randomises starting direction

        if (noNonChecked >= 1) {
            while (robot.look(IRobot.AHEAD) != IRobot.PASSAGE) {
                robot.face(IRobot.RIGHT);
            }
        }

        else {
            while (robot.look(IRobot.AHEAD) == IRobot.WALL) {
                robot.face(IRobot.RIGHT);
            }
        }

    }

    private int notCheckedExits(IRobot robot) {
        int notChecked = 0;
        //int[] directions = {IRobot.NORTH, IRobot.SOUTH, IRobot.EAST, IRobot.WEST};
        for (int i : directions) {
            if (i == IRobot.PASSAGE) notChecked += 1;
        }

        return notChecked;

    }

    private int nonwallExits(IRobot robot) {
        int nonWalls = 0;
        //int[] directions = {IRobot.NORTH, IRobot.SOUTH, IRobot.EAST, IRobot.WEST};
        for (int i : directions) {
            if (i != IRobot.WALL) nonWalls += 1;
        }

        return nonWalls;

    }

    public void controlRobot(IRobot robot) {
        exits = nonwallExits(robot);
        switch (exits) {
            case 1:
                deadEnd(robot);
                break;

            case 2:
                corridor(robot);
                break; 

            case 3: 
                junction_crossroad(robot);
                break;

            case 4: 
                junction_crossroad(robot);
                break;
        
            default:
                System.out.println("Broken");
                break;
        }
    }

    
}
