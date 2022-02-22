package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class OuttakeSubsystem extends SubsystemBase {

    private final CANSparkMax intakeSpark = new CANSparkMax(Constants.controllers.INTAKE_SPARK, MotorType.kBrushless);

    public OuttakeSubsystem() {}

    @Override
    public void periodic() {
  
    }

    public void drive(double speed) {
      intakeSpark.set(speed);
    }
    
}
