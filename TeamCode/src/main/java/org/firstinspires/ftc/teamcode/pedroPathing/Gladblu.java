package org.firstinspires.ftc.teamcode.pedroPathing;

import static android.os.SystemClock.sleep;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import  com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous

public class Gladblu extends OpMode {
    private Follower follower;
    private Timer pathTimer, actionTimer, opmodeTimer;
    private int pathState;

    private DcMotorEx shooter = null;
    private DcMotor intakeForward = null;
    private DcMotor intakeBack =null;
    private Servo hood = null;
    private Servo blocker = null;
    private final Pose scorePose = new Pose(56,8, Math.toRadians(180));
    private final Pose path2 = new Pose(9,25,Math.toRadians(270));
    private final Pose path3 = new Pose(10,10,Math.toRadians(270));
    private final Pose path4 = new Pose(56,20,Math.toRadians(180));
    //scorePose
    private final Pose endPose = new Pose(35,8,Math.toRadians(180));
    private PathChain Grab1, end;
    public void autonomousPathUpdate() {
        shooter.setVelocity(1750);
        intakeForward.setPower(1);
        intakeBack.setPower(1);
        hood.setPosition(0.27);

        switch (pathState) {
            case 0:
                if (shooter.getVelocity()<1725) {
                    blocker.setPosition(1);
                    sleep(2000);
                    follower.followPath(Grab1);
                    pathState = 1;
                    break;
                }
            case 1:
                if(!follower.isBusy()) {
                    blocker.setPosition(1);
                    sleep(2000);
                    follower.followPath(end);

                }
        }
    }
    @Override
    public void loop() {
        follower.update();
        autonomousPathUpdate();
    }
    @Override
    public void start() {
        pathState = 0;
    }
    public void buildPaths() {
        Grab1 = follower.pathBuilder()
                .addPath(new BezierLine(scorePose, path2))
                .setLinearHeadingInterpolation(scorePose.getHeading(), path2.getHeading())
                .addPath(new BezierLine(path2, path3))
                .setLinearHeadingInterpolation(path2.getHeading(), path3.getHeading())
                .addPath(new BezierLine(path3, path4))
                .setLinearHeadingInterpolation(path3.getHeading(), path4.getHeading())
                .addPath(new BezierLine(path4, scorePose))
                .setLinearHeadingInterpolation(path4.getHeading(), scorePose.getHeading())
                .build();
        end = follower.pathBuilder()
                .addPath(new BezierLine(scorePose, endPose))
                .setLinearHeadingInterpolation(scorePose.getHeading(), endPose.getHeading())
                .build();
    }
    @Override
    public void init() {
        pathTimer = new Timer();
        opmodeTimer = new Timer();
        opmodeTimer.resetTimer();
        follower = Constants.createFollower(hardwareMap);
        buildPaths();
        follower.setStartingPose(scorePose);
        shooter = hardwareMap.get(DcMotorEx.class, "shooter");
        intakeForward = hardwareMap.get(DcMotor.class, "intakeForward");
        intakeBack = hardwareMap.get(DcMotor.class, "intakeBack");
        hood = hardwareMap.get(Servo.class, "hood");
        blocker = hardwareMap.get(Servo.class, "blocker");

        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeForward.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        shooter.setDirection(DcMotor.Direction.REVERSE);
        intakeForward.setDirection(DcMotor.Direction.REVERSE);
        intakeBack.setDirection(DcMotor.Direction.REVERSE);
        hood.setDirection(Servo.Direction.FORWARD);
    }
}
