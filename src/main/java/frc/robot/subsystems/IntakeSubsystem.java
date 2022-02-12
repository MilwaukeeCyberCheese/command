package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {

  private final CANSparkMax intakeSpark = new CANSparkMax(Constants.controllers.INTAKE_SPARK, MotorType.kBrushless);

    public IntakeSubsystem() {}

    @Override
    public void periodic() {
    }
  
    @Override
    public void simulationPeriodic() {
      // This method will be called once per scheduler run during simulation
    }  
    
}
