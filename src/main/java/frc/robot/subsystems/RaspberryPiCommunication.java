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
  private final NetworkTableEntry yaw;
  private final NetworkTableEntry pipeline;
  private final NetworkTableEntry area;
  public RaspberryPiCommunication() {
    this.instance = NetworkTableInstance.getDefault();
    this.table = instance.getTable("chameleon-vision").getSubTable("camera");
    this.yaw = table.getEntry("targetYaw");
    this.pipeline = table.getEntry("pipeline");
    this.area = table.getEntry("area");
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public double[] getXYBall() {
    this.pipeline.setDouble(1.0);
    double val = yaw.getDouble(-999999999.0);
    double[] ret = {((val != -999999999.0) ? 1.0 : 0.0) * val, (val != -999999999.0) ? 1.0 : 0.0};
    return ret;
  }
  public double[] getXYOcta() {
    this.pipeline.setDouble(2.0);
    double val = yaw.getDouble(-999999999.0);
    double[] ret = {((val != -999999999.0) ? 1.0 : 0.0) * val, (val != -999999999.0) ? 1.0 : 0.0};
    return ret;
  }
  public double getAreaCircle() {
    this.pipeline.setDouble(1.0);
    return area.getDouble(-1.0);
  }
  public double getAreaOcta() {
    this.pipeline.setDouble(2.0);
    return area.getDouble(-1.0);
  }
}
