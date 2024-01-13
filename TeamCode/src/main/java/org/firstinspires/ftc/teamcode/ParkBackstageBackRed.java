package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "ParkBackstageRed")
public class ParkBackstageBackRed extends LinearOpMode {

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
                robot.autoDriveRobot(-6,  -6 );//park
                robot.autoDriveRobot(30, 30);//backs up from pixel
                robot.autoDriveRobot(-30,  30 );// turn
                robot.autoDriveRobot(-100,  -100 );//park


            }
             else if (propNumber == robot.LEFT) {
                robot.autoDriveRobot(30,  -30);// turn Right?
                robot.autoDriveRobot(-10,  -10); //puts pixel on stripe
                robot.autoDriveRobot(50,  50);//backs up from pixel
            }
            else if (propNumber == robot.RIGHT) {

                    robot.autoDriveRobot(-30, 30);// turn left
                    sleep(2000);
                    robot.autoDriveRobot(-10, -10); //puts pixel on stripe
                }
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



