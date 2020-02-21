/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Constants;
public class EncoderSubsystem extends SubsystemBase {
  /**
   * Creates a new EncoderSubsystem.
   */
  public final Encoder[] drive_encoders;
  public final Encoder manipulatorEncoder;
  public EncoderSubsystem() {
    //drive encoder 1: front right motor, 0, 1
    //drive encoder 2: front left motor, 2, 3
    //drive encoder 3: back right motor, 4, 5
    //drive encoder 4: back left motor, 6, 7
    //maniplator encoder: 8, 9
    drive_encoders = new Encoder[4];
    for (int i=0;i<8;i+=2) {
      drive_encoders[i / 2] = new Encoder(i, i + 1);
      //4096 PPR
      //0.1524m diameter
      drive_encoders[i / 2].setDistancePerPulse(0.1514 * Constants.PI / 4096.0);
      drive_encoders[i / 2].setMaxPeriod(0.1);
    }
    manipulatorEncoder = new Encoder(8, 9);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}