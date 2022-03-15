package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.OuttakeSubsystem;

public class OuttakeCommand extends CommandBase {
    private final OuttakeSubsystem m_outtakeSubsystem;

    public OuttakeCommand(OuttakeSubsystem m_outtakeSubsystem) {
        this.m_outtakeSubsystem = m_outtakeSubsystem;

        addRequirements(m_outtakeSubsystem);
    }
    
    @Override
    public void execute() {
        m_outtakeSubsystem.drive(-Constants.subsystems.intake.INTAKE_SPEED);
    }

    /**
     * This function ensures when the action is complete, the robot stops moving
     * @param interrupted 
     */
    @Override
    public void end(boolean interrupted) {
        m_outtakeSubsystem.drive(0.0);
    }
}
