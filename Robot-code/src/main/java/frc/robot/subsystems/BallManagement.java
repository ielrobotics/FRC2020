/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallManagement extends SubsystemBase {
  /**
   * Creates a new BallManagement.
   */
  private final VictorSP ball_manipulation_motor;
  public BallManagement() {
    this.ball_count = 3;
    this.ball_manipulation_motor = new VictorSP(1);
  }

  @Override
  public void periodic() {
    this.ball_manipulation_motor.feed();
    // This method will be called once per scheduler run
  }
  private int ball_count;
  public int get_ball_count() {
    return this.ball_count;
  }
  /**
   * 
   * @param value 1 for intake, -1 for outtake, 0 for no take
   */
  public void set_ball_intake(int value) {
    if (this.ball_count < 5 && value == 1) {
      this.ball_manipulation_motor.set(0.0);
      return;
    }
    this.ball_manipulation_motor.set(value);
  }
  /**
   * Resets ball count.
   */
  public void reset_ball() {
    this.ball_count = 0;
  }
  /**
   * Increments ball count.
   */
  public void increment_ball() {
    if (this.ball_count == 5) {
      System.out.print("WARNING: Too many balls collected!");
      return;
    }
    this.ball_count++;
    return;
  }
}
