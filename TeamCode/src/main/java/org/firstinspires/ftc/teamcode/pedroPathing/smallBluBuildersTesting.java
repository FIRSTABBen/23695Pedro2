package org.firstinspires.ftc.teamcode.pedroPathing;

import static android.os.SystemClock.sleep;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.List;


@Autonomous
public class smallBluBuildersTesting extends OpMode {

    private Follower follower;

    private Timer pathTimer, opModeTimer;

    private DcMotorEx shooter = null;
    private DcMotor intakeForward = null;
    private DcMotor intakeBack =null;
    private DcMotor turret = null;
    private Servo hood = null;
    private Servo blocker = null;

    private Limelight3A limelight = null;


    double turningPower = 0;
    int timer = (500);
    int tagID = 0;
    boolean target = false;

    private enum Pathstate{
        PATH1, PATH2, PATH3, PATH4, PATH5, PATH6, PATH7, PATH8, PATH9, PATH10, PATH11
        //SPOS EPOS
    }

    Pathstate pathState;

    private final Pose startPose = new Pose(56,8, Math.toRadians(-180));
    private final Pose movePose1 = new Pose(56, 12, Math.toRadians(-180));
    private final Pose movePose2 = new Pose(39,35, Math.toRadians(-180));
    private final Pose movePose3 = new Pose(12,35, Math.toRadians(-180));
    private final Pose movePose4 = new Pose(56, 12, Math.toRadians(-180));
    private final Pose movePose5 = new Pose(9, 35, Math.toRadians(270));
    private final Pose movePose6 = new Pose(9, 9, Math.toRadians(270));
    private final Pose movePose7 = new Pose(16,16, Math.toRadians(270));
    private final Pose movePose8 = new Pose(56, 12, Math.toRadians(-180));
    private final Pose finalPose = new Pose(36, 12, Math.toRadians(-180));


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
//    private PathChain path11;

    private void shoot(){
        blocker.setPosition(0.6);
        sleep(1000);
        intakeBack.setPower(1);
        intakeForward.setPower(1);
        sleep(1000);
        intakeBack.setPower(0);
        intakeForward.setPower(0);
        blocker.setPosition(0.05);
    }
//    private void align(){
//        int timer = (500);
//        LLResult result = limelight.getLatestResult();
//        double tx = result.getTx();
//        while (timer > 0){
//            result = limelight.getLatestResult();
//            tx = result.getTx();
//            if (tx < -7.5 || tx > -1.5){
//                turningPower = (tx / 32.5);
//            }
//            else {
//                turningPower = 0;
//            }
//            timer =- 1;
//            telemetry.addData("tag x ", (tx));
//            telemetry.addData("timer ", (timer));
//        }


