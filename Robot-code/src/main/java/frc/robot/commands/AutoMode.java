/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.BallContainerManagement;
import frc.robot.subsystems.BallManagement;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.RaspberryPiCommunication;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoMode extends SequentialCommandGroup {
  /**
   * This variant picks up the five power cells at its side of the rendezvous square after running AutoModeBase.
   * @param m_cont Ball Container subsystem
   * @param m_ball Ball Manipulator subsystem
   * @param m_chassis Chassis subsystem
   * @param m_navx NavX subsystem
   * @param m_rasp Raspberry Pi subsystem
   * @param x The distance between the robot's initial position on the alliance line and the edge of the wall closest to the target zone
   */
  public AutoMode(BallContainerManagement m_cont, BallManagement m_ball, Chassis m_chassis, NavX m_navx, RaspberryPiCommunication m_rasp, double x) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new AutoModeBase(m_cont, m_ball, m_chassis, m_navx, m_rasp, x)
      
    );
    //TODO: Finish adding Auto Mode logic here
  }
}
