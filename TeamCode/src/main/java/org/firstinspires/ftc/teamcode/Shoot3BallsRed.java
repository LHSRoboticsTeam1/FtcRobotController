package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.teamPedroPathing.PedroPathTelemetry;
import org.firstinspires.ftc.teamcode.teamPedroPathing.PedroSleep;
import org.firstinspires.ftc.teamcode.teamPedroPathing.PedroTeleopData;

@Autonomous(name = "FARShoot3BallsRed", group = "Auto")
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

        robot.outtakeShoot(1);
        sleep(3000);

        // ---------------- START ALL SYSTEMS ONCE ----------------
        robot.outtakeShoot(1);   // shooter stays ON
        telemetry.addData("Auto", "Shooting (initial)");
        shootBalls();

        sleep(3000);

        // ---------------- PARK ----------------
        driveForward(-0.2, 1600);

        telemetry.addData("Auto", "Finished Shooting 3 Balls");
        telemetry.update();
        PedroTeleopData.startingPose = new Pose(36, 81, Math.toRadians(100));

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
        long shootDurationMs = 1000;
        sleep(2000);
        long shootDurationMs2 = 1000;
        sleep(2000);
        long shootDurationMs3 = 1000;

        while (opModeIsActive()
                && System.currentTimeMillis() - startTime < shootDurationMs + shootDurationMs2 + shootDurationMs3) {

            robot.liftItUp();
            sleep(1000);
            robot.stopLiftItUp();
            sleep(600);
        }

        robot.intakeOff();
        robot.outtakeStop();
    }
}


