package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class ShooterMotorRun extends OpMode {
    private DcMotor testshooter = null;

    @Override
    public void init() {
        testshooter = hardwareMap.get(DcMotor.class, "testshooter");
        testshooter.setDirection(DcMotorSimple.Direction.REVERSE);
        testshooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    @Override
    public void loop() {
            if (gamepad1.x) {
                testshooter.setPower(1);
            }
            else testshooter.setPower(0);
        }
    }
