package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.teamPedroPathing.PedroPathTelemetry;
import org.firstinspires.ftc.teamcode.teamPedroPathing.PedroSleep;
import org.firstinspires.ftc.teamcode.teamPedroPathing.PedroTeleopData;

@Autonomous(name = "Start from front wall / goal side")
public class AutonomousFrontWallOpModeWithActions extends LinearOpMode {

    private PedroPathTelemetry pedroTelemetry;
    private Follower follower;

    private StartPosition startPosition = StartPosition.FRONT_WALL;
    private AllianceColor selectedColor = AllianceColor.RED;
    private BallSpikeLocation ballSpikeLocation = BallSpikeLocation.AUDIENCE_SIDE;

    private final double ballPickupPower = 0.4;

    private int pathState = 0;
    private String pedroMessage = "NO MESSAGE";

    private PedroPathsFrontWall pedroPaths;
    private PathChain pathFromStartToLaunch;
    private Pose startPose;
    private PathChain pathFromLaunchZoneToStartBallPickup;
    private PathChain pathFromStartBallPickupToEndBallPickup;
    private PathChain pathFromEndBallPickupToLaunchZone;
    private PedroSleep sleeper;

    private HardwareForRobot robot;

    @Override
    public void runOpMode() {

        robot = new HardwareForRobot(this);
        robot.init();

        PedroPathConfiguration pedroPathConfiguration =
                new PedroPathConfiguration(this);
        follower = pedroPathConfiguration.getFollower();

        // ---------- INIT MENU ----------
        initSetup();

        sleeper = new PedroSleep(follower);

        // ---------- CREATE PATHS ----------
        pedroPaths = createPedroPaths(selectedColor);
        setStartPath();
        setBallSpikeLocationPaths();

        pedroTelemetry =
                new PedroPathTelemetry(telemetry, follower, selectedColor);
        PedroTeleopData.allianceColor = selectedColor;

        // ---------- SET START POSE ----------
        follower.setStartingPose(startPose);

        follower.update();

        waitForStart();

        robot.outtakeShoot(.45);

        // ---------- STATE MACHINE ----------
        while (opModeIsActive()) {
            follower.update();
            performActions();
            pedroTelemetry.pathTelemetry(pedroMessage);
        }

        PedroTeleopData.startingPose = follower.getPose();

        robot.outtakeStop();
    }

    // =====================================================
    // STATE MACHINE
    // =====================================================

    private void setStartPath(){
        if(startPosition == StartPosition.FRONT_WALL) {
            startPose = pedroPaths.startFrontPose();
            pathFromStartToLaunch = pedroPaths.startFrontToLaunchPath();
        } else{
            startPose = pedroPaths.startGoalPose();
            pathFromStartToLaunch = pedroPaths.startGoalToLaunchPath();
        }
    }

    private void performActions() {

        switch (pathState) {

            // 0️⃣ START → LAUNCH
            case 0:
                if (!follower.isBusy()) {
                    pedroMessage = "Start → Launch";
                    follower.followPath(pathFromStartToLaunch);
                    pathState++;
                }
                break;

            // 1️⃣ SHOOT FIRST TIME
            case 1:
                if (!follower.isBusy()) {
                    pedroMessage = "Shooting (initial)";
                    shootBalls();
                    pathState++;
                }
                break;

            // 2️⃣ LAUNCH → BALL PICKUP
            case 2:
                if (!follower.isBusy()) {
                    pedroMessage = "Launch → Ball Pickup";
                    follower.followPath(pathFromLaunchZoneToStartBallPickup);
                    pathState++;
                }
                break;

            // 3️⃣ PICKUP BALLS
            case 3:
                if (!follower.isBusy()) {
                    pedroMessage = "Picking up balls";
                    robot.intakeOn();

                    follower.followPath(
                            pathFromStartBallPickupToEndBallPickup,
                            ballPickupPower,
                            true
                    );
                    pathState++;
                }
                break;

            // 4️⃣ RETURN TO LAUNCH
            case 4:
                if (!follower.isBusy()) {
                    pedroMessage = "Return → Launch";
                    follower.followPath(pathFromEndBallPickupToLaunchZone);
                    pathState++;
                }
                break;

            // 5️⃣ SHOOT AGAIN
            case 5:
                if (!follower.isBusy()) {
                    pedroMessage = "Shooting (after pickup)";
                    shootBalls();

                    ballSpikeLocation = BallSpikeLocation.INSTANCE.toggleLocation(true, ballSpikeLocation);
                    ballSpikeLocation = BallSpikeLocation.INSTANCE.toggleLocation(true, ballSpikeLocation);

                    setBallSpikeLocationPaths();
                    follower.followPath(pathFromLaunchZoneToStartBallPickup);
                    pathState++;
                }
                break;

            // 6 Second ball pickup
            case 6:
                if (!follower.isBusy()) {
                    pedroMessage = "Picking up balls";
                    robot.intakeOn();

                    follower.followPath(
                            pathFromStartBallPickupToEndBallPickup,
                            ballPickupPower,
                            true
                    );
                    pathState++;
                }
                break;

                // 4️⃣ RETURN TO LAUNCH
            case 7:
                if (!follower.isBusy()) {
                    pedroMessage = "Return → Second Launch";
                    follower.followPath(pathFromEndBallPickupToLaunchZone);
                    pathState++;
                }
                break;

            // 5️⃣ SHOOT AGAIN
            case 8:
                if (!follower.isBusy()) {
                    pedroMessage = "Shooting (after second pickup)";
                    shootBalls();
                    robot.outtakeStop();
                    robot.intakeOff();

                    follower.followPath(pathFromLaunchZoneToStartBallPickup);
                    pathState++;
                }
                break;

            // 6️⃣ DONE
            case 9:
                if (!follower.isBusy()) {
                    pedroMessage = "Autonomous Complete";
                }
                break;
        }
    }

