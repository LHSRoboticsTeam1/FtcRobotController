package org.firstinspires.ftc.teamcode;




import com.pedropathing.follower.Follower;
import com.pedropathing.utils.Timer;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teamPedroPathing.Constants;
import org.firstinspires.ftc.teamcode.teamPedroPathing.TeamPoses;
import org.lhssa.ftc.teamcode.pedroPathing.AllianceColor;
import org.lhssa.ftc.teamcode.pedroPathing.HeadingInterpolationType;
import org.lhssa.ftc.teamcode.pedroPathing.PedroMotion;
import org.lhssa.ftc.teamcode.pedroPathing.PedroPather;


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
    private Timer parkTimer;

    @Override
    public void init() {
        robotHardware = new RobotHardware(this);
        follower = Constants.create(hardwareMap);
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        pedroMotion = new PedroMotion(follower);
        parkTimer = new Timer();
    shooterTimer = new Timer();
    }

    @Override
    public void init_loop() {
        menu();
    }

    public void start() {
        limelight.pipelineSwitch(allianceColor == AllianceColor.BLUE ? 1 : 2);
        pedroPather = new PedroPather(TeamPoses.canonicalColor, allianceColor, PedroPather::FlipRoutineAlliancesOnOppositeSides);
        parkTimer.reset();
    }

    @Override
    public void loop() {
        LLResult llResult = limelight.getLatestResult();
        follower.update();
        telemetry.clear();
        telemetry.addData("this is the currently selected alliance", allianceColor.toString());
        telemetry.addData("this is the currently selected side", startingSide.toString());
        if(timeToPark()){
            pedroMotion.goPath(pedroPather.pathBetween(follower.pose(), TeamPoses.parkPose, HeadingInterpolationType.LINEAR));
            if(pedroMotion.isPathComplete()){
                requestOpModeStop();
            }
        }
        switch (currentAction) {
            case 0:
                if(shootUntilComplete()){


                    currentAction = 1;
                }
                break;
            case 1:
                robotHardware.intakeOn();
                currentAction = 2;
                break;
            case 2:
                pedroMotion.goPath(pedroPather.pathBetween(TeamPoses.startShootPose, TeamPoses.startGardenPose, HeadingInterpolationType.LINEAR));
                if (pedroMotion.isPathComplete()) {
                    currentAction = 3;
                }
                break;

            case 3:
                pedroMotion.goPath(pedroPather.pathBetween(TeamPoses.startGardenPose, TeamPoses.endGardenPose, HeadingInterpolationType.LINEAR), 0.5);
                if (pedroMotion.isPathComplete()) {
                    currentAction = 4;
                }
                break;
            case 4:
                robotHardware.intakeOff();
                currentAction = 5;
                break;
            case 5:
                pedroMotion.goPath(pedroPather.pathBetween(TeamPoses.startGardenPose, TeamPoses.startShootPose, HeadingInterpolationType.LINEAR));
                if (pedroMotion.isPathComplete()) {
                    currentAction = 6;
                }
                break;
            case 6:
                if (llResult.isValid()){
                    if(shootUntilComplete()){
                        currentAction = 7;

                    }
                } else {

                }


                break;
            case 7:
                pedroMotion.goPath(pedroPather.pathBetween(TeamPoses.startShootPose, TeamPoses.parkPose, HeadingInterpolationType.LINEAR));
                if(pedroMotion.isPathComplete()) {
                    currentAction = 8;
                }
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
    private boolean timeToPark(){
        boolean isTime = false;
        isTime = parkTimer.seconds() > 15;
        return isTime;
    }
}
