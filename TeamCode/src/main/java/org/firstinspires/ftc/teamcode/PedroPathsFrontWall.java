package org.firstinspires.ftc.teamcode;

import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public interface PedroPathsFrontWall {
    PathChain startToLaunchPath();

    public Pose startingPose();
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
