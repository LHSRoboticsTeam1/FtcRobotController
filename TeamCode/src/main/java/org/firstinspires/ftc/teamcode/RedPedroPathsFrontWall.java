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
    private final Pose goalSideStart  = new Pose(123.193, 121.45, Math.toRadians(35));
    private final Pose parkPose = new Pose(39.486, 19.0, Math.toRadians(0));

    //Possible 2nd park pose for full?
    // ---------- LAUNCH ----------
    private final Pose shortLaunchPose = new Pose(80, 99, Math.toRadians(30));
private final Pose longLaunchPose = new Pose(78.9, 78, Math.toRadians(43));

    // ---------- BALL PICKUP ----------
    private final Pose startGoalSideBallPickupPose = new Pose(96, 83, 0);
    private final Pose endGoalSideBallPickupPose   = new Pose(125.2, 83, 0);

    private final Pose startCentralSidePickupPose = new Pose(96, 60,0);
    private final Pose endCentralSidePickupPose   = new Pose(127, 60,0);

    private final Pose startAudienceSidePickupPose = new Pose(96, 35.8, 0);
    private final Pose endAudienceSidePickupPose   = new Pose(130, 35.8, 0);


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

        launchToStartGoalSideBallPickup = buildPath(shortLaunchPose, startGoalSideBallPickupPose);
        startGoalSideBallPickupToEndGoalSideBallPickup = buildPath(startGoalSideBallPickupPose, endGoalSideBallPickupPose);
        endGoalSideBallPickupToLaunch = buildPath(endGoalSideBallPickupPose, shortLaunchPose);

        launchToStartCentralSideBallPickup = buildPath(shortLaunchPose, startCentralSidePickupPose);
        startCentralSideBallPickupToEndCentralSideBallPickup = buildPath(startCentralSidePickupPose, endCentralSidePickupPose);
        endCentralSideBallPickupToLaunch = buildPath(endCentralSidePickupPose, shortLaunchPose);

        launchToStartAudienceSideBallPickup = buildPath(shortLaunchPose, startAudienceSidePickupPose);
        startAudienceSideBallPickupToEndAudienceSideBallPickup = buildPath(startAudienceSidePickupPose, endAudienceSidePickupPose);
        endAudienceSideBallPickupToLaunch = buildPath(endAudienceSidePickupPose, shortLaunchPose);
    }

    private PathChain buildStartToLaunch(Pose start) {
        FlippablePath fPath = FlippablePath.linearHeadingPath(
                new BezierLine(start, shortLaunchPose),
                start.getHeading(),
                shortLaunchPose.getHeading()
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
    @Override public Pose parkPose() { return parkPose; }
    @Override public Pose shortLaunchPose(){ return shortLaunchPose;}
    @Override public Pose longLaunchPose(){ return longLaunchPose;}
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
