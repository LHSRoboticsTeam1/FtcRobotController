package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class LimelightUp extends OpMode {
    private Limelight3A limelight;
    private boolean BlueUp;

    @Override
    public void init() {
        String tagNumberResults = "";
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(1);

        limelight.start();

    }

    @Override
    public void loop() {
        LLResult llResult = limelight.getLatestResult();
        if(llResult.isValid()){
            BlueUp = true;
        }else {
            BlueUp = false;
        }

        telemetry.addData("up", BlueUp );
        telemetry.update();

    }
}
