package org.firstinspires.ftc.teamcode;

import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public interface PedroPathsFrontWall {

    // ---------- START POSES ----------
    Pose startFrontPose();
    Pose startGoalPose();

    Pose parkPose();

    // ---------- START → LAUNCH ----------
    PathChain startFrontToLaunchPath();
    PathChain startGoalToLaunchPath();

    // ---------- LAUNCH → BALL PICKUP ----------
    PathChain launchToStartGoalSideBallPickup();
    PathChain startGoalSideBallPickupToEndGoalSideBallPickup();
    PathChain endGoalSideBallPickupToLaunch();

    PathChain launchToStartCentralSideBallPickup();
    PathChain startCentralSideBallPickupToEndCentralSideBallPickup();
    PathChain endCentralSideBallPickupToLaunch();

    PathChain launchToStartAudienceSideBallPickup();
    PathChain startAudienceSideBallPickupToEndAudienceSideBallPickup();
    PathChain endAudienceSideBallPickupToLaunch();
}
