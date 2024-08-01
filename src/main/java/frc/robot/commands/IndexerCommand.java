// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IndexerSubsystem;

public class IndexerCommand extends Command {

  IndexerSubsystem m_IndexerSubsystem;

  /** Creates a new IndexerCommand. */
  public IndexerCommand(IndexerSubsystem indexerSubsystem) {
    m_IndexerSubsystem = indexerSubsystem;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(indexerSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_IndexerSubsystem.run();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_IndexerSubsystem.kill();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (IndexerSubsystem.beamBreak.getValue()){
      return true;
    } else {
      return false;
    }
  }
}
