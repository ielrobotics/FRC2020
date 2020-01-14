/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.*;
public class RaspberryPiCommunication extends SubsystemBase {
  /**
   * Creates a new RaspberryPiCommunication.
   */
  private final NetworkTableInstance instance;
  private final NetworkTable table;
  private final NetworkTableEntry imageProcessing;
  public RaspberryPiCommunication() {
    instance = NetworkTableInstance.getDefault();
    table = instance.getTable("datatable");
    imageProcessing = table.getEntry("image-processing-commands");
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public double[] getXY() {
    double def[] = {-1,-1,-1};
    return imageProcessing.getDoubleArray(def);
  }
}