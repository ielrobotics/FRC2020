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
import frc.robot.subsystems.RaspberryPiCommunication;

public class ImageRecognitionMotion extends CommandBase {
  /**
   * Creates a new ImageRecognitionMotion.
   */
  private final Chassis m_chassis;
  private final RaspberryPiCommunication m_comms;
  private final BallStatus m_ball;
  public ImageRecognitionMotion(Chassis chassis, RaspberryPiCommunication comms, BallStatus m_b) {
    m_chassis = chassis;
    m_comms = comms;
    m_ball = m_b;
    addRequirements(m_chassis, m_comms, m_ball);

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double ret[] = m_comms.getXYBall();
    if (ret.length == 3) {
      m_chassis.drive.arcadeDrive(ret[0], ret[1]);
    } else {
      m_chassis.drive.arcadeDrive(0, 0);
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
