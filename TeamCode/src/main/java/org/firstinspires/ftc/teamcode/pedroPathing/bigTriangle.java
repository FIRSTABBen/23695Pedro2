package org.firstinspires.ftc.teamcode.pedroPathing;

import static android.os.SystemClock.sleep;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous
public class bigTriangle extends OpMode {

    private Follower follower;

    private Timer pathTimer, opModeTimer;

    private DcMotorEx shooter = null;
    private DcMotor intakeForward = null;
    private DcMotor intakeBack =null;
    private Servo hood = null;
    private Servo blocker = null;

    private enum Pathstate{
        PATH1, PATH2, PATH3, PATH4, PATH5, PATH6, PATH7, PATH8, PATH9, PATH10, PATH11
        //SPOS EPOS
    }

    Pathstate pathState;

    //"x,y,heading in radians(yuck)"
    private final Pose startPose = new Pose(56,8, Math.toRadians(180));
    private final Pose movePose1 = new Pose(21, 123, Math.toRadians(180));
    private final Pose movePose2 = new Pose(30,115, Math.toRadians(180));
    private final Pose movePose3 = new Pose(43,84, Math.toRadians(180));
    private final Pose movePose4 = new Pose(15, 84, Math.toRadians(180));
    private final Pose movePose5 = new Pose(29, 115, Math.toRadians(180));
    private final Pose movePose6 = new Pose(44, 58, Math.toRadians(180));
    private final Pose movePose7 = new Pose(14,58, Math.toRadians(180));
    private final Pose movePose8 = new Pose(29, 114, Math.toRadians(180));
    private final Pose movePose9 = new Pose(19,105, Math.toRadians(180));


    //alice in pathChains\/

    private PathChain path1;
    private PathChain path2;
    private PathChain path3;
    private PathChain path4;
    private PathChain path5;
    private PathChain path6;
    private PathChain path7;
    private PathChain path8;
    private PathChain path9;
    private PathChain path10;
    private PathChain path11;


    private void shoot(){
        intakeBack.setPower(1);
        intakeForward.setPower(1);
        blocker.setPosition(0);
        sleep(3000);
        intakeBack.setPower(0);
        intakeForward.setPower(0);
        blocker.setPosition(0);
    }
    public void statePathUpdate() {
        shooter.setVelocity(1100);

        //1100 for close
        hood.setPosition(0.12);
        //0.12 for close
        switch (pathState) {
            case PATH1:
                sleep(3000);
                shoot();
                follower.followPath(path1, true);
                setPathState(Pathstate.PATH2);
                break;
            case PATH2:
                if (!follower.isBusy()) {
                    intakeBack.setPower(1);
                    intakeForward.setPower(1);
                    telemetry.addLine("done Path1");
                    follower.followPath(path2, true);
                    setPathState(Pathstate.PATH3);
                }
                break;
            case PATH3:
                if (!follower.isBusy()) {
                    intakeBack.setPower(0);
                    intakeForward.setPower(0);
                    telemetry.addLine("done Path2");
                    follower.followPath(path3, true);
                    setPathState(Pathstate.PATH4);
                }
                break;
            //shoot
            case PATH4:
                if (!follower.isBusy()) {
                    shoot();
                    telemetry.addLine("done Path3");
                    follower.followPath(path4, true);
                    setPathState(Pathstate.PATH5);
                }
                break;
            case PATH5:
                if (!follower.isBusy()) {

                    telemetry.addLine("done Path4");
                    follower.followPath(path5, true);
                    setPathState(Pathstate.PATH6);
                }
                break;
            case PATH6:
                if (!follower.isBusy()) {
                    intakeBack.setPower(1);
                    intakeForward.setPower(1);
                    telemetry.addLine("done Path5");
                    follower.followPath(path6, true);
                    setPathState(Pathstate.PATH7);
                }
                break;
            case PATH7:
                if (!follower.isBusy()) {
                    intakeBack.setPower(0);
                    intakeForward.setPower(0);
                    telemetry.addLine("done Path6");
                    follower.followPath(path7, true);
                    setPathState(Pathstate.PATH8);
                }
                break;
            case PATH8:
                if (!follower.isBusy()) {
                    shoot();
                    telemetry.addLine("done Path7");
                    follower.followPath(path8, true);
                    setPathState(Pathstate.PATH9);
                }
                break;
            case PATH9:
                if (!follower.isBusy()) {
                    shoot();
                    telemetry.addLine("done Path7");
                    follower.followPath(path9, true);
                    setPathState(Pathstate.PATH10);
                }
                break;
            case PATH10:
                if (!follower.isBusy()) {
                    shoot();
                    telemetry.addLine("done Path7");
                    follower.followPath(path10, true);
                    setPathState(Pathstate.PATH11);
                }
                break;
            case PATH11:
                if (!follower.isBusy()) {
                    shoot();
                    telemetry.addLine("done Path7");
                    follower.followPath(path11, true);
                    setPathState(Pathstate.PATH11);
                }
                break;
            default:
                telemetry.addLine("no state");
                terminateOpModeNow();
                break;

        }

    }

