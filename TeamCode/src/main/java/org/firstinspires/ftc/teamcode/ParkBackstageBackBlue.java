package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "ParkBackstage")
public class ParkBackstageBackBlue extends LinearOpMode {
    static final int LEFT = 1;
    static final int RIGHT = 2;
    static final int CENTER = 3;
    HardwareForRObot robot;
    @Override
    public void runOpMode() {

         robot = new HardwareForRObot(this);
        robot.init();
        //robot.holdDrone();
        waitForStart();
      //  robot.autoDriveRobot(-30,  -30 );
        int propNumber = PropNumber();
        if (propNumber == CENTER) {
            robot.autoDriveRobot(-5,  -5 ); //puts pixel on stripe
            robot.autoDriveRobot(5,  5 );//backs up from pixel
            robot.autoDriveRobot(-90,  90 );// turn
            robot.autoDriveRobot(-20,  -20 );//park


        }
        else if (propNumber == LEFT) {
            robot.autoDriveRobot(-80,  80 );// turn left?
            robot.autoDriveRobot(-5,  -5 ); //puts pixel on stripe
            robot.autoDriveRobot(5,  5 );//backs up from pixel
            robot.autoDriveRobot(80,  -80 );// turn Right?
            robot.autoDriveRobot(-25,  -25 );//backs up to wall
            robot.autoDriveRobot(-80,  80 );// turn left?
            robot.autoDriveRobot(-20,  -20 );//park


        }
        else if (propNumber == RIGHT) {
            robot.autoDriveRobot(60,  -60 );// turn Right?
            robot.autoDriveRobot(-10,  -10 ); //puts pixel on stripe
            robot.autoDriveRobot(10,  10 );//backs up from pixel
            robot.autoDriveRobot(20,  20 );//backs up from pixel and parks


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

    public int PropNumber() {
        double leftDistance = robot.findLocationLeft();
        double rightDistance = robot.findLocationRight();
        int propNumber;
        telemetry.setAutoClear(true);

        if (leftDistance <= 10) {
            telemetry.addData("prop", "1");
            propNumber = LEFT;
            return propNumber;
        }
        else if (rightDistance <= 10) {
            telemetry.addData("prop", "2");
            propNumber = RIGHT;
            return propNumber;
        }
        else {
            telemetry.addData("prop", "3");
            propNumber = CENTER;
            return propNumber;
        }
    }
}
