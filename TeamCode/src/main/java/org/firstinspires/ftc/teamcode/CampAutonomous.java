package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.paths.Path;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teamPedroPathing.PedroPathTelemetry;
import org.lhssa.ftc.teamcode.pedroPathing.AllianceColor;
import org.lhssa.ftc.teamcode.pedroPathing.HeadingInterpolationType;
import org.lhssa.ftc.teamcode.pedroPathing.PedroMotion;
import org.lhssa.ftc.teamcode.pedroPathing.PedroPather;

import java.util.List;


@Autonomous
public class CampAutonomous extends OpMode {
    private RobotHardware robotHardware;
    private Follower follower;
    private Limelight3A limelight;
    private int gameNum;
    private int actionStep = 0;
    private PedroMotion pedroMotion;
    private PedroPather pedroPather;
    private PedroPathTelemetry pedroPathTelemetry;

    @Override
    public void init() {
        PedroPathConfiguration pedroPathConfiguration = new PedroPathConfiguration(this);
        follower = pedroPathConfiguration.getFollower();
        robotHardware = new RobotHardware(this);
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(1);
        limelight.start();
        follower.setStartingPose(TeamPoses.startPose);
        pedroMotion = new PedroMotion(follower);
        pedroPather = new PedroPather(AllianceColor.RED, AllianceColor.RED);
        pedroPathTelemetry = new PedroPathTelemetry(telemetry, follower, org.firstinspires.ftc.teamcode.AllianceColor.RED);
    }

    public void init_loop() {
        follower.update();
        pedroPathTelemetry.pathTelemetry("tagNumber is"+ gameNum);
        LLResult llResult = limelight.getLatestResult();
        if (llResult.isValid()) {
            telemetry.addLine("results are valid");
            List<LLResultTypes.FiducialResult> results = llResult.getFiducialResults();
            if (!results.isEmpty()) {
                telemetry.addLine("results are not empty ");
                gameNum = results.get(0).getFiducialId();
            }
        }
    }

    @Override
    public void loop() {
        pedroPathTelemetry.pathTelemetry("");
        switch (gameNum) {
            case 21:
                performLeftActions();
                break;
            case 22:
                performRightActions();
                break;
            case 23:
                    performCornerActions();
                    break;
        }
        follower.update();
    }

    private void performLeftActions() {
        Path path;
        switch (actionStep) {
            case 0:
                path = pedroPather.pathBetween(TeamPoses.startPose, TeamPoses.startLeftPollenPickupPose);
                pedroMotion.goPath(path);
                if (pedroMotion.isPathComplete()) {
                    actionStep = actionStep + 1;
                }
                break;
            case 1:
                robotHardware.intakeOn();
                path = pedroPather.pathBetween(TeamPoses.startLeftPollenPickupPose, TeamPoses.endLeftPollenPickupPose, HeadingInterpolationType.TANGENT);
                pedroMotion.goPath(path, 0.5);
                if (pedroMotion.isPathComplete()) {
                    actionStep = actionStep + 1;
                    robotHardware.intakeOff();
                }
                break;
            case 2:
                path = pedroPather.pathBetween(TeamPoses.endLeftPollenPickupPose, TeamPoses.flowerPose);
                pedroMotion.goPath(path);
                if (pedroMotion.isPathComplete()) {
                    actionStep = actionStep + 1;
                    //robotHardware.liftPollen();
                }
                break;

        }

    }

    private void performRightActions() {
        Path path;
        switch (actionStep) {
            case 0:
                path = pedroPather.pathBetween(TeamPoses.startPose, TeamPoses.startRightPollenPickupPose);
                pedroMotion.goPath(path);
                if (pedroMotion.isPathComplete()) {
                    actionStep = actionStep + 1;
                }
                break;
            case 1:
                robotHardware.intakeOn();
                path = pedroPather.pathBetween(TeamPoses.startLeftPollenPickupPose, TeamPoses.endRightPollenPickupPose, HeadingInterpolationType.TANGENT);
                pedroMotion.goPath(path, 0.2);
                if (pedroMotion.isPathComplete()) {
                    actionStep = actionStep + 1;
                    robotHardware.intakeOff();
                }
                break;
            case 2:
                path = pedroPather.pathBetween(TeamPoses.endLeftPollenPickupPose, TeamPoses.flowerPose);
                pedroMotion.goPath(path);
                if (pedroMotion.isPathComplete()) {
                    actionStep = actionStep + 1;
                    //robotHardware.liftPollen();
                }
                break;
        }
    }
    private void performCornerActions()
    {
        Path path;
        switch (actionStep) {
            case 0:
                path = pedroPather.pathBetween(TeamPoses.startPose, TeamPoses.startCornerPollenPickupPose);
                pedroMotion.goPath(path);
                if (pedroMotion.isPathComplete()) {
                    actionStep = actionStep + 1;
                }
                break;
            case 1:
                robotHardware.intakeOn();
                path = pedroPather.pathBetween(TeamPoses.startCornerPollenPickupPose, TeamPoses.endCornerPollenPickupPose, HeadingInterpolationType.TANGENT);
                pedroMotion.goPath(path, 0.5);
                if (pedroMotion.isPathComplete()) {
                    actionStep = actionStep + 1;
                    robotHardware.intakeOff();
                }
                break;
            case 2:
                path = pedroPather.pathBetween(TeamPoses.endCornerPollenPickupPose, TeamPoses.flowerPose);
                pedroMotion.goPath(path);
                if (pedroMotion.isPathComplete()) {
                    actionStep = actionStep + 1;
                    //robotHardware.liftPollen();
                }
                break;
        }
    }
}