    public void setPathState(Pathstate newState) {
        pathState = newState;
        pathTimer.resetTimer();

    }
    public void buildPaths() {
        path1 = follower.pathBuilder()
                .addPath(new BezierLine(startPose, movePose1))
                .setLinearHeadingInterpolation(startPose.getHeading(), movePose1.getHeading())
                .build();
        path2 = follower.pathBuilder()
                .addPath(new BezierLine(movePose1, movePose2))
                .setLinearHeadingInterpolation(movePose1.getHeading(), movePose2.getHeading())
                .build();
        path3 = follower.pathBuilder()
                .addPath(new BezierLine(movePose2, movePose3))
                .setLinearHeadingInterpolation(movePose2.getHeading(), movePose3.getHeading())
                .build();
        path4 = follower.pathBuilder()
                .addPath(new BezierLine(movePose3, movePose4))
                .setLinearHeadingInterpolation(movePose3.getHeading(), movePose4.getHeading())
                .build();
        path5 = follower.pathBuilder()
                .addPath(new BezierLine(movePose4, movePose5))
                .setLinearHeadingInterpolation(movePose3.getHeading(), movePose4.getHeading())
                .build();
        path6 = follower.pathBuilder()
                .addPath(new BezierLine(movePose4, movePose5))
                .setLinearHeadingInterpolation(movePose4.getHeading(), movePose5.getHeading())
                .build();
        path7 = follower.pathBuilder()
                .addPath(new BezierLine(movePose5, movePose6))
                .setLinearHeadingInterpolation(movePose5.getHeading(), movePose6.getHeading())
                .build();
        path8 = follower.pathBuilder()
                .addPath(new BezierLine(movePose6, movePose7))
                .setLinearHeadingInterpolation(movePose6.getHeading(), movePose7.getHeading())
                .build();
        path9 = follower.pathBuilder()
                .addPath(new BezierLine(movePose7, movePose8))
                .setLinearHeadingInterpolation(movePose7.getHeading(), movePose8.getHeading())
                .build();
        path10 = follower.pathBuilder()
                .addPath(new BezierLine(movePose8, movePose9))
                .setLinearHeadingInterpolation(movePose8.getHeading(), movePose9.getHeading())
                .build();

    }

    @Override
    public void init() {
        pathState = Pathstate.PATH1;
        pathTimer = new Timer();
        opModeTimer = new Timer();
        opModeTimer.resetTimer();
        follower = Constants.createFollower(hardwareMap);
        buildPaths();
        shooter = hardwareMap.get(DcMotorEx.class, "shooter");
        intakeForward = hardwareMap.get(DcMotor.class, "intakeForward");
        intakeBack = hardwareMap.get(DcMotor.class, "intakeBack");
        hood = hardwareMap.get(Servo.class, "hood");
        blocker = hardwareMap.get(Servo.class, "blocker");
        follower.setPose(startPose);

        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeForward.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        shooter.setDirection(DcMotor.Direction.FORWARD);
        intakeForward.setDirection(DcMotor.Direction.FORWARD);
        intakeBack.setDirection(DcMotor.Direction.FORWARD);
        hood.setDirection(Servo.Direction.FORWARD);
        blocker.setDirection(Servo.Direction.FORWARD);

    }

    public void start() {
        opModeTimer.resetTimer();
        setPathState(pathState);

    }
    public void loop(){
        follower.update();
        statePathUpdate();


        telemetry.addData("path state", pathState.toString());
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.addData("Path time", pathTimer.getElapsedTimeSeconds());
    }
}
