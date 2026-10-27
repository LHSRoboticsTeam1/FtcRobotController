package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.pedropathing.follower.Follower;

import org.firstinspires.ftc.teamcode.teamPedroPathing.Constants;



@TeleOp
public class BiobuzzTeleOpMk1 extends OpMode {
    private RobotHardware robotHardware;

    private Follower follower;
    @Override
    public void init() {
        follower = Constants.create(hardwareMap);
        robotHardware = new RobotHardware(this);
    }
    @Override
    public void loop() {
        follower.manual(
                -gamepad1.left_stick_y,
                gamepad1.left_stick_x,
                gamepad1.right_stick_x
        );
        follower.update();
        if(gamepad1.a){
            robotHardware.intakeOn();
        }
        if(gamepad1.dpadUpWasPressed()){
            robotHardware.intakeSpeedUp();
        }
        if(gamepad1.dpadDownWasPressed()){
            robotHardware.intakeSpeedDown();
        }
       if(gamepad1.y){
            robotHardware.shooterON();
        }
       if(gamepad1.x){
           robotHardware.shooterIntakeOn();
       }

            telemetry.addData("intake Velocity",robotHardware.power);
            telemetry.update();
    }
}
