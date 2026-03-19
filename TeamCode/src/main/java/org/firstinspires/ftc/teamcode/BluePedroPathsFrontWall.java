package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.math.MathFunctions;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.teamPedroPathing.PedroPathFlipper;

public class BluePedroPathsFrontWall implements PedroPathsFrontWall {

    private final PedroPathsFrontWall red;
    private final PedroPathFlipper flipper;

    private final Pose blueGoalStart =
            new Pose(21.81, 126.977, Math.toRadians(145));

    public BluePedroPathsFrontWall(Follower follower) {
        red = new RedPedroPathsFrontWall(follower);
        flipper = new PedroPathFlipper(follower);
    }

    @Override public Pose startFrontPose() {
        return new Pose(
                144 - red.startFrontPose().getX(),
                red.startFrontPose().getY(),
                MathFunctions.normalizeAngle(Math.PI - red.startFrontPose().getHeading())
                );
    }

    @Override public Pose parkPose() {
        return new Pose(
                144 - red.parkPose().getX(),
                red.parkPose().getY(),
                MathFunctions.normalizeAngle(Math.PI - red.parkPose().getHeading())
        );
    }

    @Override public Pose startGoalPose() {
        return blueGoalStart;
    }

    @Override public PathChain startFrontToLaunchPath() {
        return flipper.flipPathChain(red.startFrontToLaunchPath());
    }

    @Override public PathChain startGoalToLaunchPath() {
        return flipper.flipPathChain(red.startGoalToLaunchPath());
    }

    @Override public PathChain launchToStartGoalSideBallPickup() {
        return flipper.flipPathChain(red.launchToStartGoalSideBallPickup());
    }

    @Override public PathChain startGoalSideBallPickupToEndGoalSideBallPickup() {
        return flipper.flipPathChain(red.startGoalSideBallPickupToEndGoalSideBallPickup());
    }

    @Override public PathChain endGoalSideBallPickupToLaunch() {
        return flipper.flipPathChain(red.endGoalSideBallPickupToLaunch());
    }

    @Override public PathChain launchToStartCentralSideBallPickup() {
        return flipper.flipPathChain(red.launchToStartCentralSideBallPickup());
    }

    @Override public PathChain startCentralSideBallPickupToEndCentralSideBallPickup() {
        return flipper.flipPathChain(red.startCentralSideBallPickupToEndCentralSideBallPickup());
    }

    @Override public PathChain endCentralSideBallPickupToLaunch() {
        return flipper.flipPathChain(red.endCentralSideBallPickupToLaunch());
    }

    @Override public PathChain launchToStartAudienceSideBallPickup() {
        return flipper.flipPathChain(red.launchToStartAudienceSideBallPickup());
    }

    @Override public PathChain startAudienceSideBallPickupToEndAudienceSideBallPickup() {
        return flipper.flipPathChain(red.startAudienceSideBallPickupToEndAudienceSideBallPickup());
    }

    @Override public PathChain endAudienceSideBallPickupToLaunch() {
        return flipper.flipPathChain(red.endAudienceSideBallPickupToLaunch());
    }
}
