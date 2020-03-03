/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.RaspberryPiCommunication;

public class AlignWithOcta extends CommandBase {

  /**
   * Creates a new AlignWithOcta.
   */
  /* 

    This command is for detecting octagon under goal
    Using for autonomus and helper
  */
  
  private final RaspberryPiCommunication m_rasp;
  private final Chassis m_chas;
  /**
   * 
   * @param chassis for movement
   * @param rasp for Image Recognation
   */
  public AlignWithOcta(Chassis chassis, RaspberryPiCommunication rasp) {
    this.m_chas = chassis;
    this.m_rasp = rasp;
    addRequirements(m_chas, m_rasp);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //TODO: Check shape of hex to dock properly by determining the alignment of the hex
    //Here comes coordinat of octagon
    double[] directions = m_rasp.getXYOcta();
    this.m_chas.drive.arcadeDrive(directions[0], directions[1]);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //TODO: Add magic value for hex area threshold
    //TODO: Check the shape of the detected hex to determine if fully docked
    return m_rasp.getAreaOcta() > 50;
  }
}
