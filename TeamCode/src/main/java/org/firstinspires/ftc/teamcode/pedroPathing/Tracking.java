package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import java.util.List;

public class Tracking {
}
//setup
//limelight = hardwareMap.get(Limelight3A.class, "limelight");
//limelight.pipelineSwitch(0); RED
//limelight.pipelineSwitch(2); BLUE
//int tagID = 0;
//private Limelight3A limelight = null;

//LLResult result = limelight.getLatestResult();
//limelight.start();
//limelight.setPollRateHz(90);
//double tx = result.getTx();
//double ta = result.getTa();
//String limelight_telemetry = "Limelight Data";
//int pipeline = result.getPipelineIndex();
//
//if (llResult.isValid()) {
//    List<LLResultTypes.FiducialResult> fiducialResults = result.getFiducialResults();
//         for (LLResultTypes.FiducialResult fr : fiducialResults) {
//             tagID = fr.getFiducialId();
//         }
//    }
//    else {
//        tagID = 0;
//    }

// actual code
//if ((result.getStaleness() < 100) && ((result != null && result.isValid()))) {
//    if (ta < 0.5){
//        if (tx < 0 || tx > 6){    //red, -6 and 0 for blue
//             turningPower = (tx / 32.5);
//        }
//        else {
//            turningPower = 0;
//        }
//    }
//    else if (ta > 0.5){
//        if (tx < -3 || tx > 3){
//            turningPower = (tx / 32.5);
//        }
//        else if (tx < -7 || tx > 7){
//            turningPower = (tx / 40);
//        }
//        else {
//            turningPower = 0;
//        }
//    }
//}
