/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.EncoderSubsystem;
import frc.robot.subsystems.NavX;

public class DriveSetDistance extends CommandBase {
  /**
   * Creates a new DriveSetDistance.
   */
  private final EncoderSubsystem m_encoder;
  private final Chassis m_chassis;
  private final double distance;
  public DriveSetDistance(EncoderSubsystem encoder, Chassis chassis, double dist) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(chassis, encoder);
    m_chassis = chassis;
    m_encoder = encoder;
    distance = dist;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_encoder.reset_drive_encoders();
    m_chassis.drive.arcadeDrive(1, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_chassis.drive.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_encoder.get_forward_distance() > distance;
  }
}
