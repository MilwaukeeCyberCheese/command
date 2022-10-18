package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ServoSubsystem;

public class ServoCommand extends CommandBase {
    private final ServoSubsystem m_servoSubsystem;

    public ServoCommand(ServoSubsystem m_servoSubsystem) {
        this.m_servoSubsystem = m_servoSubsystem;

        addRequirements(m_servoSubsystem);
    }

    @Override
    public void execute() {
        m_servoSubsystem.drive(Constants.subsystems.servo.OPEN_POS);
    }
    
    /**
     * This function ensures when the action is complete, the robot stops moving
     * @param interrupted 
     */
    @Override
    public void end(boolean interrupted) {
        m_servoSubsystem.drive(Constants.subsystems.servo.CLOSED_POS);
    }
}