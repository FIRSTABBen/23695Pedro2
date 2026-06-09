package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class BlockerandHoodBeinFunky extends OpMode
{
    private Servo hood = null;
    private Servo blocker = null;
    boolean blockerPowerControl = true;
    boolean hoodPowerControl = true;


    @Override
    public void init() {
        //Declare variables for phone to recognise//

        //names on the config

        hood = hardwareMap.get(Servo.class, "hood");
        blocker = hardwareMap.get(Servo.class, "blocker");


        //other motor initializing


        hood.setDirection(Servo.Direction.FORWARD);
        blocker.setDirection(Servo.Direction.FORWARD);



        hood.setPosition(0.27);
        blocker.setPosition(0.33);


        telemetry.addData("status", "Initialized");
    }


    //Set variables//
    @Override
    public void loop() {

        // drive variables


//      y
//   x     b   << controller button layout
//      a
        // joystick controls
        // shooter controls
        if (gamepad2.right_trigger > 0.5) {
            blocker.setPosition(0);
            hood.setPosition(0.24);
        }
        else if (gamepad2.right_bumper) {
            hood.setPosition(0.12);
            blocker.setPosition(0.66);
        }
        else if (gamepad2.a) {
            hood.setPosition(0.4);
            blocker.setPosition(1);
        }
        else if (gamepad2.x) {
            blocker.setPosition(0.33);
            hood.setPosition(0.05);

        }

        telemetry.addData("hood pos ", hood.getPosition());
        telemetry.addData("blocker pos ", blocker.getPosition());
        telemetry.addData("blocker stuff ", blocker.getConnectionInfo(), blocker.getClass(), blocker.getDeviceName());


        // intake and transfer controls
    }


    @Override
    public void stop() {



    }

}

