package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "ParkBackstageBlue")
public class ParkBackstageBackBlue extends LinearOpMode {

    HardwareForRObot robot;
    @Override
    public void runOpMode() {

        robot = new HardwareForRObot(this);
        robot.init();
        //robot.holdDrone();
        waitForStart();
        if (opModeIsActive()) {
            robot.autoDriveRobot(-35, -35);
            sleep(2000);
            int propNumber = robot.PropNumber();
            if (propNumber == robot.CENTER) {
                robot.autoDriveRobot(-5,  -5 );//puts pixel on spike
                robot.autoDriveRobot(12, 12);//backs up from pixel
                robot.autoDriveRobot(30,  -30 );// turn
                robot.autoDriveRobot(-100,  -100 );//park
            }
             else if (propNumber == robot.RIGHT) {
                robot.autoDriveRobot(-30,  30);// turn right
                robot.resetEncoders();
                robot.autoDriveRobot(-9,  -9); //puts pixel on stripe
                robot.autoDriveRobot(50,  50);//backs up from pixel
            }
            else if (propNumber == robot.LEFT) {
                robot.autoDriveRobot(3, 3);
                robot.autoDriveRobot(30, -30);// turn left
                robot.resetEncoders();
                robot.autoDriveRobot(-8, -8); //puts pixel on stripe
                robot.autoDriveRobot(9, 9); // backs away from pixel
                robot.autoDriveRobot(-30, 30); // turns right
                robot.resetEncoders();
                robot.autoDriveRobot(-30, -30); // moves away from spike area
                robot.autoDriveRobot(30, -30); // turn to backstage
                robot.resetEncoders();
                robot.autoDriveRobot(-45, -45); // drive towards backstage
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

    }

