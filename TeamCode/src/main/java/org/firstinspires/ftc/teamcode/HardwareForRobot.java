package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class HardwareForRobot {

    private LinearOpMode myOpMode;

    // ---- DRIVE MOTORS ----
    private DcMotor leftFrontWheel;
    private DcMotor rightFrontWheel;
    private DcMotor leftRearWheel;
    private DcMotor rightRearWheel;

    // ---- INTAKE / OUTTAKE ----
    public DcMotor intakeMotor;
    public DcMotor outtakeMotor;
    public DcMotor outtakeMotor2;

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
        leftFrontWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        leftRearWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFrontWheel.setDirection(DcMotorSimple.Direction.FORWARD);
        rightRearWheel.setDirection(DcMotorSimple.Direction.FORWARD);

        leftFrontWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRearWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRearWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    // ---------------- INTAKE / OUTTAKE ----------------
    private void initIntakeOuttake() {
        intakeMotor   = myOpMode.hardwareMap.get(DcMotor.class, "intake");
        outtakeMotor  = myOpMode.hardwareMap.get(DcMotor.class, "outtake");
        outtakeMotor2 = myOpMode.hardwareMap.get(DcMotor.class, "outtake2");

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
    public void manuallyDriveRobot(double x, double y, double turn) {
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(turn), 1);

        double lf = (y + x + turn) / denominator;
        double rf = (y - x - turn) / denominator;
        double lr = (y - x + turn) / denominator;
        double rr = (y + x - turn) / denominator;

        leftFrontWheel.setPower(lf);
        rightFrontWheel.setPower(rf);
        leftRearWheel.setPower(lr);
        rightRearWheel.setPower(rr);
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
        outtakeMotor.setPower(1);
        outtakeMotor2.setPower(1);
    }

    public void outtakeStop() {
        outtakeMotor.setPower(0);
        outtakeMotor2.setPower(0);
    }
}
