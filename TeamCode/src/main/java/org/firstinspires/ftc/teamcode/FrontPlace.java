package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "FrontPlace")
public class FrontPlace extends LinearOpMode {
    @Override
    public void runOpMode() {

        HardwareForRObot robot = new HardwareForRObot(this);
        robot.init();
        waitForStart();
        if (opModeIsActive()) {
            robot.autoDriveRobot(-35, -35);
            sleep(2000);
            int propNumber = robot.PropNumber();
            if (propNumber == robot.CENTER) {
                robot.autoDriveRobot(-6, -6);//backs up from pixel
            }
            else if (propNumber == robot.RIGHT) {

                robot.autoDriveRobot(-30, 30);// turn right
                sleep(2000);
                robot.autoDriveRobot(-15, -15); //puts pixel on stripe
            }
            else if (propNumber == robot.LEFT) {

                robot.autoDriveRobot(30, -30);// turn left?
                sleep(2000);
                robot.autoDriveRobot(-10, -10); //puts pixel on stripe
            }
        }



        robot.shutDown();
    }


}
