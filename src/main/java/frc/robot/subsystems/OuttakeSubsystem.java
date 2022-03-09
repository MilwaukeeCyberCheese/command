package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class OuttakeSubsystem extends SubsystemBase {

    public OuttakeSubsystem() {}

    @Override
    public void periodic() {
  
    }

    public void drive(double speed) {
      Constants.controllers.intakeSpark.set(speed);
    }
    
}
