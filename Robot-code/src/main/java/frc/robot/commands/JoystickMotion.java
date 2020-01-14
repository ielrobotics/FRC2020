/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.JoystickInterface;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class JoystickMotion extends CommandBase {
  /**
   * Creates a new JoystickMotion.
   */
  private final Chassis m_sub;
  private Joystick joystick;
  public JoystickMotion(Chassis m_chassis, JoystickInterface m_joystick) {
    m_sub = m_chassis;
    addRequirements(m_sub, m_joystick);
    joystick = m_joystick.joystick;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putString("Joystick", joystick.getName());
    m_sub.drive.arcadeDrive(-joystick.getY(), joystick.getX());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}