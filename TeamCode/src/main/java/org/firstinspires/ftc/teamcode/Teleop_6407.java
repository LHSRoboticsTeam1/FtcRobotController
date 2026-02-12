package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleOp_6407", group="Linear OpMode")
public class Teleop_6407 extends LinearOpMode {

    private HardwareForRobot robot;

    @Override
    public void runOpMode() {

        robot = new HardwareForRobot(this);
        robot.init();

        telemetry.addData("Status", "Ready");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            // ---------------- DRIVE ----------------
            double drive  = gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double turn   = gamepad1.right_stick_x;

            robot.manuallyDriveRobot(strafe, drive, turn);

            // ---------------- INTAKE (B BUTTON) ----------------
            if (gamepad1.b) {
                robot.intakeOn();
            } else {
                robot.intakeOff();
            }
            
            // ---------------- LIFT BELTS FORWARD (X BUTTON) ----------------
            if (gamepad1.x) {
                robot.liftItUp();
            } else {
                robot.stopLiftItUp();
            }

            // ---------------- OUTTAKE (A BUTTON) ----------------
            if (gamepad1.a) {
                robot.outtakeShoot();
            } else {
                robot.outtakeStop();
            }

            telemetry.update();
        }
    }
}
