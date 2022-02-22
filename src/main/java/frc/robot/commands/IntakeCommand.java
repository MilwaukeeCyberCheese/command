package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends CommandBase {
    private final IntakeSubsystem m_intakeSubsystem;

    public IntakeCommand(IntakeSubsystem m_intakeSubsystem) {
        this.m_intakeSubsystem = m_intakeSubsystem;

        addRequirements(m_intakeSubsystem);
    }
    
    @Override
    public void execute() {
        m_intakeSubsystem.drive(Constants.subsystems.intake.INTAKE_SPEED);
    }

     /**
     * @param interrupted
     */
    @Override
    public void end(boolean interrupted) {
        m_intakeSubsystem.drive(0.0);
    }
}
