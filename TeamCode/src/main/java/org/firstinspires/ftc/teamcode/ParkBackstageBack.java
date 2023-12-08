package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "ParkBackstage")
public class ParkBackstageBack extends LinearOpMode {

    HardwareForRObot robot;
    @Override
    public void runOpMode() {

         robot = new HardwareForRObot(this);
        robot.init();
        waitForStart();
       // robot.autoDriveRobot(-25,  -25 );
        while (opModeIsActive()) {
            double leftDistance = robot.findLocationLeft();
            double rightDistance = robot.findLocationRight();
            telemetry.setAutoClear(true);
            telemetry.addData("distance left", "%.0f", leftDistance);
            telemetry.addData("distance right", "%.0f", rightDistance);
            PropNumber();
            telemetry.update();
        }
        robot.shutDown();
    }

    public int PropNumber() {
        double leftDistance = robot.findLocationLeft();
        double rightDistance = robot.findLocationRight();
        int propNumber;
        telemetry.setAutoClear(true);

        if (leftDistance <= 10) {
            telemetry.addData("prop", "1");
            propNumber = 1;
            return propNumber;
        }
        else if (rightDistance <= 10) {
            telemetry.addData("prop", "2");
            propNumber = 2;
            return propNumber;
        }
        else {
            telemetry.addData("prop", "3");
            propNumber = 3;
            return propNumber;
        }
    }
}
