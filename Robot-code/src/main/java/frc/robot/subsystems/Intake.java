/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  /**
   * Creates a new BallManagement.
   */
  private final VictorSP intake_motor;
  public Intake() {
    this.intake_motor = new VictorSP(1);
  }
  public enum ball_intake_state {
    BALL_INTAKE,
    BALL_OUTTAKE,
    BALL_STOP
  }
  @Override
  public void periodic() {
    this.intake_motor.feed();
    // This method will be called once per scheduler run
  }
  /**
   * Sets the ball intake motor state.
   * @param value The state to set the ball intake motor to.
   */
  public void set_ball_intake(ball_intake_state value) {
    //TODO: test if these are backwards
    switch (value) {
      case BALL_INTAKE:
        intake_motor.set(1.0);
        break;
      case BALL_OUTTAKE:
        intake_motor.set(-1.0);
        break;
      case BALL_STOP:
        intake_motor.set(0.0);
        break;
    }
  }
}
