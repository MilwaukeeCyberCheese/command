package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ServoSubsystem extends SubsystemBase {
    
    public ServoSubsystem() {}

    public void periodic() {
        if (RobotContainer.readAuto) {
            RobotContainer.m_autoSubsystem.addServo(Constants.controllers.servo.getPosition() > 0);
        }
    }

    public void drive(double pos) {
        Constants.controllers.servo.set(pos);
    }
}