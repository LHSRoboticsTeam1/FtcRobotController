 package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import java.util.Locale;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import java.util.Locale;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name="Teleop_6407", group="Iterative Opmode")
//@Disabled
public class Teleop_6407 extends LinearOpMode {
    // Declare OpMode members.

    private ElapsedTime runtime = new ElapsedTime();
    private HardwareForRObot robot;

    boolean isLeftBumper1Pressed = false;
    boolean isRightBumper1Pressed = false;
    boolean isIntakeOn = false;

    //Code to run ONCE when the driver hits INIT
    @Override
    public void runOpMode () {
        robot = new HardwareForRObot(this);
        robot.init();
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        
        while (opModeIsActive()) {
            robot.manuallyDriveRobot(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

            if (gamepad1.y) {
                robot.releaseDrone();
            }
            if (gamepad1.dpad_down) {
                robot.raisePully();
            }
            if (gamepad1.dpad_up) {
                robot.lowerPully();
            }
            if (!gamepad1.dpad_down && !gamepad1.dpad_up) {
                robot.stoppully();
            }
            if (gamepad1.start) {
                robot.hangrobot();
            }
            if (gamepad1.left_bumper)
            {
                robot.raiseArm();
            }
            if (gamepad1.right_bumper)
            {
                robot.lowerArm();
            }
            if (!gamepad2.left_bumper && !gamepad1.right_bumper)
            {
                robot.setArmPower(0);
            }
            if (gamepad1.b)
            {
               robot.openClaw();
            }
            if (gamepad1.a)
            {
                robot.closeClaw();
            }
            if (gamepad1.x)
            {
                robot.resetDrone();
            }

        }
        robot.shutDown();
    }

    String formatAngle (AngleUnit angleUnit,double angle){
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    String formatDegrees ( double degrees){
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }

}