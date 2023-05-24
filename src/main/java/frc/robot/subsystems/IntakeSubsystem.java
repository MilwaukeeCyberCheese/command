package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class IntakeSubsystem extends SubsystemBase {

  private double speed;

  public IntakeSubsystem() {
    Constants.subsystems.intake.m_intake.setInverted(Constants.subsystems.intake.INVERTED);
  }

  /**
   * This function is called every few milliseconds when the robot is enabled
   * 
   * For this subsystem, this function helps record auto paths
   */
  @Override
  public void periodic() {
    // this is only used for when planning auto paths
  //   if (RobotContainer.readAuto)
  //     RobotContainer.m_autoSubsystem.addIntaking(speed);
  }

  /**
   * This function is called when the button on the controller to operate this
   * subsystem is activated
   * 
   * @param speed The speed to move this subsystem at
   */
  public void drive(double speed) {
    this.speed = speed;
    Constants.subsystems.intake.m_intake.set(speed);
  }

}