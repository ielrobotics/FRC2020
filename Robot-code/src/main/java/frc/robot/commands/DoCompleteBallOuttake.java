/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Intake.ball_intake_state;

public class DoCompleteBallOuttake extends CommandBase {
  private double time;
  /**
   * Creates a new DoCompleteBallOuttake.
   */
  private Intake m_b;
  private Arm m_c;
  public DoCompleteBallOuttake(Intake m_ballman, Arm m_ballcon) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_b = m_ballman;
    this.m_c = m_ballcon;
    addRequirements(this.m_b, this.m_c);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.time = Timer.getFPGATimestamp();
    this.m_c.lift_arm();
    this.m_b.set_ball_intake(ball_intake_state.BALL_OUTTAKE);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.m_b.set_ball_intake(ball_intake_state.BALL_STOP);
    this.m_c.release_arm();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Timer.getFPGATimestamp() - time > 5.0;
  }
}