    public void statePathUpdate() {
        shooter.setVelocity(2000);
        target = false;

        //1100 for close
        hood.setPosition(0.365);
        //0.12 for close
        switch (pathState) {
            case PATH1:
                loop();
                follower.followPath(path1, true);
                setPathState(Pathstate.PATH2);
                break;
            case PATH2:
                if (!follower.isBusy()) {
//                    align();
                    sleep(2000);
                    shoot();
                    telemetry.addLine("done Path1");
                    follower.followPath(path2, true);
                    setPathState(Pathstate.PATH3);
                }
                break;
            case PATH3:
                if (!follower.isBusy()) {
                    intakeBack.setPower(1);
                    intakeForward.setPower(1);
                    telemetry.addLine("done Path2");
                    follower.followPath(path3, true);
                    sleep(250);
                    setPathState(Pathstate.PATH4);
                }
                break;
            //shoot
            case PATH4:
                if (!follower.isBusy()) {
                    intakeBack.setPower(0);
                    intakeForward.setPower(0);
                    telemetry.addLine("done Path3");
                    follower.followPath(path4, true);
                    setPathState(Pathstate.PATH5);
                }
                break;
            case PATH5:
                if (!follower.isBusy()) {
//                    align();
                    sleep(2000);
                    shoot();
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
                    sleep(250);
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
                    telemetry.addLine("done Path7");
                    follower.followPath(path8, true);
                    setPathState(Pathstate.PATH9);
                }
                break;
            case PATH9:
                if (!follower.isBusy()) {
//                    align();
                    sleep(2000);
                    shoot();
                    telemetry.addLine("done Path8");
                    follower.followPath(path9, true);
                    setPathState(Pathstate.PATH10);
                }
                break;
            case PATH10:
                if (!follower.isBusy()) {
                    telemetry.addLine("done Path9");
                    follower.followPath(path10, true);
                }
                break;
            default:
                telemetry.addLine("no state");
                stop();
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
                .setLinearHeadingInterpolation(movePose4.getHeading(), movePose5.getHeading())
                .build();
        path6 = follower.pathBuilder()
                .addPath(new BezierLine(movePose5, movePose6))
                .setLinearHeadingInterpolation(movePose5.getHeading(), movePose6.getHeading())
                .build();
        path7 = follower.pathBuilder()
                .addPath(new BezierLine(movePose6, movePose7))
                .setLinearHeadingInterpolation(movePose6.getHeading(), movePose7.getHeading())
                .build();
        path8 = follower.pathBuilder()
                .addPath(new BezierLine(movePose7, movePose8))
                .setLinearHeadingInterpolation(movePose7.getHeading(), movePose8.getHeading())
                .build();
        path9 = follower.pathBuilder()
                .addPath(new BezierLine(movePose8, finalPose))
                .setLinearHeadingInterpolation(movePose8.getHeading(), finalPose.getHeading())
                .build();
//        path10 = follower.pathBuilder()
//                .addPath(new BezierLine(movePose7, movePose8))
//                .setLinearHeadingInterpolation(movePose8.getHeading(), finalPose.getHeading())
//                .build();
//        path11 = follower.pathBuilder()
//                .addPath(new BezierLine(startPose, finalPose))
//                .setLinearHeadingInterpolation(startPose.getHeading(), finalPose.getHeading())
//                .build();
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
        turret = hardwareMap.get(DcMotor.class, "turret");
        follower.setPose(startPose);
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(2);


        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeForward.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        turret.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        shooter.setDirection(DcMotor.Direction.REVERSE);
        intakeForward.setDirection(DcMotor.Direction.FORWARD);
        intakeBack.setDirection(DcMotor.Direction.REVERSE);
        hood.setDirection(Servo.Direction.FORWARD);
        blocker.setDirection(Servo.Direction.FORWARD);
        turret.setDirection(DcMotor.Direction.FORWARD);


    }

    public void start() {
        opModeTimer.resetTimer();
        setPathState(pathState);
        limelight.start();
        limelight.setPollRateHz(90);
        LLResult result = limelight.getLatestResult();
        String tagseen = " ";
        String limelight_telemetry = "Limelight Data";
        int pipeline = result.getPipelineIndex();


        if (result.isValid()) {
            List<LLResultTypes.FiducialResult> fiducialResults = result.getFiducialResults();
            for (LLResultTypes.FiducialResult fr : fiducialResults) {
                tagID = fr.getFiducialId();
            }
        } else {
            tagID = 0;
            target = true;
        }


    }
    public void loop() {
        limelight.start();
        limelight.setPollRateHz(90);
        LLResult result = limelight.getLatestResult();
        double tx = result.getTx();
        double ta = result.getTa();
        String tagseen = " ";
        String limelight_telemetry = "Limelight Data";
        int pipeline = result.getPipelineIndex();

        if (result.isValid()) {
            List<LLResultTypes.FiducialResult> fiducialResults = result.getFiducialResults();
            for (LLResultTypes.FiducialResult fr : fiducialResults) {
                tagID = fr.getFiducialId();
            }
        } else {
            tagID = 0;
            target = true;
        }
        if (tx < -7.5 || tx > -1.5) {
            turningPower = (tx / 32.5);
            timer = -1;
            telemetry.addData("tag x ", (tx));
            telemetry.addData("timer ", (timer));
        } else {
            turningPower = 0;
            timer = -1;
            telemetry.addData("tag x ", (tx));
            telemetry.addData("timer ", (timer));
        }


        long staleness = result.getStaleness();
        if (staleness < 100) {
            telemetry.addData("data", "good");
        } else {
            telemetry.addData("data", "bad (" + staleness + " ms)");
        }
        if (result != null && result.isValid()) {
            tagseen = "true";
        } else {
            tagseen = "false";
        }
        telemetry.addData("tagSeen ", tagseen);
        telemetry.addData("limelight x = ", tx);
        telemetry.addData("limelight a = ", ta);
        telemetry.addData("limelight pipeline = ", pipeline);
        telemetry.addData("tag ", "ID: %d", tagID);
        telemetry.update();
        turret.setPower(turningPower);


        follower.update();
        statePathUpdate();


        telemetry.addData("path state", pathState.toString());
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.addData("Path time", pathTimer.getElapsedTimeSeconds());

    }
    @Override
    public void stop(){
            shooter.setVelocity(0);
            setPathState(null);
            limelight.stop();
            requestOpModeStop();
    }

}
