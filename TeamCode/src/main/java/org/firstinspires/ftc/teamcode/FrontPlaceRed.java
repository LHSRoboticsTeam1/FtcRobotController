package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "FrontPlaceRed")
public class FrontPlaceRed extends LinearOpMode {
    @Override
    public void runOpMode() {
///
        HardwareForRObot robot = new HardwareForRObot(this);
        robot.init();
        waitForStart();
        if (opModeIsActive()) {
            robot.autoDriveRobot(-35, -35);
            sleep(2000);
            int propNumber = robot.PropNumber();
            if (propNumber == robot.CENTER) {
                robot.autoDriveRobot(-5, -5);//backs up from pixel
                robot.autoDriveRobot(36, 36);
                robot.autoDriveRobot(-30, 30);
                robot.resetEncoders();
                robot.autoDriveRobot(-105, -105);
            }
            else if (propNumber == robot.RIGHT) {

                robot.autoDriveRobot(-30, 30);// turn right
                robot.resetEncoders();
                robot.autoDriveRobot(-10, -10); //puts pixel on stripe
                robot.autoDriveRobot(20, 20);
                robot.autoDriveRobot(-30, 30);
                robot.resetEncoders();
                robot.autoDriveRobot(-30, -30);
                robot.autoDriveRobot(28, -28);
                robot.resetEncoders();
                robot.autoDriveRobot(-115, -115);
            }
            else if (propNumber == robot.LEFT) {

                robot.autoDriveRobot(1, 1);
                robot.resetEncoders();
                robot.autoDriveRobot(29, -29m );// turn right?
                robot.resetEncoders();
                robot.autoDriveRobot(-10, -10); //puts pixel on stripe
                robot.autoDriveRobot(85, 85);
                robot.autoDriveRobot(30, -30); // slow turn to ensure it doesn't run over the pixel
                robot.resetEncoders();
                robot.autoDriveRobot(-27, -27);
                robot.autoDriveRobot(30, -30);
                robot.resetEncoders();
                robot.autoDriveRobot(-20, -20);
            }
        }



        robot.shutDown();
    }


}
