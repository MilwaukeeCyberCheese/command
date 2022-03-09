package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ShooterSubsystem extends SubsystemBase {
  
  double topSpeed = 0.0;
  double bottomSpeed = 0.0;

    public ShooterSubsystem() {}
    
    @Override
    public void periodic() {
      // this is only used for when planning auto paths
      if (RobotContainer.readAuto)
          RobotContainer.m_autoSubsystem.addShooting(topSpeed > 0.0 && bottomSpeed > 0.0);
    }

    public void drive(double topSpeed, double bottomSpeed) {
      this.topSpeed = topSpeed;
      this.bottomSpeed = bottomSpeed;
      Constants.controllers.shooterTopSpark.set(topSpeed);
      Constants.controllers.shooterBottomSpark.set(bottomSpeed);
    }
}
