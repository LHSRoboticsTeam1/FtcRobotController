package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.teamPedroPathing.PedroPathTelemetry;
import org.firstinspires.ftc.teamcode.teamPedroPathing.PedroSleep;
import org.firstinspires.ftc.teamcode.teamPedroPathing.PedroTeleopData;

@Autonomous(name = "FARShoot3BallsBlue", group = "Auto")
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

        robot.outtakeShoot(1);
        sleep(3000);

        // ---------------- START ALL SYSTEMS ONCE ----------------
        robot.outtakeShoot(1);   // shooter stays ON
        telemetry.addData("Auto","Shooting (initial)");
        shootBalls();

        sleep(3000);

        // ---------------- PARK ----------------
        driveForward(-0.2, 1600);

        telemetry.addData("Auto", "Finished Shooting 3 Balls");
        telemetry.update();

        PedroTeleopData.startingPose = new Pose (35, 30, Math.toRadians(160));

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

    private void shootBalls() {

        robot.intakeOn();

        long startTime = System.currentTimeMillis();
        long shootDurationMs = 3000;

        while (opModeIsActive()
                && System.currentTimeMillis() - startTime < shootDurationMs) {

            robot.liftItUp();
            sleep(600);
            robot.stopLiftItUp();
            sleep(500);
        }

        robot.intakeOff();
        robot.outtakeStop();
    }
}
