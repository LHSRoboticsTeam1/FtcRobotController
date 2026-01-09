package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Shoot3Balls", group = "Auto")
public class Shoot3Balls extends LinearOpMode {

    private HardwareForRobot robot;

    @Override
    public void runOpMode() {

        robot = new HardwareForRobot(this);
        robot.init();

        telemetry.addData("Status", "Initialized for 3-Ball Auto");
        telemetry.update();

        waitForStart();

        if (!opModeIsActive()) return;

        // ---------- DRIVE FORWARD 2 FEET ----------
        driveForward2ft();

        telemetry.addData("Auto", "Finished Shooting 3 Balls");
        telemetry.update();

        sleep(5000);
    }

    private void driveForward2ft() {
        // Forward: +y → but your drive is inverted, so we use NEGATIVE power
        double power = -0.4;

        robot.manuallyDriveRobot(0, power, 0);
        sleep(850);    // adjust this if needed (650–900ms depending on robot)
        robot.manuallyDriveRobot(0, 0, 0);
    }
}
