package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;




@TeleOp
public class ThisProgramCannotRun extends OpMode
{


    @Override
    public void init() {
        //Declare variables for phone to recognise//
        String goober = "";

        //names on the config
        telemetry.addData("ill give ya 50 bucks if you can make this program run", goober);

    }


    //Set variables//
    @Override
    public void loop() {
       terminateOpModeNow();
    }


    @Override
    public void stop() {

    }

}

