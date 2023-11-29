package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "ParkBackstageFrontBlue")
public class ParkBackstageFrontBlue extends LinearOpMode {
    @Override
    public void runOpMode() {

        HardwareForRObot robot = new HardwareForRObot(this);
        robot.init();
        waitForStart();
        robot.autoDriveRobot(-80,  -80 );
        robot.autoDriveRobot( 35 ,  -35 );
        robot.autoDriveRobot( -100 ,  -100 );



        robot.shutDown();
    }


}
