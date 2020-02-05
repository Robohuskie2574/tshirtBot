/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team2574;


import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    CANJaguar Jaguar1;
    CANJaguar Jaguar2;
    CANJaguar Jaguar3;
    CANJaguar Jaguar4;
    
    Relay Relay1;
    Relay Relay2;
    Relay Relay3;
    Relay Relay4;
 
    
    Talon Talon1;
    
    Joystick Joystick1;
     
    RobotDrive Drive1;
    
    boolean currentlyFiring, cylinder1, cylinder2, cylinder3, cylinder4;
    
    
    public void robotInit() {
        try {
            Jaguar1 = new CANJaguar(1);
            Jaguar2 = new CANJaguar(2);
            Jaguar3 = new CANJaguar(3);
            Jaguar4 = new CANJaguar(4);
            
           
        } catch(Exception e) {
            e.printStackTrace();
        }
            Relay1 = new Relay(2);
            Relay2 = new Relay(3);
            Relay3 = new Relay(4);
            Relay4 = new Relay(5);
            
            Joystick1 = new Joystick(1);
            Drive1 = new RobotDrive(Jaguar2,Jaguar4,Jaguar1,Jaguar3);
            Drive1.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, false);
            Drive1.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);
            Drive1.setInvertedMotor(RobotDrive.MotorType.kFrontRight, false);
            Drive1.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);
            
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
       

       if(currentlyFiring) {
           //TODO timing logic here 
           
       } else {
           boolean safety = Joystick1.getRawButton(1);
           
           if(safety) {
               //safety disengaged, disable driving
               Drive1.arcadeDrive(0,0);
           } else {
               //not firing or preparing to fire, drive normally
               Drive1.arcadeDrive(-Joystick1.getRawAxis(2),-Joystick1.getRawAxis(3));
           }
          
           if (safety && Joystick1.getRawButton(5)){
               Relay1.set(Relay.Value.kForward);
               
           } else {
               Relay1.set(Relay.Value.kOff);
           }
           
           if (safety && Joystick1.getRawButton(3)){
               Relay2.set(Relay.Value.kForward); 
           } else {
               Relay2.set(Relay.Value.kOff);
           }
           
           if (safety && Joystick1.getRawButton(4)){
               Relay3.set(Relay.Value.kForward); 
           } else {
               Relay3.set(Relay.Value.kOff);
           }
           
           if (safety && Joystick1.getRawButton(6)){
               Relay4.set(Relay.Value.kForward); 
           } else {
               Relay4.set(Relay.Value.kOff);
           }
          
           
       }
       
       
       
       

       
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    }
    

