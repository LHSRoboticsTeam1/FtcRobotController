package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teamPedroPathing.PedroPathTelemetry;
import org.firstinspires.ftc.teamcode.teamPedroPathing.PedroSleep;
import org.firstinspires.ftc.teamcode.teamPedroPathing.PedroTeleopData;

/**
 * This OpMode demonstrates using Pedro Pathing for teleOp,
 * and also building and a Path to "instantly" go to the launch Pose
 * from anywhere on the field.
 */

@TeleOp(name = "Pedro Path Teleop")
public class PedroPathTeleOp extends LinearOpMode {
    private Follower follower;
    private Pose longLaunchPose;
    private Pose shortLaunchPose;
    private Pose parkPose;
    private AllianceColor allianceColor;
    private PedroSleep pedroSleep;
    private PedroPathsFrontWall pedroPaths;

    private double[] pLevels = { .95, 1 };
    private int pLevelCurrent = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        HardwareForRobot robot = new HardwareForRobot(this);
        robot.init();

        PedroPathConfiguration pedroPathConfiguration = new PedroPathConfiguration(this);
        allianceColor = PedroTeleopData.allianceColor == null ? AllianceColor.RED : PedroTeleopData.allianceColor;
        follower = pedroPathConfiguration.getFollower();
        follower.startTeleOpDrive(); //This calls update() as well.
        initSetup(); //Give chance to change alliance color.
        pedroPaths = (allianceColor == AllianceColor.RED) ? new RedPedroPathsFrontWall(follower) :
                new BluePedroPathsFrontWall(follower);
        follower.setStartingPose(PedroTeleopData.startingPose == null ? pedroPaths.startFrontPose() : PedroTeleopData.startingPose);
        setlongLaunchPose(); //Set launch pose for alliance color.
        setShortLaunchPose();
        setParkPose();
        pedroSleep = new PedroSleep(follower);
        PedroPathTelemetry pedroPathTelemetry = new PedroPathTelemetry(telemetry, follower, allianceColor);

        waitForStart();

        /*
         * The follower can be either in startTeleOpDrive() or followPath().
         */

        String pedroMessage = "Power mode: Low";
        while (opModeIsActive()) {
            follower.update();
            pedroPathTelemetry.pathTelemetry(pedroMessage);

            //Call setTeleOpDrive() as long as isTeleopDrive().
            if (follower.isTeleopDrive()) {
                follower.setTeleOpDrive(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, true);
            }
            /*
             * If not isTeleopDrive(), then we might still be following a Path.
             * If that Path is complete (not isBusy()), do whatever we went there to do,
             * then re-startTeleOpDrive().
             */

            else if (!follower.isBusy()) {
                pedroSleep.sleep(750);
                //shootBalls(); 
                follower.startTeleOpDrive();
            }
            /*
             * See if we pressed the gamepad button to follow a Path.
             * If whatever Path we might already have been on is complete (not isBusy()),
             * followPath() the path fromHereToLaunch().
             */

            // ----- DPAD AUTO SETS ---------
            if (gamepad1.dpadUpWasPressed()){
                if (follower.isBusy()) {
                    follower.startTeleOpDrive();
                } else {
                    follower.followPath(fromHereToLongLaunch());
                }
            }

            if (gamepad1.dpadDownWasPressed()){
                if (follower.isBusy()) {
                    follower.startTeleOpDrive();
                } else {
                    follower.followPath(fromHereToPark());
                }
            }

            if(gamepad1.dpadLeftWasPressed()){
                if(follower.isBusy()){
                    follower.startTeleOpDrive();
                }else {
                    follower.followPath(fromHereToShortLaunch());
                }
            }

            //dpad right reserved for full park?
            // ---------------- INTAKE ----------------
            if (gamepad1.b) {
                robot.intakeOn();
            } else {
                robot.intakeOff();
            }

            // ---------------- LIFT ----------------
            if (gamepad1.x) {
                robot.liftItUp();
            } else {
                robot.stopLiftItUp();
            }

            // ---------------- OUTTAKE ----------------
            if (gamepad1.a) {
                robot.outtakeShoot(pLevels[pLevelCurrent]);
            } else {
                robot.outtakeStop();
            }

            if (gamepad1.rightBumperWasPressed()) {
                pLevelCurrent++;

                // Toggle power level; if high, then go low
                pLevelCurrent %= 2;

                pedroMessage = "Power Mode: " + ((pLevelCurrent == 0) ? "Low" : "High");
            }
        }
    }

    /*
     * Build a PathChain from our current Pose to the launchPose,
     * and set it to LinearHeadingInterpolation.
     *
     * @return PathChain
     */

    private PathChain fromHereToLongLaunch() {
        Pose herePose = follower.getPose();

        return follower.pathBuilder()
                .addPath(new BezierLine(herePose, longLaunchPose))
                .setLinearHeadingInterpolation(herePose.getHeading(), longLaunchPose.getHeading())
                .build();
    }

    private PathChain fromHereToShortLaunch() {
        Pose herePose = follower.getPose();

        return follower.pathBuilder()
                .addPath(new BezierLine(herePose, shortLaunchPose))
                .setLinearHeadingInterpolation(herePose.getHeading(), shortLaunchPose.getHeading())
                .build();
    }
    private PathChain fromHereToPark() {
        Pose herePose = follower.getPose();

        return follower.pathBuilder()
                .addPath(new BezierLine(herePose, parkPose))
                .setLinearHeadingInterpolation(herePose.getHeading(), parkPose.getHeading())
                .build();
    }


    private void setParkPose() {
        parkPose = pedroPaths.parkPose();

    }
    private void setShortLaunchPose(){
        shortLaunchPose = pedroPaths.shortLaunchPose();
        telemetry.addLine("Short Launch Pose");
        telemetry.addData("x", shortLaunchPose.getX());
        telemetry.addData("y", shortLaunchPose.getY());
        telemetry.addData("heading", Math.toDegrees(shortLaunchPose.getHeading()));
        telemetry.update();
    }
    private void setlongLaunchPose(){
        longLaunchPose = pedroPaths.longLaunchPose();
        telemetry.addLine("long Launch Pose");
        telemetry.addData("x", longLaunchPose.getX());
        telemetry.addData("y", longLaunchPose.getY());
        telemetry.addData("heading", Math.toDegrees(longLaunchPose.getHeading()));
        telemetry.update();
    }
    private void initSetup() {
        while (opModeInInit()) {
            telemetry.addLine("Press Right Bumper to toggle between Red and Blue alliance.");
            telemetry.addData(allianceColor.toString(), " currently selected");
            telemetry.update();

            allianceColor = AllianceColor.INSTANCE.toggleColor(gamepad1.rightBumperWasPressed(), allianceColor);
        }
    }
}