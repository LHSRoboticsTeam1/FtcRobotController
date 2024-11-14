package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "FrontPlaceBlue")
public class FrontPlaceBlue extends LinearOpMode {
    @Override
    public void runOpMode() {
///
        HardwareForRobot robot = new HardwareForRobot(this);
        robot.init();
        waitForStart();
        if (opModeIsActive()) {
          /*  robot.closeClaw();
            robot.autoDriveRobot(-34, -34);
            sleep(2000);
            int propNumber = robot.PropNumber();
            if (propNumber == robot.CENTER) {
                robot.autoDriveRobot(-5, -5);
                robot.autoDriveRobot(34, 34);
                robot.turnToHeading(90, .4);
                robot.resetEncoders();
                robot.autoDriveRobot(-100, -100);
                robot.openClaw();
                sleep(1000);
                robot.autoDriveRobot(5, 5);


            }
            else if (propNumber == robot.RIGHT) {

                robot.turnToHeading(-90, .4);
                robot.resetEncoders();
                robot.autoDriveRobot(-10, -10); //puts pixel on stripe
                robot.autoDriveRobot(11, 11);
                robot.turnToHeading(-180, .4);
                robot.resetEncoders();
                robot.autoDriveRobot(-30, -30);
                robot.turnToHeading(90, .4);
                robot.resetEncoders();
                robot.autoDriveRobot(-100, -100);
                robot.openClaw();
                sleep(1000);
                robot.autoDriveRobot(5, 5);
            }
            else if (propNumber == robot.LEFT) {

                robot.turnToHeading(90, .4);
                robot.resetEncoders();
                robot.autoDriveRobot(-10, -10); //puts pixel on stripe
                robot.autoDriveRobot(20, 20);
                robot.turnToHeading(180, .4);
                robot.resetEncoders();
                robot.autoDriveRobot(-28, -28);
                robot.turnToHeading(90, .4);
                robot.resetEncoders();
                robot.autoDriveRobot(-100, -100);
                robot.openClaw();
                sleep(1000);
                robot.autoDriveRobot(5, 5);

            }
*/
        }



        robot.shutDown();
    }


}
