/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallContainerManagement;
import frc.robot.subsystems.BallManagement;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.JoystickInterface;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class JoystickMotion extends CommandBase {
  /**
   * Creates a new JoystickMotion.
   */
  private final Chassis m_sub;
  private final Joystick joystick;
  private final BallManagement m_ball;
  private final BallContainerManagement m_cont;
  private final DoCompleteBallOuttake c;
  public JoystickMotion(Chassis m_chassis, JoystickInterface m_joystick, BallManagement ball, BallContainerManagement cont) {
    m_sub = m_chassis;
    m_cont = cont;
    m_ball = ball;
    addRequirements(m_sub, m_joystick, m_cont, m_ball);
    joystick = m_joystick.joystick;
    c = new DoCompleteBallOuttake(m_ball, m_cont);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }
private final double POVMagicValues[][] = {{1, 0.5}, {1,1}, {0,1}, {-1,-1}, {-1,0.5}, {-1,1}, {0,-1}, {1,-1}};
double turboamount;
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putString("Joystick", joystick.getName());
    //joystick turbo key
    //Turbo key
    if(joystick.getRawButton(9)){
      turboamount = 0.2;
    } else {
      turboamount = joystick.getRawAxis(3);
      if (!joystick.getRawButton(5)) {
        turboamount = turboamount * 0.5 + 0.5;
      }
    }
    /*

    disable turbo if unwanted
    if (joystick.getRawButtonReleased(5)) {
      turboamount = 0.7;
    }
    */

    //reverse
    double magiclist[] = POVMagicValues[joystick.getPOV() / 45];
    if (joystick.getRawButton(6)) {
      m_sub.drive.arcadeDrive(magiclist[0], -magiclist[1]);
      turboamount = - turboamount;
    } else {
      m_sub.drive.arcadeDrive(magiclist[0], magiclist[1]);
    }
    m_sub.drive.setMaxOutput(turboamount);
    //Ball throw key (throw constantly)
    if (joystick.getRawButton(2)) {
      c.initialize();
      while (!c.isFinished()) {
        c.execute();
      }
      c.end(false);
    }
    if (joystick.getRawButtonPressed(1)) {
      m_cont.lift_arm();
      m_ball.set_ball_intake(-1);
    }
    if (joystick.getRawButtonReleased(1)) {
      m_ball.set_ball_intake(0);
      m_cont.release_arm();
      m_ball.reset_ball();
    }
    if (!joystick.getRawButton(1) && joystick.getRawButton(2)) {
      m_ball.set_ball_intake(1);
    } else if (!joystick.getRawButton(1)) {
      m_ball.set_ball_intake(0);
    }

  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end. test commit
  @Override
  public boolean isFinished() {
    return false;
  }
}