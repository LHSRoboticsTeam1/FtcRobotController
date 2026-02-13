package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.pedropathing.follower.Follower;

import org.firstinspires.ftc.teamcode.teamPedroPathing.PedroPathFollower;
import org.firstinspires.ftc.teamcode.teamPedroPathing.PedroPathTelemetry;

@TeleOp(name="TeleOp_6407", group="Linear OpMode")
public class Teleop_6407 extends LinearOpMode {

    private HardwareForRobot robot;

    @Override
    public void runOpMode() {

        robot = new HardwareForRobot(this);
        robot.init();

        telemetry.addData("Status", "Ready");
        telemetry.update();

        waitForStart();

        boolean lastLeftBumper = false;
        boolean lastLeftTrigger = false;
        boolean autoAligning = false;

        while (opModeIsActive()) {

            // ---------- AUTO ALIGN CONTROLS ----------
            boolean lb = gamepad1.left_bumper;          // BLUE
            boolean lt = gamepad1.left_trigger > 0.5;  // RED

            // BLUE ALLIANCE
            if (lb && !lastLeftBumper && !autoAligning) {
                autoAligning = true;
                goToShootingPoseBlue();
                autoAligning = false;
            }

            // RED ALLIANCE
            if (lt && !lastLeftTrigger && !autoAligning) {
                autoAligning = true;
                goToShootingPoseRed();
                autoAligning = false;
            }

            lastLeftBumper = lb;
            lastLeftTrigger = lt;

            // ---------------- MANUAL DRIVE ----------------
            if (!autoAligning) {
                double drive  = gamepad1.left_stick_y;
                double strafe = gamepad1.left_stick_x;
                double turn   = gamepad1.right_stick_x;
                robot.manuallyDriveRobot(strafe, drive, turn);
            }

            // ---------------- INTAKE ----------------
            if (gamepad1.b) robot.intakeOn();
            else robot.intakeOff();

            // ---------------- LIFT ----------------
            if (gamepad1.x) robot.liftItUp();
            else robot.stopLiftItUp();

            // ---------------- OUTTAKE ----------------
            if (gamepad1.a) robot.outtakeShoot();
            else robot.outtakeStop();

            telemetry.update();
        }
    }

    // =================================================
    // RED ALLIANCE → SHOOTING POSE
    // =================================================
    private void goToShootingPoseRed() {

        PedroPathConfiguration pedroConfig = new PedroPathConfiguration(this);
        Follower follower = pedroConfig.getFollower();

        PedroPathsFrontWall paths =
                new RedPedroPathsFrontWall(follower);

        PedroPathTelemetry pedroTelemetry =
                new PedroPathTelemetry(telemetry, follower, AllianceColor.RED);

        PedroPathFollower pedroFollower =
                new PedroPathFollower(this, follower, pedroTelemetry, follower.getPose());

        pedroFollower.followPathChain(
                paths.startToLaunchPath(),
                "TeleOp RED -> Shooting Pose"
        );
    }

    // =================================================
    // BLUE ALLIANCE → SHOOTING POSE
    // =================================================
    private void goToShootingPoseBlue() {

        PedroPathConfiguration pedroConfig = new PedroPathConfiguration(this);
        Follower follower = pedroConfig.getFollower();

        PedroPathsFrontWall paths =
                new BluePedroPathsFrontWall(follower);

        PedroPathTelemetry pedroTelemetry =
                new PedroPathTelemetry(telemetry, follower, AllianceColor.BLUE);

        PedroPathFollower pedroFollower =
                new PedroPathFollower(this, follower, pedroTelemetry, follower.getPose());

        pedroFollower.followPathChain(
                paths.startToLaunchPath(),
                "TeleOp BLUE -> Shooting Pose"
        );
    }
}
