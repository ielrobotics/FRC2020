/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
public class Chassis extends SubsystemBase {
  /**
   * Creates a new Chassis.
   */
  public final DifferentialDrive drive;
  public final WPI_TalonSRX right_talon;
  public final WPI_TalonSRX left_talon;
  public Chassis(int frontLeft,int frontRight,int backLeft,int backRight) {
    //3 4 sag 1 2 sol
    right_talon = new WPI_TalonSRX(frontRight);
    left_talon = new WPI_TalonSRX(frontLeft);
    right_talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    left_talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    drive = new DifferentialDrive(new SpeedControllerGroup(right_talon,new WPI_VictorSPX(backRight)),new SpeedControllerGroup(new SpeedControllerGroup(left_talon ,new WPI_VictorSPX(backLeft))));
    drive.setMaxOutput(0.7);
  }
  @Override
  public void periodic() {
    
    drive.feed();
    // This method will be called once per s<p>heduler run
  }
  
  public int get_right_sensor() {
    //TODO: multiply with constant to convert to meters or something
    return right_talon.getSelectedSensorPosition();
  }
  public int get_left_sensor() {
    return left_talon.getSelectedSensorPosition();
  }
  public double get_forward_distance() {
    return (left_talon.getSelectedSensorPosition() + right_talon.getSelectedSensorPosition()) / 2.0;
  }
}