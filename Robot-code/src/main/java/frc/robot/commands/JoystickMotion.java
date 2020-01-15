/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallStatus;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.JoystickInterface;

import java.util.concurrent.TimeUnit;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class JoystickMotion extends CommandBase {
  /**
   * Creates a new JoystickMotion.
   */
  private final Chassis m_sub;
  private final BallStatus m_ball;
  private final Joystick joystick;
  public JoystickMotion(Chassis m_chassis, JoystickInterface m_joystick, BallStatus m_b) {
    m_sub = m_chassis;
    m_ball = m_b;
    addRequirements(m_sub, m_joystick, m_b);
    joystick = m_joystick.joystick;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }
double turboamount;
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putString("Joystick", joystick.getName());
    //joystick turbo key
    
    //Turbo key
    if (joystick.getRawButton(5)) {
      turboamount = joystick.getRawAxis(3);
    } else {
      turboamount = 0.7;
    }
    /*
    disable turbo if unwanted
    if (joystick.getRawButtonReleased(5)) {
      turboamount = 0.7;
    }
    */

    //reverse
    if (joystick.getRawButton(6)) {
      m_sub.drive.arcadeDrive(-joystick.getY(), -joystick.getX());
      turboamount = - turboamount;
      m_sub.drive.setMaxOutput(turboamount);
    } else {
      m_sub.drive.arcadeDrive(-joystick.getY(), joystick.getX());
      m_sub.drive.setMaxOutput(turboamount);
    }
      


    if(joystick.getPOV() == 0){
      m_sub.drive.arcadeDrive(1, 0.5);
      
    }

    if(joystick.getPOV() == 315){
      m_sub.drive.arcadeDrive(1, -1);
      
    }
    if(joystick.getPOV() == 270){
      m_sub.drive.arcadeDrive(0, -1);
      
    }
    if(joystick.getPOV() == 135){
      m_sub.drive.arcadeDrive(-1, -1);
      
    }
    if(joystick.getPOV() == 180){
      m_sub.drive.arcadeDrive(-1, 0.5);
      
    }
    if(joystick.getPOV() == 45){
      m_sub.drive.arcadeDrive(1, 1);
      
    }
    if(joystick.getPOV() == 90){
      m_sub.drive.arcadeDrive(0, 1);
      
    }
    if(joystick.getPOV() == 225){
      m_sub.drive.arcadeDrive(-1, 1);
      
    }
  

    //Ball throw key (throw constantly)
    if (joystick.getRawButton(2)) {
      m_ball.ballThrow();
    }
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