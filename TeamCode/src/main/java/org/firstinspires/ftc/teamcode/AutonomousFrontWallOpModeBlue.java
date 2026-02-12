package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.teamPedroPathing.PedroPathFollower;
import org.firstinspires.ftc.teamcode.teamPedroPathing.PedroPathTelemetry;

@Autonomous(name = "AutonomousBlueeFrontWall")
public class AutonomousFrontWallOpModeBlue extends LinearOpMode {
    private PedroPathTelemetry pedroTelemetry;
    private Follower follower;
    private final double ballPickupPower = .4;
    private AllianceColor selectedColor = AllianceColor.BLUE;
    private PedroPathsFrontWall pedroPaths;

    private HardwareForRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new HardwareForRobot(this);
        robot.init();

        PedroPathConfiguration pedroPathConfiguration = new PedroPathConfiguration(this);
        follower = pedroPathConfiguration.getFollower();

        pedroPaths = createPedroPaths(selectedColor);
        pedroTelemetry = new PedroPathTelemetry(telemetry, follower, selectedColor);

        PedroPathFollower pedroPathFollower =
                new PedroPathFollower(this, follower, pedroTelemetry, pedroPaths.startingPose());

        waitForStart();

        if (opModeIsActive()) {

            // ---------- START -> LAUNCH ----------
            pedroPathFollower.followPathChain(
                    pedroPaths.startToLaunchPath(),
                    "Start to launch"
            );
            shootBalls();

            // ---------- GOAL SIDE ----------
            pedroPathFollower.followPathChain(
                    pedroPaths.launchToStartGoalSideBallPickup(),
                    "Launch to goal side pickup start"
            );
            ballPickup();

            pedroPathFollower.followPathChain(
                    pedroPaths.startGoalSideBallPickupToEndGoalSideBallPickup(),
                    ballPickupPower,
                    "Goal side pickup"
            );

         /*   pedroPathFollower.followPathChain(
                    pedroPaths.endGoalSideBallPickupToLaunch(),
                    "Goal side pickup to launch"
            );
            shootBalls();*/
/*

            // ---------- CENTRAL ----------
            pedroPathFollower.followPathChain(
                    pedroPaths.launchToStartCentralSideBallPickup(),
                    "Launch to central pickup start"
            );
            ballPickup();

            pedroPathFollower.followPathChain(
                    pedroPaths.startCentralSideBallPickupToEndCentralSideBallPickup(),
                    ballPickupPower,
                    "Central pickup"
            );

            pedroPathFollower.followPathChain(
                    pedroPaths.endCentralSideBallPickupToLaunch(),
                    "Central pickup to launch"
            );
            shootBalls();

            // ---------- AUDIENCE ----------
            pedroPathFollower.followPathChain(
                    pedroPaths.launchToStartAudienceSideBallPickup(),
                    "Launch to audience pickup start"
            );
            ballPickup();

            pedroPathFollower.followPathChain(
                    pedroPaths.startAudienceSideBallPickupToEndAudienceSideBallPickup(),
                    ballPickupPower,
                    "Audience pickup"
            );

            pedroPathFollower.followPathChain(
                    pedroPaths.endAudienceSideBallPickupToLaunch(),
                    "Audience pickup to launch"
            );
            shootBalls();
*/

            // ---------- FINAL GOAL SIDE ----------
         /*   pedroPathFollower.followPathChain(
                    pedroPaths.launchToStartGoalSideBallPickup(),
                    "Final goal side pickup start"
            );

            pedroPathFollower.followPathChain(
                    pedroPaths.startGoalSideBallPickupToEndGoalSideBallPickup(),
                    ballPickupPower,
                    "Final goal side pickup"
            );*/
        }
    }

    private PedroPathsFrontWall createPedroPaths(AllianceColor selectedColor) {
        if (selectedColor == AllianceColor.BLUE) {
            return new BluePedroPathsFrontWall(follower);
        } else {
            return new BluePedroPathsFrontWall(follower);
        }
    }

    private void shootBalls() {
        pedroTelemetry.pathTelemetry("Shooting balls.");
        robot.outtakeShoot();
        robot.intakeOn();

        sleep(1200);

        for (int i = 0; i < 6; i++) {
            robot.liftItUp();
            sleep(1000);
            robot.stopLiftItUp();
            sleep(1500);
        }
    }

    private void ballPickup() {
        robot.intakeOn();
        pedroTelemetry.pathTelemetry("Ball scooper turned on.");
        sleep(500);
        robot.intakeOff();
    }
}
