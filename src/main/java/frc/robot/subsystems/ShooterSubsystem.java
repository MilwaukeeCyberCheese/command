package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

  private final CANSparkMax shooterTopSpark = new CANSparkMax(Constants.controllers.SHOOTER_TOP_SPARK, MotorType.kBrushed);
  private final CANSparkMax shooterBottomSpark = new CANSparkMax(Constants.controllers.SHOOTER_BOTTOM_SPARK, MotorType.kBrushed);
  
  double topSpeed = 0.0;
  double bottomSpeed = 0.0;

    public ShooterSubsystem() {}
    
    @Override
    public void periodic() {
      shooterTopSpark.set(topSpeed);
      shooterBottomSpark.set(bottomSpeed);
    }

    public void drive(double topSpeed, double bottomSpeed) {
      this.topSpeed = topSpeed;
      this.bottomSpeed = bottomSpeed;
    }
}
