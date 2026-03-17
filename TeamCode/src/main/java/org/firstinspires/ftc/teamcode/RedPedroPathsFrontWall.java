package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.teamPedroPathing.FlippablePath;

public class RedPedroPathsFrontWall implements PedroPathsFrontWall {

    private final Follower follower;

    // ---------- START POSES ----------
    private final Pose frontWallStart = new Pose(87.5, 7.17, Math.toRadians(90));
    private final Pose goalSideStart  = new Pose(129.014, 126.977, Math.toRadians(35));

    // ---------- LAUNCH ----------
    private final Pose launchPose = new Pose(90, 90, Math.toRadians(43));

    // ---------- BALL PICKUP ----------
    private final Pose startGoalSideBallPickupPose = new Pose(96, 83.5, 0);
    private final Pose endGoalSideBallPickupPose   = new Pose(125, 83.5, 0);

    private final Pose startCentralSidePickupPose = new Pose(96, 60, 0);
    private final Pose endCentralSidePickupPose   = new Pose(124, 60, 0);

    private final Pose startAudienceSidePickupPose = new Pose(96, 35, 0);
    private final Pose endAudienceSidePickupPose   = new Pose(130, 35, 0);


    // ---------- PATHS ----------
    private PathChain frontStartToLaunch;
    private PathChain goalStartToLaunch;

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
        frontStartToLaunch = buildStartToLaunch(frontWallStart);
        goalStartToLaunch  = buildStartToLaunch(goalSideStart);

        launchToStartGoalSideBallPickup = buildPath(launchPose, startGoalSideBallPickupPose);
        startGoalSideBallPickupToEndGoalSideBallPickup = buildPath(startGoalSideBallPickupPose, endGoalSideBallPickupPose);
        endGoalSideBallPickupToLaunch = buildPath(endGoalSideBallPickupPose, launchPose);

        launchToStartCentralSideBallPickup = buildPath(launchPose, startCentralSidePickupPose);
        startCentralSideBallPickupToEndCentralSideBallPickup = buildPath(startCentralSidePickupPose, endCentralSidePickupPose);
        endCentralSideBallPickupToLaunch = buildPath(endCentralSidePickupPose, launchPose);

        launchToStartAudienceSideBallPickup = buildPath(launchPose, startAudienceSidePickupPose);
        startAudienceSideBallPickupToEndAudienceSideBallPickup = buildPath(startAudienceSidePickupPose, endAudienceSidePickupPose);
        endAudienceSideBallPickupToLaunch = buildPath(endAudienceSidePickupPose, launchPose);
    }

    private PathChain buildStartToLaunch(Pose start) {
        FlippablePath fPath = FlippablePath.linearHeadingPath(
                new BezierLine(start, launchPose),
                start.getHeading(),
                launchPose.getHeading()
        );
        return follower.pathBuilder().addPath(fPath).build();
    }

    private PathChain buildPath(Pose a, Pose b) {
        FlippablePath fPath = FlippablePath.linearHeadingPath(
                new BezierLine(a, b),
                a.getHeading(),
                b.getHeading()
        );
        return follower.pathBuilder().addPath(fPath).build();
    }

    // ---------- INTERFACE IMPLEMENTATION ----------
    @Override public Pose startFrontPose() { return frontWallStart; }
    @Override public Pose startGoalPose()  { return goalSideStart; }

    @Override public PathChain startFrontToLaunchPath() { return frontStartToLaunch; }
    @Override public PathChain startGoalToLaunchPath()  { return goalStartToLaunch; }

    @Override public PathChain launchToStartGoalSideBallPickup() { return launchToStartGoalSideBallPickup; }
    @Override public PathChain startGoalSideBallPickupToEndGoalSideBallPickup() { return startGoalSideBallPickupToEndGoalSideBallPickup; }
    @Override public PathChain endGoalSideBallPickupToLaunch() { return endGoalSideBallPickupToLaunch; }

    @Override public PathChain launchToStartCentralSideBallPickup() { return launchToStartCentralSideBallPickup; }
    @Override public PathChain startCentralSideBallPickupToEndCentralSideBallPickup() { return startCentralSideBallPickupToEndCentralSideBallPickup; }
    @Override public PathChain endCentralSideBallPickupToLaunch() { return endCentralSideBallPickupToLaunch; }

    @Override public PathChain launchToStartAudienceSideBallPickup() { return launchToStartAudienceSideBallPickup; }
    @Override public PathChain startAudienceSideBallPickupToEndAudienceSideBallPickup() { return startAudienceSideBallPickupToEndAudienceSideBallPickup; }
    @Override public PathChain endAudienceSideBallPickupToLaunch() { return endAudienceSideBallPickupToLaunch; }
}
