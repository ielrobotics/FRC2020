/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

public class TurnRelativeAngle extends CommandBase {
  /**
   * Creates a new TurnRelativeAngle.
   */
  private TurnAbsoluteAngle command;
  private final Chassis m_chassis;
  private final double target_angle;
  public TurnRelativeAngle(Chassis chassis, double angle) {
    addRequirements(chassis);
    this.m_chassis = chassis;
    this.target_angle = angle;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.command = new TurnAbsoluteAngle(this.m_chassis, this.target_angle + this.m_chassis.getHeading());
    this.command.initialize();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.command.execute();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.command.end(interrupted);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //1 degree threshold
    return this.command.isFinished();
  }
}
