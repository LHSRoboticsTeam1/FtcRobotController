package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "FrontPlaceRed")
public class FrontPlaceRed extends LinearOpMode {
    @Override
    public void runOpMode() {
///
        HardwareForRobot robot = new HardwareForRobot(this);
        robot.init();
        waitForStart();
        if (opModeIsActive()) {
            robot.closeClaw();
            robot.autoDriveRobot(-35, -35);
            sleep(2000);
            int propNumber = robot.PropNumber();
            if (propNumber == robot.CENTER) {
                robot.autoDriveRobot(-5, -5);
                /**
                robot.autoDriveRobot(36, 36);
                robot.autoDriveRobot(-30, 30);
                robot.resetEncoders();
                robot.autoDriveRobot(-100, -100);
                robot.openClaw();
                sleep(1000);
                robot.autoDriveRobot(5, 5);
                 */
            }
            else if (propNumber == robot.RIGHT) {

                robot.autoDriveRobot(-30, 30);// turn right
                robot.resetEncoders();
                robot.autoDriveRobot(-10, -10); //puts pixel on stripe
                /**
                robot.autoDriveRobot(20, 20);
                robot.autoDriveRobot(-30, 30);
                robot.resetEncoders();
                robot.autoDriveRobot(-30, -30);
                robot.autoDriveRobot(30, -30);
                robot.resetEncoders();
                robot.autoDriveRobot(-110, -110);
                robot.openClaw();
                sleep(1000);
                robot.autoDriveRobot(5, 5);
                 */

            }
            else if (propNumber == robot.LEFT) {

                robot.autoDriveRobot(1, 1);
                robot.resetEncoders();
                robot.autoDriveRobot(30, -30);// turn left?
                robot.resetEncoders();
                robot.autoDriveRobot(-10, -10); //puts pixel on stripe
                /**
                robot.autoDriveRobot(11, 11);
                robot.autoDriveRobot(30, -30, .2); // slow turn to ensure it doesn't run over the pixel
                robot.resetEncoders();
                robot.autoDriveRobot(-30, -30);
                robot.autoDriveRobot(30, -30);
                robot.resetEncoders();
                robot.autoDriveRobot(-100, -100);
                robot.openClaw();
                sleep(1000);
                robot.autoDriveRobot(5, 5);
                 */
            }

        }


        robot.shutDown();
    }


}
