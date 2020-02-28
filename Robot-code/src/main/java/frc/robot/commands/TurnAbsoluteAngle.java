/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.NavX;
public class TurnAbsoluteAngle extends CommandBase {
  /**
   * Creates a new TurnAbsoluteAngle.
   */
  private final NavX m_navx;
  private final Chassis m_chassis;
  private final double target_angle;
  public TurnAbsoluteAngle(NavX navx, Chassis chassis, double angle) {
    addRequirements(navx, chassis);
    m_navx = navx;
    m_chassis = chassis;
    target_angle = angle;
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
    m_chassis.drive.arcadeDrive(0, (m_navx.ah.getAngle() < target_angle) ? 1 : -1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_chassis.drive.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //TODO: test threshold value here
    return Math.abs(m_navx.ah.getAngle() - target_angle) < 1;
  }
}
