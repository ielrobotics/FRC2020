/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;
public class DriveSetDistance extends CommandBase {
  /**
   * Creates a new DriveSetDistance.
   */
  private final Chassis m_chassis;
  private final double distance;
  
  public DriveSetDistance(Chassis chassis, double dist) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_chassis = chassis;
    this.distance = dist;
    addRequirements(this.m_chassis);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_chassis.front_left_talon.setSelectedSensorPosition(0);
    m_chassis.front_right_talon.setSelectedSensorPosition(0);
    m_chassis.left_pid.setSetpoint(distance);
    m_chassis.right_pid.setSetpoint(distance);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_chassis.front_left_talon.set(m_chassis.get_left_pid());
    m_chassis.front_right_talon.set(m_chassis.get_right_pid());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.m_chassis.drive.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return this.m_chassis.left_pid.atSetpoint() && this.m_chassis.right_pid.atSetpoint();
  }
}
