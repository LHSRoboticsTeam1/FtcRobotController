package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class HardwareForRobot {

    private LinearOpMode myOpMode;

    // ---- DRIVE MOTORS ----
    private DcMotor leftFrontWheel;
    private DcMotor rightFrontWheel;
    private DcMotor leftRearWheel;
    private DcMotor rightRearWheel;

    // ---- INTAKE / OUTTAKE ----
    public DcMotorEx intakeMotor;
    public DcMotorEx outtakeMotor;
    public DcMotorEx outtakeMotor2;

    // ---- CONVEYORS ----
    public CRServo leftConveyor;
    public CRServo rightConveyor;

    // ---- LIFT BELTS ----
    public CRServo liftBeltLeft;
    public CRServo liftBeltRight;

    public HardwareForRobot(LinearOpMode opmode) {
        myOpMode = opmode;
    }

    public void init() {
        initDriveMotors();
        initIntakeOuttake();
        initServos();

        myOpMode.telemetry.addData(">", "Hardware Initialized");
        myOpMode.telemetry.update();
    }

    // ---------------- DRIVE ----------------
    private void initDriveMotors() {
        leftFrontWheel  = myOpMode.hardwareMap.get(DcMotor.class, "LeftFront");
        rightFrontWheel = myOpMode.hardwareMap.get(DcMotor.class, "FrontRight");
        leftRearWheel   = myOpMode.hardwareMap.get(DcMotor.class, "LeftBack");
        rightRearWheel  = myOpMode.hardwareMap.get(DcMotor.class, "BackRight");

        // FIXED → proper mecanum directions
        leftFrontWheel.setDirection(DcMotorSimple.Direction.FORWARD);
         leftRearWheel.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFrontWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRearWheel.setDirection(DcMotorSimple.Direction.REVERSE);

       // leftFrontWheel.setDirection(DcMotorSimple.Direction.REVERSE);
       // leftRearWheel.setDirection(DcMotorSimple.Direction.REVERSE);
       // rightFrontWheel.setDirection(DcMotorSimple.Direction.FORWARD);
       // rightRearWheel.setDirection(DcMotorSimple.Direction.FORWARD);


        leftFrontWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightFrontWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        leftRearWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightRearWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        leftFrontWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRearWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRearWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    // ---------------- INTAKE / OUTTAKE ----------------
    private void initIntakeOuttake() {
        intakeMotor   = myOpMode.hardwareMap.get(DcMotorEx.class, "intake");
        outtakeMotor  = myOpMode.hardwareMap.get(DcMotorEx.class, "outtake");
        outtakeMotor2 = myOpMode.hardwareMap.get(DcMotorEx.class, "outtake2");

        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        outtakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        outtakeMotor2.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    // ---------------- SERVOS ----------------
    private void initServos() {

        leftConveyor  = myOpMode.hardwareMap.get(CRServo.class, "LeftConveyor");
        rightConveyor = myOpMode.hardwareMap.get(CRServo.class, "RightConveyor");

        leftConveyor.setDirection(CRServo.Direction.FORWARD);
        rightConveyor.setDirection(CRServo.Direction.REVERSE);

        liftBeltLeft  = myOpMode.hardwareMap.get(CRServo.class, "LiftBeltLeft");
        liftBeltRight = myOpMode.hardwareMap.get(CRServo.class, "LiftBeltRight");

        // Belts spin the SAME direction


        liftBeltLeft.setDirection(CRServo.Direction.REVERSE);
        liftBeltRight.setDirection(CRServo.Direction.FORWARD);
    }

    // ---------------- DRIVE FUNCTION ----------------
    public void manuallyDriveRobot(double stick1x, double stick1y, double stick2x) {
        double vectorLength = Math.hypot(stick1x, stick1y);
        double robotAngle = Math.atan2(stick1y, -stick1x) - Math.PI / 4;
        double rightXscale = stick2x * .5;
        double rightFrontVelocity = vectorLength * Math.cos(robotAngle) + rightXscale;
        double leftFrontVelocity = vectorLength * Math.sin(robotAngle) - rightXscale;
        double rightRearVelocity = vectorLength * Math.sin(robotAngle) + rightXscale;
        double leftRearVelocity = vectorLength * Math.cos(robotAngle) - rightXscale;

        leftFrontWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRearWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRearWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFrontWheel.setPower(leftFrontVelocity);
        rightFrontWheel.setPower(rightFrontVelocity);
        leftRearWheel.setPower(leftRearVelocity);
        rightRearWheel.setPower(rightRearVelocity);
    }

    // ---------------- INTAKE SYSTEM ----------------
    public void intakeOn() {
        intakeMotor.setPower(1);
        leftConveyor.setPower(1);
        rightConveyor.setPower(1);
    }

    public void intakeOff() {
        intakeMotor.setPower(0);
        leftConveyor.setPower(0);
        rightConveyor.setPower(0);
    }

    // ---------------- BELTS ----------------
    public void liftBeltsUp() {
        liftBeltLeft.setPower(-1);
        liftBeltRight.setPower(-1);
    }

    public void liftBeltsStop() {
        liftBeltLeft.setPower(0);
        liftBeltRight.setPower(0);
    }

    public void liftItUp() {
        liftBeltLeft.setPower(1);
        liftBeltRight.setPower(1);
    }

    public void stopLiftItUp() {
        liftBeltLeft.setPower(0);
        liftBeltRight.setPower(0);
    }

    // ---------------- OUTTAKE ----------------
    public void outtakeShoot() {
        double vel = 0.5 * 2800;
        outtakeMotor.setVelocity(vel);
        outtakeMotor2.setVelocity(vel);
    }

    public void outtakeStop() {
        outtakeMotor.setPower(0);
        outtakeMotor2.setPower(0);
    }
}

