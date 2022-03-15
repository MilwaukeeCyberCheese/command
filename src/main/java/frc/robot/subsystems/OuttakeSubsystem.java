package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class OuttakeSubsystem extends SubsystemBase {

    /**
     * This function is called every few milliseconds when the robot is enabled
     * 
     * For this subsystem, this function is unused
     */
    @Override
    public void periodic() {}

    /**
     * This function is called when the button on the controller to operate this subsystem is activated
     * 
     * @param speed The speed to move this subsystem at
     */
    public void drive(double speed) {
      Constants.controllers.intakeSpark.set(speed);
    }
    
}
