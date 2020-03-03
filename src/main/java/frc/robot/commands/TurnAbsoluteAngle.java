/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;
public class TurnAbsoluteAngle extends CommandBase {
  /**
   * Creates a new TurnAbsoluteAngle.
   */
  private final Chassis m_chassis;
  private final double target_angle;
  public TurnAbsoluteAngle(Chassis chassis, double angle) {
    addRequirements(chassis);
    this.m_chassis = chassis;
    this.target_angle = angle;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //TODO: Test if spin is wrong
    this.m_chassis.drive.arcadeDrive(0, (this.m_chassis.getHeading() < target_angle) ? 1 : -1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.m_chassis.drive.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //TODO: test threshold value here
    return Math.abs(m_chassis.getHeading() - target_angle) < 1;
  }
}
