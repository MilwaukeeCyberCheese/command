package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends CommandBase {
    private final IntakeSubsystem m_intakeSubsystem;

    public IntakeCommand(IntakeSubsystem intakeSubsystem) {
        this.m_intakeSubsystem = intakeSubsystem;
    }
    
    @Override
    public void execute() {
        
    }

     /**
     * @param interrupted
     */
    @Override
    public void end(boolean interrupted) {

    }
}
