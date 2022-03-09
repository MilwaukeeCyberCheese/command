package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class LoadSubsystem extends SubsystemBase {
    
    public LoadSubsystem(){}

public void periodic(){
    if(RobotContainer.readAuto){
        RobotContainer.m_autoSubsystem.addServo(Constants.controllers.loadServo.getPosition() > 0);
    }
}

    public void load(double pos){
        Constants.controllers.loadServo.set(pos);
    }
}