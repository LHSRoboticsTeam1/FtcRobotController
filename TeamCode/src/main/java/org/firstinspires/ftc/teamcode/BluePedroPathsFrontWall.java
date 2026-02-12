package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.teamPedroPathing.PedroPathFlipper;

public class BluePedroPathsFrontWall implements PedroPathsFrontWall {
    private final Follower follower;

    /*private final Pose startingPose = new Pose(87.5, 7.17, Math.toRadians(90));
    private final Pose launchPose = new Pose(90, 90, Math.toRadians(45));

    private final Pose startGoalSideBallPickupPose = new Pose(108, 83.5, 0);
    private final Pose endGoalSideBallPickupPose = new Pose(130, 83.5, 0);

    private final Pose startCentralSidePickupPose = new Pose(106, 60, 0);
    private final Pose endCentralSidePickupPose = new Pose(130, 60, 0);

    private final Pose startAudienceSidePickupPose = new Pose(106, 35, 0);
    private final Pose endAudienceSidePickupPose = new Pose(130, 35, 0);
*/
    private PathChain startToLaunchPath;

    private PathChain launchToStartGoalSideBallPickup;
    private PathChain startGoalSideBallPickupToEndGoalSideBallPickup;
    private PathChain endGoalSideBallPickupToLaunch;

    private PathChain launchToStartCentralSideBallPickup;
    private PathChain startCentralSideBallPickupToEndCentralSideBallPickup;
    private PathChain endCentralSideBallPickupToLaunch;

    private PathChain launchToStartAudienceSideBallPickup;
    private PathChain startAudienceSideBallPickupToEndAudienceSideBallPickup;
    private PathChain endAudienceSideBallPickupToLaunch;

    private PedroPathsFrontWall redPaths;

    private PedroPathFlipper pedroPathFlipper;

/*    public BluePedroPathsFrontWall(Follower follower) {
        this.follower = follower;
        this.redPaths = redPaths;
        this.pedroPathFlipper = new PedroPathFlipper(follower);
        initPaths();
    }*/
    public BluePedroPathsFrontWall(Follower follower) {
        this.follower = follower;
        this.redPaths = new RedPedroPathsFrontWall(follower);
        this.pedroPathFlipper = new PedroPathFlipper(follower);
        initPaths();
    }
    private void initPaths() {
        startToLaunchPath = buildStartToLaunchPath();

        launchToStartGoalSideBallPickup = buildLaunchToStartGoalSideBallPickup();
        startGoalSideBallPickupToEndGoalSideBallPickup = buildStartGoalSideBallPickupToEndGoalSideBallPickup();
        endGoalSideBallPickupToLaunch = buildEndGoalSideBallPickupToLaunch();

        launchToStartCentralSideBallPickup = buildLaunchToStartCentralSideBallPickup();
        startCentralSideBallPickupToEndCentralSideBallPickup = buildStartCentralSideBallPickupToEndCentralSideBallPickup();
        endCentralSideBallPickupToLaunch = buildEndCentralSideBallPickupToLaunch();

        launchToStartAudienceSideBallPickup = buildLaunchToStartAudienceSideBallPickup();
        startAudienceSideBallPickupToEndAudienceSideBallPickup = buildStartAudienceSideBallPickupToEndAudienceSideBallPickup();
        endAudienceSideBallPickupToLaunch = buildEndAudienceSideBallPickupToLaunch();
    }

    private PathChain buildStartToLaunchPath() {
        return pedroPathFlipper.flipPathChain(redPaths.startToLaunchPath());
    }

    @Override
    public Pose startingPose() {
        return startToLaunchPath.firstPath().getFirstControlPoint();
    }

    @Override
    public PathChain startToLaunchPath() {
        return startToLaunchPath;
    }

    private PathChain buildLaunchToStartGoalSideBallPickup() {
        return pedroPathFlipper.flipPathChain(redPaths.launchToStartGoalSideBallPickup());

    }

    @Override
    public PathChain launchToStartGoalSideBallPickup() {
        return launchToStartGoalSideBallPickup;
    }

    private PathChain buildStartGoalSideBallPickupToEndGoalSideBallPickup() {
        return pedroPathFlipper.flipPathChain(redPaths.startGoalSideBallPickupToEndGoalSideBallPickup());

    }

    @Override
    public PathChain startGoalSideBallPickupToEndGoalSideBallPickup() {
        return startGoalSideBallPickupToEndGoalSideBallPickup;
    }

    private PathChain buildEndGoalSideBallPickupToLaunch() {
        return pedroPathFlipper.flipPathChain(redPaths.endGoalSideBallPickupToLaunch());
    }

    @Override
    public PathChain endGoalSideBallPickupToLaunch() {
        return endGoalSideBallPickupToLaunch;
    }

    private PathChain buildLaunchToStartCentralSideBallPickup() {
        return pedroPathFlipper.flipPathChain(redPaths.launchToStartCentralSideBallPickup());
    }

    @Override
    public PathChain launchToStartCentralSideBallPickup() {
        return launchToStartCentralSideBallPickup;
    }

    private PathChain buildStartCentralSideBallPickupToEndCentralSideBallPickup() {
        return pedroPathFlipper.flipPathChain(redPaths.startCentralSideBallPickupToEndCentralSideBallPickup());

    }

    @Override
    public PathChain startCentralSideBallPickupToEndCentralSideBallPickup() {
        return startCentralSideBallPickupToEndCentralSideBallPickup;
    }

    private PathChain buildEndCentralSideBallPickupToLaunch() {
        return pedroPathFlipper.flipPathChain(redPaths.endCentralSideBallPickupToLaunch());
    }

    @Override
    public PathChain endCentralSideBallPickupToLaunch() {
        return endCentralSideBallPickupToLaunch;
    }

    private PathChain buildLaunchToStartAudienceSideBallPickup() {
        return pedroPathFlipper.flipPathChain(redPaths.launchToStartAudienceSideBallPickup());
    }

    @Override
    public PathChain launchToStartAudienceSideBallPickup() {
        return launchToStartAudienceSideBallPickup;
    }

    private PathChain buildStartAudienceSideBallPickupToEndAudienceSideBallPickup() {
        return pedroPathFlipper.flipPathChain(redPaths.startAudienceSideBallPickupToEndAudienceSideBallPickup());
    }

    @Override
    public PathChain startAudienceSideBallPickupToEndAudienceSideBallPickup() {
        return startAudienceSideBallPickupToEndAudienceSideBallPickup;
    }

    private PathChain buildEndAudienceSideBallPickupToLaunch() {
        return pedroPathFlipper.flipPathChain(redPaths.endAudienceSideBallPickupToLaunch());
    }

    @Override
    public PathChain endAudienceSideBallPickupToLaunch() {
        return endAudienceSideBallPickupToLaunch;
    }
}
