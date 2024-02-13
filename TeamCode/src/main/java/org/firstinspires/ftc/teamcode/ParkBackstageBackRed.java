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

            robot.closeClaw();
            robot.autoDriveRobot(-37, -37);
            sleep(1000);
            int propNumber = robot.PropNumber();


            if (propNumber == robot.CENTER) {

                robot.autoDriveRobot(-3,  -3 );//puts pixel on spike
                robot.autoDriveRobot(12, 12);//backs up from pixel
                robot.autoDriveRobot(-30,  30 );// turn
                robot.raiseArm(-95); //robot.raiseArm(-.4); //rotates arm up into scoring position
                robot.resetEncoders();
                robot.autoDriveRobot(-47,  -47);//park
                robot.openClaw();
                sleep(1000);
                robot.autoDriveRobot(5, 5);
            }
             else if (propNumber == robot.LEFT) {

                robot.autoDriveRobot(3, 3);
                robot.autoDriveRobot(30,  -30);// turn left?
                robot.resetEncoders();
                robot.autoDriveRobot(-9,  -9); //puts pixel on stripe
                robot.autoDriveRobot(25,  25);//backs up from pixel
                robot.autoDriveRobot(-60, 60);
                robot.raiseArm(-95);
                robot.resetEncoders();
                robot.autoDriveRobot(-27, -27);
                robot.openClaw();
                sleep(1000);
                robot.autoDriveRobot(5, 5);
            }
            else if (propNumber == robot.RIGHT) {
                robot.autoDriveRobot(-30, 30);// turn right
                robot.resetEncoders();
                robot.autoDriveRobot(-8, -8); //puts pixel on stripe
                robot.autoDriveRobot(9, 9); // backs away from pixel
                robot.autoDriveRobot(30, -30); // turns left
                robot.resetEncoders();
                robot.autoDriveRobot(-30, -30); // moves away from spike area
                robot.autoDriveRobot(-30, 30); // turn to backstage
                robot.raiseArm(-95);
                robot.resetEncoders();
                robot.autoDriveRobot(-35, -35); // drive towards backstage
                robot.autoDriveRobot(-30, 30);
                robot.resetEncoders();
                robot.autoDriveRobot(-40, -40);
                robot.autoDriveRobot(30, -30);
                robot.resetEncoders();
                robot.autoDriveRobot(-15, -15);
                robot.openClaw();
                sleep(1000);
                robot.autoDriveRobot(5, 5);

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



