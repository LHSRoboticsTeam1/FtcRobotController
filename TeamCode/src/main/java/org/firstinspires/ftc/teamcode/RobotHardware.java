
package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

public class RobotHardware {
    private Servo rotationTubeServo;
    private OpMode myOpMode;

    public DcMotor leftLift;
    public int leftEncoderCount;

    public final int fullExtensionCount = 30320;
    public final double power = 1;

    // ---------------- LIFT SENSOR ----------------
    DigitalChannel digitalTouch;

    // ---- INTAKE / OUTTAKE ----
    private DcMotorEx intakeMotor;


    public RobotHardware(OpMode opmode) {
        myOpMode = opmode;
        init();
    }

    private void init() {
        initIntakeOuttake();
        initServos();
        initLift();
        initLiftBottom();
        myOpMode.telemetry.addData(">", "Hardware Initialized");
        myOpMode.telemetry.update();
    }

    private void initLift(){
    leftLift = myOpMode.hardwareMap.get(DcMotor.class, "leftLift"); // Name Placeholder
       leftLift.setDirection(DcMotorSimple.Direction.REVERSE);
      leftEncoderCount = leftLift.getCurrentPosition();
    }

    // ---------------- INTAKE / OUTTAKE ----------------
    private void initIntakeOuttake() {
        intakeMotor   = myOpMode.hardwareMap.get(DcMotorEx.class, "intake");


        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    // ---------------- SERVOS ----------------
    private void initServos(){
        rotationTubeServo = myOpMode.hardwareMap.get(Servo.class, "rotationalTubeServo");
    }
    public void tiltTube(){
        rotationTubeServo.setPosition(1) ;
    }
    public void vertTube(){
        rotationTubeServo.setPosition(0.5) ;
    }


    public void initLiftBottom(){
        digitalTouch = myOpMode.hardwareMap.get(DigitalChannel.class, "liftBottom");

        digitalTouch.setMode(DigitalChannel.Mode.INPUT);

    }

    public void liftDown () {
        //myOpMode.telemetry.addData("leftEncoderCount",leftEncoderCount);
        int leftTargetPos = fullExtensionCount - leftEncoderCount;

//        myOpMode.telemetry.addData("leftTargetPos", leftTargetPos);
//        myOpMode.telemetry.update();

        leftLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftLift.setPower(-power);

        while(digitalTouch.getState()) {
           myOpMode.telemetry.addLine("Running slide to bottom");
           myOpMode.telemetry.update();
        }
        
        leftLift.setPower(0);
        myOpMode.telemetry.addData("should be", "bottom");
        myOpMode.telemetry.update();
    }
    public void liftUp () {
        leftEncoderCount = leftLift.getCurrentPosition();
        leftLift.setTargetPosition(leftEncoderCount +fullExtensionCount);
        leftLift.setPower(power);
    }



    // ---------------- INTAKE ----------------
    public void intakeOn() {
        intakeMotor.setPower(1);
    }
    public void outtakeOn(){
        intakeMotor.setPower(-1);
    }
    public void intakeOff() {
        intakeMotor.setPower(0);
    }

    // ---------------- BELTS ----------------


    // ---------------- OUTTAKE ----------------

}


