package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;


@Autonomous
public class autoPractise extends OpMode {

    private Follower follower;

    private Timer pathTimer, opModeTimer;

    private enum Pathstate{
        PATH1, PATH2, PATH3, PATH4, PATH5, PATH6, PATH7, PATH8, PATH9, PATH10, PATH11
        //SPOS EPOS
    }

    Pathstate pathState;

    //"x,y,heading in radians(yuck)"
    private final Pose startPose = new Pose(56,8, Math.toRadians(180));
    private final Pose movePose1 = new Pose(45, 32, Math.toRadians(180));
    private final Pose movePose2 = new Pose(15,32, Math.toRadians(180));
    private final Pose movePose3 = new Pose(56,27, Math.toRadians(270));
    private final Pose movePose4 = new Pose(8, 28, Math.toRadians(270));
    private final Pose movePose5 = new Pose(8, 8, Math.toRadians(270));
    private final Pose movePose6 = new Pose(56, 20, Math.toRadians(90));
    private final Pose movePose7 = new Pose(56,8, Math.toRadians(90));
    private final Pose finalPose = new Pose(56, 20, Math.toRadians(90));


    //pathChains\/

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
public void statePathUpdate() {
        switch (pathState) {
            case PATH1:
                follower.followPath(path1, true);
                setPathState(Pathstate.PATH2);

                break;
            case PATH2:
                if (!follower.isBusy()) {
                    telemetry.addLine("done Path1");
                    follower.followPath(path2, true);
                    setPathState(Pathstate.PATH3);
                }
                break;
            case PATH3:
                if (!follower.isBusy()) {
                    telemetry.addLine("done Path2");
                    follower.followPath(path3, true);
                    setPathState(Pathstate.PATH4);
                }
                break;
            case PATH4:
                if (!follower.isBusy()) {
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
                    telemetry.addLine("done Path5");
                    follower.followPath(path6, true);
                    setPathState(Pathstate.PATH7);
                }
                break;
            case PATH7:
                if (!follower.isBusy()) {
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
                    telemetry.addLine("done Path8");
                    follower.followPath(path9, true);
                    setPathState(Pathstate.PATH10);
                }
                break;
            case PATH10:
                if (!follower.isBusy()) {
                    telemetry.addLine("done Path9");
                    follower.followPath(path10, true);
                    setPathState(Pathstate.PATH11);
                }
                break;
            case PATH11:
                if (!follower.isBusy()) {
                    telemetry.addLine("done Path9");
                    follower.followPath(path11, true);
                    terminateOpModeNow();
                }
                break;
            default:
                telemetry.addLine("no state");
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
                .addPath(new BezierLine(movePose2, startPose))
                .setLinearHeadingInterpolation(movePose2.getHeading(), startPose.getHeading())
                .build();
        path4 = follower.pathBuilder()
                .addPath(new BezierLine(startPose, movePose3))
                .setLinearHeadingInterpolation(startPose.getHeading(), movePose3.getHeading())
                .build();
        path5 = follower.pathBuilder()
                .addPath(new BezierLine(movePose3, movePose4))
                .setLinearHeadingInterpolation(movePose3.getHeading(), movePose4.getHeading())
                .build();
        path6 = follower.pathBuilder()
                .addPath(new BezierLine(movePose4, movePose5))
                .setLinearHeadingInterpolation(movePose4.getHeading(), movePose5.getHeading())
                .build();
        path7 = follower.pathBuilder()
                .addPath(new BezierLine(movePose5, startPose))
                .setLinearHeadingInterpolation(movePose5.getHeading(), startPose.getHeading())
                .build();
        path8 = follower.pathBuilder()
                .addPath(new BezierLine(startPose, movePose6))
                .setLinearHeadingInterpolation(startPose.getHeading(), movePose6.getHeading())
                .build();
        path9 = follower.pathBuilder()
                .addPath(new BezierLine(movePose6, movePose7))
                .setLinearHeadingInterpolation(movePose6.getHeading(), movePose7.getHeading())
                .build();
        path10 = follower.pathBuilder()
                .addPath(new BezierLine(movePose7, startPose))
                .setLinearHeadingInterpolation(movePose7.getHeading(), startPose.getHeading())
                .build();
        path11 = follower.pathBuilder()
                .addPath(new BezierLine(startPose, finalPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), finalPose.getHeading())
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

        follower.setPose(startPose);

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
