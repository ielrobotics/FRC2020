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
public class AutoModeBase extends SequentialCommandGroup {
  /**
   * The start of the autonomous routine. The robot is expected to branch off into a 
   * 
   * @param m_cont Ball Container subsystem
   * @param m_ball Ball Manipulator subsystem
   * @param m_chassis Chassis subsystem
   * @param m_navx NavX subsystem
   * @param m_rasp Raspberry Pi subsystem
   * @param x The distance between the robot's initial position on the alliance line and the edge of the wall closest to the target zone
   */
  public AutoModeBase(final BallContainerManagement m_cont, final BallManagement m_ball, final Chassis m_chassis,
      final NavX m_navx, final RaspberryPiCommunication m_rasp, final double x) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new TurnRelativeAngle(m_navx, m_chassis, Math.asin(305.0 * 305.0 / ((x - 164.4) * (x - 164.4) + 305.0 * 305.0)) * (x < 164.4 ? -1.0 : 1.0)),
      new DriveSetDistance(m_chassis, Math.sqrt((x - 164.4) * (x - 164.4) + 305.0 * 305.0)),
      new AlignWithOcta(m_chassis, m_rasp),
      new DoCompleteBallOuttake(m_ball, m_cont)
    );
    
    //TODO: I do NOT think the first TurnRelativeAngle is going to work. Actually test this i beg you.
    //TODO: Test if the AlignWithOcta dealigns the robot too much.
  }
}