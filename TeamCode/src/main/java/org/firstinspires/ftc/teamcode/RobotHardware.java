
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class RobotHardware {

    private OpMode myOpMode;

    // ---- INTAKE / OUTTAKE ----
    public DcMotorEx intakeMotor;


    public RobotHardware(OpMode opmode) {
        myOpMode = opmode;
        init();
    }

    private void init() {

        initIntakeOuttake();


        myOpMode.telemetry.addData(">", "Hardware Initialized");
        myOpMode.telemetry.update();
    }

    // ---------------- DRIVE ----------------

    // ---------------- INTAKE / OUTTAKE ----------------
    private void initIntakeOuttake() {
        intakeMotor   = myOpMode.hardwareMap.get(DcMotorEx.class, "intake");


        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    // ---------------- SERVOS ----------------


    // ---------------- DRIVE FUNCTION ----------------


    // ---------------- INTAKE ----------------
    public void intakeOn() {
        intakeMotor.setPower(1);
    }
    public void outakeOn(){
        intakeMotor.setPower(-1);
    }
    public void intakeOff() {
        intakeMotor.setPower(0);
    }

    // ---------------- BELTS ----------------


    // ---------------- OUTTAKE ----------------

}


