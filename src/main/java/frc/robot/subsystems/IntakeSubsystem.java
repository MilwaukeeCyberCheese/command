package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {

    public static final CANSparkMax intakeSpark = new CANSparkMax(Constants.controllers.INTAKE_SPARK, MotorType.kBrushless);

    public IntakeSubsystem() {}

    @Override
    public void periodic() {
  
    }

    public void drive(double speed) {
      intakeSpark.set(speed);
    }
    
}