    // =====================================================
    // SHOOTING (BLOCKING – SAME AS YOUR ORIGINAL)
    // =====================================================
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
    }

    // =====================================================
    // BALL SPIKE LOCATION PATHS
    // =====================================================
    private void setBallSpikeLocationPaths() {

        switch (ballSpikeLocation) {

            case AUDIENCE_SIDE:
                pathFromLaunchZoneToStartBallPickup =
                        pedroPaths.launchToStartAudienceSideBallPickup();
                pathFromStartBallPickupToEndBallPickup =
                        pedroPaths.startAudienceSideBallPickupToEndAudienceSideBallPickup();
                pathFromEndBallPickupToLaunchZone =
                        pedroPaths.endAudienceSideBallPickupToLaunch();
                break;

            case MIDDLE:
                pathFromLaunchZoneToStartBallPickup =
                        pedroPaths.launchToStartCentralSideBallPickup();
                pathFromStartBallPickupToEndBallPickup =
                        pedroPaths.startCentralSideBallPickupToEndCentralSideBallPickup();
                pathFromEndBallPickupToLaunchZone =
                        pedroPaths.endCentralSideBallPickupToLaunch();
                break;

            case GOAL_SIDE:
                pathFromLaunchZoneToStartBallPickup =
                        pedroPaths.launchToStartGoalSideBallPickup();
                pathFromStartBallPickupToEndBallPickup =
                        pedroPaths.startGoalSideBallPickupToEndGoalSideBallPickup();
                pathFromEndBallPickupToLaunchZone =
                        pedroPaths.endGoalSideBallPickupToLaunch();
                break;
        }
    }

    // =====================================================
    // PATH FACTORY
    // =====================================================
    private PedroPathsFrontWall createPedroPaths(AllianceColor color) {

        if (color == AllianceColor.RED) {
            return new RedPedroPathsFrontWall(follower);
        } else {
            return new BluePedroPathsFrontWall(follower);
        }
    }

    // =====================================================
    // INIT MENU
    // =====================================================
    private void initSetup() {

        while (opModeInInit()) {

            telemetry.addLine("RB = Alliance (Red / Blue)");
            telemetry.addLine("A = Ball Pickup Position 2");
            telemetry.addLine("LB = Ball Pickup Location");
            telemetry.addLine("X  = Start Position");

            telemetry.addData("Alliance", selectedColor);
            telemetry.addData("Ball Location", ballSpikeLocation);
            telemetry.addData("Start Position", startPosition);

            telemetry.update();

            selectedColor =
                    AllianceColor.INSTANCE.toggleColor(
                            gamepad1.rightBumperWasPressed(), selectedColor);

            ballSpikeLocation =
                    BallSpikeLocation.INSTANCE.toggleLocation(
                            gamepad1.leftBumperWasPressed(), ballSpikeLocation);

            startPosition =
                    StartPosition.toggle(
                            gamepad1.xWasPressed(), startPosition);
        }
    }
}
