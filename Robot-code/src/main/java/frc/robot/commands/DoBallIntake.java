/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallManagement;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.RaspberryPiCommunication;

public class DoBallIntake extends CommandBase {
  /**
   * Creates a new DoBallIntake.
   */
  private final BallManagement m_ball;
  private final RaspberryPiCommunication m_rasp;
  private final Chassis m_chas;
  private double time;
  public DoBallIntake(BallManagement ball, RaspberryPiCommunication rasp, Chassis ch) {
    m_ball = ball;
    m_rasp = rasp;
    m_chas = ch;
    addRequirements(m_ball, m_rasp, m_chas);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time = 0;
    m_ball.set_ball_intake(1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //TODO: Insert area threshold for circle
  
    if (m_rasp.getAreaCircle() > 50 && time == 0) {
      time = Timer.getFPGATimestamp();
    }
    double[] directions = m_rasp.getXYBall();
    m_chas.drive.arcadeDrive(directions[0], directions[1]);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_ball.set_ball_intake(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //TODO: Test if 1 second is too much, test for optimal time

    return time != 0 && Timer.getFPGATimestamp() - time > 1;
  }
}
