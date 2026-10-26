package org.firstinspires.ftc.teamcode;



import static org.lhssa.ftc.teamcode.pedroPathing.PedroTeleopData.startingPose;

import com.pedropathing.follower.Follower;
import com.pedropathing.paths.Path;
import com.pedropathing.utils.Timer;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teamPedroPathing.Constants;
import org.firstinspires.ftc.teamcode.teamPedroPathing.TeamPoses;
import org.lhssa.ftc.teamcode.pedroPathing.AllianceColor;
import org.lhssa.ftc.teamcode.pedroPathing.HeadingInterpolationType;
import org.lhssa.ftc.teamcode.pedroPathing.PedroMotion;
import org.lhssa.ftc.teamcode.pedroPathing.PedroPathData;
import org.lhssa.ftc.teamcode.pedroPathing.PedroPather;


@Disabled
@TeleOp
public class BiobuzzAutonomousmk1 extends OpMode {

    private StartingSide startingSide = StartingSide.HIVEUP;
    private AllianceColor allianceColor = AllianceColor.RED;

    private RobotHardware robotHardware;
    private PedroPather pedroPather;
    private PedroMotion pedroMotion;
    private Follower follower;
    private Limelight3A limelight;
    private int currentAction = 0;

    private Timer shooterTimer;

    @Override
    public void init() {
        robotHardware = new RobotHardware(this);
        follower = Constants.create(hardwareMap);
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        pedroMotion = new PedroMotion(follower);


    }

    @Override
    public void init_loop() {
        menu();
    }

    public void start() {
        limelight.pipelineSwitch(allianceColor == AllianceColor.BLUE ? 99 : 98);
        pedroPather = new PedroPather(TeamPoses.canonicalColor, allianceColor, PedroPather::FlipRoutineAlliancesOnOppositeSides);
    }

    @Override
    public void loop() {
        PedroPathData startToGarden = pedroPather.pathBetween(TeamPoses.startShootPose, TeamPoses.startGardenPose, HeadingInterpolationType.LINEAR);
        PedroPathData garden = pedroPather.pathBetween(TeamPoses.startGardenPose, TeamPoses.endGardenPose, HeadingInterpolationType.LINEAR);
        PedroPathData gardenToPark = pedroPather.pathBetween(TeamPoses.startGardenPose, TeamPoses.endGardenPose, HeadingInterpolationType.LINEAR);
        telemetry.clear();
        telemetry.addData("this is the currently selected alliance", allianceColor.toString());
        telemetry.addData("this is the currently selected side", startingSide.toString());
        //TODO: This might not work check in with Alwood
        switch (currentAction) {
            case 0:
                robotHardware.shooterON();
                if(shootUntilComplete()){

                    currentAction = 1;
                }
                break;
            case 1:
                robotHardware.intakeOn();
                currentAction = 2;
                break;
            case 2:
                follower.update();
                pedroMotion.goPath(startToGarden);
                if (pedroMotion.isPathComplete()) {
                    currentAction = 3;
                }


            case 3:
                pedroMotion.goPath(garden);
                currentAction = 4;
            case 4:
                robotHardware.intakeOff();
                currentAction = 5;
                break;
            case 5:
                pedroMotion.goPath(gardenToPark);
                currentAction = 6;
                break;
        }
    }

    private void menu() {
        telemetry.addData("what alliance?", "UpDpad to toggle alliance");
        telemetry.addData("this is the currently selected alliance", allianceColor.toString());
        telemetry.addData("what side are you on", "DownDpad to toggle Side");
        telemetry.addData("this is the currently selected side ", startingSide.toString());

        telemetry.update();

        allianceColor = AllianceColor.INSTANCE.toggleColor(gamepad1.dpadUpWasPressed(), allianceColor);
        startingSide = StartingSide.INSTANCE.toggleSide(gamepad1.dpadDownWasPressed(), startingSide);
    }

    private boolean shootUntilComplete() {
        boolean isComplete;
        robotHardware.shooterON();

        isComplete = shooterTimer.milliseconds() > 3000;
        if(isComplete){
            robotHardware.shooterOFF();
        }
        return isComplete;

    }
}
