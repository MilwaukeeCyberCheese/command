package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class OuttakeSubsystem extends SubsystemBase {

    private final CANSparkMax intakeSpark = IntakeSubsystem.intakeSpark;

    public OuttakeSubsystem() {}

    @Override
    public void periodic() {
  
    }

    public void drive(double speed) {
      intakeSpark.set(speed);
    }
    
}
