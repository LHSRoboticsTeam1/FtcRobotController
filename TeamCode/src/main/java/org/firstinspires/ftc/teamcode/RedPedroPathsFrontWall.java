package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.teamPedroPathing.FlippablePath;

public class RedPedroPathsFrontWall implements PedroPathsFrontWall {
    private final Follower follower;

    private final Pose startingPose = new Pose(87.5, 7.17, Math.toRadians(90));
    private final Pose launchPose = new Pose(90, 90, Math.toRadians(45));

    private final Pose startGoalSideBallPickupPose = new Pose(100, 83.5, 0);
    private final Pose endGoalSideBallPickupPose = new Pose(125, 83.5, 0);

    private final Pose startCentralSidePickupPose = new Pose(106, 60, 0);
    private final Pose endCentralSidePickupPose = new Pose(130, 60, 0);

    private final Pose startAudienceSidePickupPose = new Pose(106, 35, 0);
    private final Pose endAudienceSidePickupPose = new Pose(130, 35, 0);

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

    public RedPedroPathsFrontWall(Follower follower) {
        this.follower = follower;
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
        FlippablePath fPath = FlippablePath.linearHeadingPath(
                new BezierLine(startingPose, launchPose),
                startingPose.getHeading(),
                launchPose.getHeading()
        );

        return follower.pathBuilder()
                .addPath(fPath)
                .build();
    }

    @Override
    public Pose startingPose() {
        return startingPose;
    }

    @Override
    public PathChain startToLaunchPath() {
        return startToLaunchPath;
    }

    private PathChain buildLaunchToStartGoalSideBallPickup() {
        FlippablePath fPath = FlippablePath.linearHeadingPath(
                new BezierLine(launchPose, startGoalSideBallPickupPose),
                launchPose.getHeading(),
                startGoalSideBallPickupPose.getHeading()
        );

        return follower.pathBuilder()
                .addPath(fPath)
                .build();
    }

    @Override
    public PathChain launchToStartGoalSideBallPickup() {
        return launchToStartGoalSideBallPickup;
    }

    private PathChain buildStartGoalSideBallPickupToEndGoalSideBallPickup() {
        FlippablePath fPath = FlippablePath.linearHeadingPath(
                new BezierLine(startGoalSideBallPickupPose, endGoalSideBallPickupPose),
                startGoalSideBallPickupPose.getHeading(),
                endGoalSideBallPickupPose.getHeading()
        );

        return follower.pathBuilder()
                .addPath(fPath)
                .build();
    }

    @Override
    public PathChain startGoalSideBallPickupToEndGoalSideBallPickup() {
        return startGoalSideBallPickupToEndGoalSideBallPickup;
    }

    private PathChain buildEndGoalSideBallPickupToLaunch() {
        FlippablePath fPath = FlippablePath.linearHeadingPath(
                new BezierLine(endGoalSideBallPickupPose, launchPose),
                endGoalSideBallPickupPose.getHeading(),
                launchPose.getHeading()
        );

        return follower.pathBuilder()
                .addPath(fPath)
                .build();
    }

    @Override
    public PathChain endGoalSideBallPickupToLaunch() {
        return endGoalSideBallPickupToLaunch;
    }

    private PathChain buildLaunchToStartCentralSideBallPickup() {
        FlippablePath fPath = FlippablePath.linearHeadingPath(
                new BezierLine(launchPose, startCentralSidePickupPose),
                launchPose.getHeading(),
                startCentralSidePickupPose.getHeading()
        );

        return follower.pathBuilder()
                .addPath(fPath)
                .build();
    }

    @Override
    public PathChain launchToStartCentralSideBallPickup() {
        return launchToStartCentralSideBallPickup;
    }

    private PathChain buildStartCentralSideBallPickupToEndCentralSideBallPickup() {
        FlippablePath fPath = FlippablePath.linearHeadingPath(
                new BezierLine(startCentralSidePickupPose, endCentralSidePickupPose),
                startCentralSidePickupPose.getHeading(),
                endCentralSidePickupPose.getHeading()
        );

        return follower.pathBuilder()
                .addPath(fPath)
                .build();
    }

    @Override
    public PathChain startCentralSideBallPickupToEndCentralSideBallPickup() {
        return startCentralSideBallPickupToEndCentralSideBallPickup;
    }

    private PathChain buildEndCentralSideBallPickupToLaunch() {
        FlippablePath fPath = FlippablePath.linearHeadingPath(
                new BezierLine(endCentralSidePickupPose, launchPose),
                endCentralSidePickupPose.getHeading(),
                launchPose.getHeading()
        );

        return follower.pathBuilder()
                .addPath(fPath)
                .build();
    }

    @Override
    public PathChain endCentralSideBallPickupToLaunch() {
        return endCentralSideBallPickupToLaunch;
    }

    private PathChain buildLaunchToStartAudienceSideBallPickup() {
        FlippablePath fPath = FlippablePath.linearHeadingPath(
                new BezierLine(launchPose, startAudienceSidePickupPose),
                launchPose.getHeading(),
                startAudienceSidePickupPose.getHeading()
        );

        return follower.pathBuilder()
                .addPath(fPath)
                .build();
    }

    @Override
    public PathChain launchToStartAudienceSideBallPickup() {
        return launchToStartAudienceSideBallPickup;
    }

    private PathChain buildStartAudienceSideBallPickupToEndAudienceSideBallPickup() {
        FlippablePath fPath = FlippablePath.linearHeadingPath(
                new BezierLine(startAudienceSidePickupPose, endAudienceSidePickupPose),
                startAudienceSidePickupPose.getHeading(),
                endAudienceSidePickupPose.getHeading()
        );

        return follower.pathBuilder()
                .addPath(fPath)
                .build();
    }

    @Override
    public PathChain startAudienceSideBallPickupToEndAudienceSideBallPickup() {
        return startAudienceSideBallPickupToEndAudienceSideBallPickup;
    }

    private PathChain buildEndAudienceSideBallPickupToLaunch() {
        FlippablePath fPath = FlippablePath.linearHeadingPath(
                new BezierLine(endAudienceSidePickupPose, launchPose),
                endAudienceSidePickupPose.getHeading(),
                launchPose.getHeading()
        );

        return follower.pathBuilder()
                .addPath(fPath)
                .build();
    }

    @Override
    public PathChain endAudienceSideBallPickupToLaunch() {
        return endAudienceSideBallPickupToLaunch;
    }
}
