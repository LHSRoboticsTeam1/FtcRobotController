package org.firstinspires.ftc.teamcode;




import com.pedropathing.follower.Follower;
import com.pedropathing.utils.Timer;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.teamPedroPathing.Constants;
import org.firstinspires.ftc.teamcode.teamPedroPathing.TeamPoses;
import org.lhssa.ftc.teamcode.pedroPathing.AllianceColor;
import org.lhssa.ftc.teamcode.pedroPathing.HeadingInterpolationType;
import org.lhssa.ftc.teamcode.pedroPathing.PedroMotion;
import org.lhssa.ftc.teamcode.pedroPathing.PedroPathTelemetry;
import org.lhssa.ftc.teamcode.pedroPathing.PedroPather;


@Autonomous
public class BiobuzzAutonomousMk1 extends OpMode{

    private StartingSide startingSide = StartingSide.HIVEUP;
    private AllianceColor allianceColor = AllianceColor.RED;

    private RobotHardware robotHardware;
    private String telemetryMessage;
    private PedroPather pedroPather;
    private PedroMotion pedroMotion;
    private Follower follower;
    private Limelight3A limelight;
    private int currentAction = 0;

    private PedroPathTelemetry pedroPathTelemetry;


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
        limelight.start();
        pedroPathTelemetry = new PedroPathTelemetry(telemetry, follower, allianceColor);
        pedroPather = new PedroPather(TeamPoses.canonicalColor, allianceColor, PedroPather::FlipRoutineAlliancesOnOppositeSides);

        parkTimer.reset();
        follower.setPose(TeamPoses.startShootPose);
    }

    @Override
    public void loop() {
        LLResult llResult = limelight.getLatestResult();
        follower.update();
        pedroPathTelemetry.pathTelemetry(telemetryMessage);
        if(timeToPark()){
            pedroMotion.goPath(pedroPather.pathBetween(follower.pose(), TeamPoses.parkPose, HeadingInterpolationType.LINEAR));
            if(pedroMotion.isPathComplete()){
                requestOpModeStop();
            }
        }
        switch (currentAction) {
            case 0:
                telemetryMessage = "Shooting";
                if(shootUntilComplete()){
                    currentAction = 1;
                }
                break;
            case 1:
                telemetryMessage = "transfer Off";
                robotHardware.transferOff();
                currentAction = 2;
                break;
            case 2:
                telemetryMessage = "Going to Garden";
                pedroMotion.goPath(pedroPather.pathBetween(HeadingInterpolationType.LINEAR,
                        TeamPoses.startShootPose,
                        TeamPoses.midGardenPose,
                        TeamPoses.endGardenPose
                ));
                if (pedroMotion.isPathComplete()) {
                    currentAction = 4;
                }
                break;
//case 3 is skipped
            case 3:
                robotHardware.intakeOff();
                currentAction = 4;
                break;

            case 4:
                telemetryMessage = "Going to Shooting";
                pedroMotion.goPath(pedroPather.pathBetween(HeadingInterpolationType.LINEAR,
                        TeamPoses.endGardenPose,
                        TeamPoses.midGardenPose,
                        TeamPoses.startShootPose
                ));

                if (pedroMotion.isPathComplete()){
                    currentAction = 5 ;
                }

                break;
            case 5:
                telemetryMessage = "waiting for hive up";
                if (llResult.isValid()){
                    shooterTimer.reset();
                        currentAction = 6;
                }
                break;
            case 6:
                telemetryMessage = "Shooting";
                if(shootUntilComplete()){
                    currentAction = 7;
                }
                break;
            case 7:
                telemetryMessage = "Going to Park";
                pedroMotion.goPath(pedroPather.pathBetween(
                        HeadingInterpolationType.TANGENT,
                        TeamPoses.startShootPose,
                        TeamPoses.midParkPose,
                        TeamPoses.parkPose
                        ));
                robotHardware.intakeOff();
                robotHardware.transferOff();
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
        boolean isShootTime;
        robotHardware.shooterON();
        isShootTime = shooterTimer.milliseconds() > 1000;
        isComplete = shooterTimer.milliseconds() > 4000;
        if (isShootTime){
            robotHardware.intakeOn();
            robotHardware.transferOn();
        }
        if(isComplete){
            robotHardware.shooterOFF();
        }
        return isComplete;

    }
    private boolean timeToPark(){
        boolean isTime = false;
        isTime = parkTimer.seconds() > 25;
        return isTime;
    }
}