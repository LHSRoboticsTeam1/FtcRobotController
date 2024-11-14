package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "ParkBackstageRed")
public class ParkBackstageBackRed extends LinearOpMode {

    HardwareForRobot robot;
    @Override
    public void runOpMode() {

        robot = new HardwareForRobot(this);
        robot.init();
        //robot.holdDrone();
        waitForStart();
        if (opModeIsActive()) {


            robot.autoDriveRobot(-37, -37);
            sleep(1000);

            }
            // while (opModeIsActive()) {
            //  double leftDistance = robot.findLocationLeft();
            // double rightDistance = robot.findLocationRight();
            // telemetry.setAutoClear(true);
            //  telemetry.addData("distance left", "%.0f", leftDistance);
            // telemetry.addData("distance right", "%.0f", rightDistance);
            //  PropNumber();
            // telemetry.update();
            // }


        robot.shutDown();
        }
    }



