package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.LoadSubsystem;

public class LoadCommand extends CommandBase{
    private final LoadSubsystem m_loadSubsystem;

    public LoadCommand(LoadSubsystem m_loadSubsystem){
        this.m_loadSubsystem = m_loadSubsystem;

        addRequirements(m_loadSubsystem);
    }

    @Override
    public void execute(){
        m_loadSubsystem.load(0.5);
    }

    

    @Override
    public void end(boolean interrupted){
        m_loadSubsystem.load(0);
    }
}