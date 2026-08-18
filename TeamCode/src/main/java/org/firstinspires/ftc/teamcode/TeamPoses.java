package org.firstinspires.ftc.teamcode;

import com.pedropathing.geometry.Pose;

public class TeamPoses {
    public static Pose startPose = new Pose(72, 8, Math.toRadians (90));
    public static Pose startLeftPollenPickupPose = new Pose (24, 48, Math.toRadians(90));
    public static Pose endLeftPollenPickupPose = new Pose (24, 96, Math.toRadians(90));
    public static Pose flowerPose = new Pose (72,120, Math.toRadians(-90));
    public static Pose startRightPollenPickupPose = new Pose (108, 72, Math.toRadians(0));
    public static Pose endRightPollenPickupPose = new Pose(120, 72, Math.toRadians(0));
    public static Pose startCornerPollenPickupPose = new Pose(120, 8, Math.toRadians(0));
    public static Pose endCornerPollenPickupPose= new Pose(130, 8, Math.toRadians(0));
}
