package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Shoot3BallsRed", group = "Auto")
public class Shoot3BallsRed extends LinearOpMode {

    private HardwareForRobot robot;

    @Override
    public void runOpMode() {

        robot = new HardwareForRobot(this);
        robot.init();

        telemetry.addData("Status", "Initialized for 3-Ball Auto");
        telemetry.update();

        waitForStart();
        if (!opModeIsActive()) return;

        // ---------------- MOVE TO SHOOTING POSITION ----------------
        driveForward(0.4, 1600);

        // ---------------- START ALL SYSTEMS ONCE ----------------
        robot.outtakeShoot();   // shooter stays ON
        robot.intakeOn();       // intake stays ON

        // let shooter fully spin up
        sleep(1200);

        // ---------------- FEED 3 BALLS ----------------
        for (int i = 0; i < 6; i++) {

            // FEED ONE BALL
            robot.liftItUp();
            sleep(700);

            robot.stopLiftItUp();

            // TIME FOR BALL TO CLEAR & NEXT BALL TO MOVE UP
            sleep(1200);
        }

        // ---------------- STOP EVERYTHING ----------------
        robot.stopLiftItUp();
        robot.intakeOff();
        robot.outtakeStop();

        // ---------------- PARK ----------------
        strafeRight(-0.5, 900);

        telemetry.addData("Auto", "Finished Shooting 3 Balls");
        telemetry.update();

        sleep(3000);
    }

    // ---------------- DRIVE HELPERS ----------------
    private void driveForward(double power, long timeMs) {
        robot.manuallyDriveRobot(0, power, 0);
        sleep(timeMs);
        robot.manuallyDriveRobot(0, 0, 0);
        sleep(100);
    }

    private void strafeRight(double power, long timeMs) {
        robot.manuallyDriveRobot(power, 0, 0);
        sleep(timeMs);
        robot.manuallyDriveRobot(0, 0, 0);
        sleep(100);
    }
}
