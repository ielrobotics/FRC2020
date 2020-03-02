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
import frc.robot.subsystems.RaspberryPiCommunication;

public class ImageRecognitionMotion extends CommandBase {
  /**
   * Creates a new ImageRecognitionMotion.
   */
  private final Chassis m_chassis;
  private final RaspberryPiCommunication m_comms;
  private final BallContainerManagement m_cont;
  private final BallManagement m_ball;
  public ImageRecognitionMotion(Chassis chassis, RaspberryPiCommunication comms, BallContainerManagement cont, BallManagement ball) {
    this.m_chassis = chassis;
    this.m_comms = comms;
    this.m_cont = cont;
    this.m_ball = ball;
    addRequirements(this.m_chassis, this.m_comms, this.m_cont, this.m_ball);

    // Use addRequirements() here to declare subsystem dependencies.
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (this.m_ball.get_ball_count() == 5) {
      double ret[] = this.m_comms.getXYOcta();
      if (ret.length == 3) {
        this.m_chassis.drive.arcadeDrive(ret[0], ret[1]);
      } else {
        //look around
        this.m_chassis.drive.arcadeDrive(0, -1);
      }
    } else {
      double ret[] = this.m_comms.getXYBall();
      if (ret.length == 3) {
        this.m_chassis.drive.arcadeDrive(ret[0], ret[1]);
      } else {
        //look around
        this.m_chassis.drive.arcadeDrive(0, -1);
      }
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
