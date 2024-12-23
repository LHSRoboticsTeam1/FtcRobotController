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

            robot.autoDriveRobot(-10, -10);
            robot.turnToHeading(90,.3);
            robot.beltup();
            sleep(1200);


        }
        sleep(8000);
        robot.shutDown();
    }

    }

