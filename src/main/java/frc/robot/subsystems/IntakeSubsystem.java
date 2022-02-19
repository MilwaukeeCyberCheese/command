package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {

    private final CANSparkMax intakeSpark = new CANSparkMax(Constants.controllers.INTAKE_SPARK, MotorType.kBrushless);
    private double speed = 0.0;

    public IntakeSubsystem() {}

    @Override
    public void periodic() {
      intakeSpark.set(speed);
    }
  
    @Override
    public void simulationPeriodic() {
      // This method will be called once per scheduler run during simulation
    }  

    public void drive(double speed) {
      this.speed = speed;
    }
    
}
