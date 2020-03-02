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
  // ##
  // ##
  // ##
  // ##
  // ##
  //       Talk about timing
  // ##
  // ##
  private final BallManagement m_ball;
  private final RaspberryPiCommunication m_rasp;
  private final Chassis m_chas;
  private double time;
  /**
   * 
   * @param ball for ball status
   * @param rasp for Image Recognation
   * @param chassis for movement of robot
   */
  public DoBallIntake(BallManagement ball, RaspberryPiCommunication rasp, Chassis chassis) {
    this.m_ball = ball;
    this.m_rasp = rasp;
    this.m_chas = chassis;
    addRequirements(this.m_ball, this.m_rasp, this.m_chas);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.time = 0;
    this.m_ball.set_ball_intake(1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //TODO: Insert area threshold for circle
  
    if (this.m_rasp.getAreaCircle() > 50 && time == 0) {
      this.time = Timer.getFPGATimestamp();
    }
    double[] directions = this.m_rasp.getXYBall();
    this.m_chas.drive.arcadeDrive(directions[0], directions[1]);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.m_ball.set_ball_intake(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //TODO: Test if 1 second is too much, test for optimal time

    return this.time != 0 && Timer.getFPGATimestamp() - time > 1;
  }
}
