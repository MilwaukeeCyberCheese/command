package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

  private final CANSparkMax shooterTopSpark = new CANSparkMax(Constants.controllers.SHOOTER_TOP_SPARK, MotorType.kBrushless);
  private final CANSparkMax shooterBottomSpark = new CANSparkMax(Constants.controllers.SHOOTER_BOTTOM_SPARK, MotorType.kBrushless);

    public ShooterSubsystem() {}

    @Override
    public void periodic() {
    }
  
    @Override
    public void simulationPeriodic() {
      // This method will be called once per scheduler run during simulation
    }    
}
