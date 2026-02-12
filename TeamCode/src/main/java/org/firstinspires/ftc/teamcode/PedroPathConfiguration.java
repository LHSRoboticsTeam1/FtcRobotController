package org.firstinspires.ftc.teamcode;

import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.teamPedroPathing.SwyftMecanum;

/**
 * This class provides a single location to set Pedro Path's myriad constraints.
 * It is responsible for creating and returning a Follower built using these constraints.
 */
public class PedroPathConfiguration {
    private final OpMode myOpMode;

    private Follower follower;

    public PedroPathConfiguration(LinearOpMode opMode) {
        this.myOpMode = opMode;
        init();
    }

    /**
     * Call all the constant builders and build the Follower instance.
     */
    private void init() {
        MecanumConstants driveConstants = buildMecanumConstants();

        this.follower = new FollowerBuilder(buildFollowerConstants(), myOpMode.hardwareMap)
                .setDrivetrain(new SwyftMecanum(myOpMode.hardwareMap, driveConstants))
                .pinpointLocalizer(buildPinpointConstants())
                .pathConstraints(buildPathConstraints())
                .build();

        /*
         * Follower has its own globalMaxPower that is initialized to 1.
         * Irritatingly, this cannot be changed using FollowerConstants.
         * It can only be changed by calling setMaxPower() on the built instance
         * of Follower. Set globalMaxPower to be the the same as that in DriveConstants.
         * (DriveConstants are set via MecanumConstants.)
         */
        this.follower.setMaxPower(driveConstants.getMaxPower());
    }

    private FollowerConstants buildFollowerConstants() {
        return new FollowerConstants()
            .mass(10.2)
            .forwardZeroPowerAcceleration(-42.2)
            .lateralZeroPowerAcceleration(-61.5)
            .headingPIDFCoefficients(new PIDFCoefficients(.9, 0, .03, .03))
                //f: 0.03
            .translationalPIDFCoefficients(new PIDFCoefficients(.05,0,.005,.02))
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(.02, 0, .0001, .6, 0))
            .centripetalScaling(.0004);
    }

    private MecanumConstants buildMecanumConstants() {
        return new MecanumConstants()
            .maxPower(1)
            .leftFrontMotorName("LeftFront")
            .leftRearMotorName("LeftBack")
            .rightFrontMotorName("FrontRight")
            .rightRearMotorName("BackRight")
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .useBrakeModeInTeleOp(false) //Affects DriveTrain.startTeleopDrive() overload.
            .xVelocity(74.06)
            .yVelocity(49.81);
    }

    private PinpointConstants buildPinpointConstants() {
        return new PinpointConstants()
            .forwardPodY(0)
            .strafePodX(1.9685)
            .distanceUnit(DistanceUnit.INCH)
            .hardwareMapName("pinpoint")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD);
    }

    private PathConstraints buildPathConstraints() {
        return new PathConstraints(.99, 100, .9, 1);
    }

    public Follower getFollower() {
        return follower;
    }
}
