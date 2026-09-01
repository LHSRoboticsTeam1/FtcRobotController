package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
@Disabled
public class dualArmLift extends OpMode {
    private DcMotor leftLift;
    private DcMotor rightLift;
    private int leftEncoderCount;
    private int rightEncoderCount;
    private final int fullExtensionCount = 30320;
    private final double power = 1;

    @Override
    public void init() {
        leftLift = hardwareMap.get(DcMotor.class, "leftLift");
        rightLift = hardwareMap.get(DcMotor.class, "rightLift");
        leftLift.setDirection(DcMotorSimple.Direction.FORWARD);
        rightLift.setDirection(DcMotorSimple.Direction.REVERSE);
        leftEncoderCount = leftLift.getCurrentPosition();
        rightEncoderCount = leftLift.getCurrentPosition();
    }

    @Override
    public void loop() {
        if (gamepad1.aWasPressed()){
            liftUp();
        }
        else if (gamepad1.bWasPressed()){
            liftDown();
        }
    }
    private void liftUp () {
        telemetry.addData("leftEncoderCount", leftEncoderCount);
        telemetry.addData("rightEncoderCount", rightEncoderCount);
        int leftTargetPos = fullExtensionCount + leftEncoderCount;
        int rightTargetPos = fullExtensionCount + rightEncoderCount;
        leftLift.setTargetPosition(leftTargetPos);
        rightLift.setTargetPosition(fullExtensionCount + rightEncoderCount);

        telemetry.addData("leftTargetPos", leftTargetPos);
        telemetry.addData("rightTargetPos", rightTargetPos);
        leftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftLift.setPower(power);
        rightLift.setPower(power);
    }
    private void liftDown () {
        leftEncoderCount = leftLift.getCurrentPosition();
        rightEncoderCount = rightLift.getCurrentPosition();
        leftLift.setTargetPosition(leftEncoderCount - fullExtensionCount);
        rightLift.setTargetPosition(leftEncoderCount - fullExtensionCount);
        leftLift.setPower(power);
        rightLift.setPower(power);
    }
}