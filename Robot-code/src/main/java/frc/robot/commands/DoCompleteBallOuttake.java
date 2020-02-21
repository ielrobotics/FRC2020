/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallContainerManagement;
import frc.robot.subsystems.BallManagement;

public class DoCompleteBallOuttake extends CommandBase {
  private double time;
  /**
   * Creates a new DoCompleteBallOuttake.
   */
  private BallManagement m_b;
  private BallContainerManagement m_c;
  public DoCompleteBallOuttake(BallManagement m_ballman, BallContainerManagement c) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_b = m_ballman;
    m_c = c;
    addRequirements(m_b, m_c);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time = Timer.getFPGATimestamp();
    m_c.lift_arm();
    m_b.set_ball_intake(-1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_b.reset_ball();
    m_b.set_ball_intake(0);
    m_c.release_arm();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Timer.getFPGATimestamp() - time > 5.0;
  }
}
