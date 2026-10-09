package org.firstinspires.ftc.teamcode.teamPedroPathing;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.follower.Follower;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * This class provides a single location to set Pedro Path's myriad constraints.
 * It is responsible for creating and returning a Follower built using these constraints.
 */
public class PedroPathConfiguration {
    private final OpMode myOpMode;

    private Follower follower;

    public PedroPathConfiguration(OpMode opMode) {
        this.myOpMode = opMode;
        init();
    }

    /**
     * Call all the constant builders and build the Follower instance.
     */
    private void init() {
        HardwareMap hwMap = myOpMode.hardwareMap;

        this.follower = new Follower(
                new PinpointLocalizer(hwMap, buildPinpointConstants()),
                new Mecanum(hwMap, buildMecanumConfig()),
                new Foresight(buildForesightConfig()));
    }

    private MecanumConfig buildMecanumConfig() {
        return new MecanumConfig(config -> {

        });
    }

    private PinpointConfig buildPinpointConstants() {
        return new PinpointConfig(config -> {
        });
    }

    private ForesightConfig buildForesightConfig() {
        return new ForesightConfig(config -> {});
    }

    public Follower getFollower() {
        return follower;
    }
}