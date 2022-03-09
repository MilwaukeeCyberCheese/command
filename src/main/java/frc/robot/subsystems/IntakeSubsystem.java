package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class IntakeSubsystem extends SubsystemBase {

    private double speed;

    public IntakeSubsystem() {}

    @Override
    public void periodic() {
      // this is only used for when planning auto paths
      if (RobotContainer.readAuto)
          RobotContainer.m_autoSubsystem.addIntaking(speed > 0.0);    
    }

    public void drive(double speed) {
      this.speed = speed;
      Constants.controllers.intakeSpark.set(speed);
    }
    
}