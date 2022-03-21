package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ShooterSubsystem extends SubsystemBase {
  
    double topSpeed = 0.0;
    double bottomSpeed = 0.0;
    
    /**
     * This function is called every few milliseconds when the robot is enabled
     * 
     * For this subsystem, this function helps record auto paths
     */
    @Override
    public void periodic() {
      if (RobotContainer.readAuto)
          RobotContainer.m_autoSubsystem.addShooting(topSpeed, bottomSpeed);
    }

    /**
     * This function is called when the button on the controller to operate this subsystem is activated
     * 
     * @param speed The speed to move this subsystem at
     */
    public void drive(double topSpeed, double bottomSpeed) {
      this.topSpeed = topSpeed;
      this.bottomSpeed = bottomSpeed;
      Constants.controllers.shooterTopSpark.set(topSpeed);
      Constants.controllers.shooterBottomSpark.set(bottomSpeed);
    }
}
