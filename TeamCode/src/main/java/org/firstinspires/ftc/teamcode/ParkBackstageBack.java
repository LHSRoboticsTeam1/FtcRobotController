package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "ParkBackstage")
public class ParkBackstageBack extends LinearOpMode {
    @Override
    public void runOpMode() {

        HardwareForRObot robot = new HardwareForRObot(this);
        robot.init();
        //robot.holdDrone();
        waitForStart();
        robot.autoDriveRobot(-50,  -50 );

        robot.shutDown();
    }


}
