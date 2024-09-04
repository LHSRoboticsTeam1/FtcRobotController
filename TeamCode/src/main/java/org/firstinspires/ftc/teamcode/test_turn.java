package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Test_turn")
public class test_turn extends LinearOpMode {

    HardwareForRobot robot;
    @Override
    public void runOpMode() {

        robot = new HardwareForRobot(this);
        robot.init();
        //robot.holdDrone();
        waitForStart();
        if (opModeIsActive()) {

            telemetry.addData("heading",robot.getHeadingDegrees());
            telemetry.update();

            robot.autoDriveRobot(-37, -37);
            robot.turnToHeading(90,.3);
           // telemetry.update();
            // while (opModeIsActive()) {
            //  double leftDistance = robot.findLocationLeft();
            // double rightDistance = robot.findLocationRight();
            // telemetry.setAutoClear(true);
            //  telemetry.addData("distance left", "%.0f", leftDistance);
            // telemetry.addData("distance right", "%.0f", rightDistance);
            //  PropNumber();
            // telemetry.update();
            // }




        }
        sleep(8000);
        robot.shutDown();
    }

    }

