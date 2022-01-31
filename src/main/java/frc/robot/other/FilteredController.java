// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.other;

import edu.wpi.first.wpilibj.XboxController;

/** Add your docs here. */
public class FilteredController {
    private XboxController controller;

    public FilteredController(XboxController controller) {
        this.controller = controller;
    }

    /**
     * Gets the filtered X input for the given stick.
     * 
     * @param deadzone
     * @return double
     */
    public double getXLeft(double deadzone) {
        return new InputFilter(controller.getLeftX()).getFiltered(deadzone);
    }

    /**
     * Gets the filtered X input for the given stick.
     * 
     * @param deadzone
     * @return double
     */
    public double getXRight(double deadzone) {
        return new InputFilter(controller.getRightX()).getFiltered(deadzone);
    }


    /**
     * Gets the filtered Y input for the given stick.
     * 
     * @param deadzone
     * @return double
     */
    public double getYLeft(double deadzone) {
        return new InputFilter(controller.getLeftY()).getFiltered(deadzone);
    }

    /**
     * Gets the filtered Y input for the given stick.
     * 
     * @param deadzone
     * @return double
     */
    public double getYRight(double deadzone) {
        return new InputFilter(controller.getRightY()).getFiltered(deadzone);
    }


    /**
     * Gets the filtered trigger input for the given trigger.
     * 
     * @param deadzone
     * @return double
     */
    public double getTriggerLeft(double deadzone) {
        return new InputFilter(controller.getLeftTriggerAxis()).getFiltered(deadzone);
    }
    
    /**
     * Gets the filtered trigger input for the given trigger.
     * 
     * @param deadzone
     * @return double
     */
    public double getTriggerRight(double deadzone) {
        return new InputFilter(controller.getRightTriggerAxis()).getFiltered(deadzone);
    }

    
    /** 
     * @return boolean
     */
    public boolean getPOVPressed(){
        return controller.getPOV() != -1;
    }

    
    /** 
     * @return int
     */
    public int getPOVButton() {
        int POVButton;
        if (controller.getPOV() != -1) {
            switch (controller.getPOV()) {
                case 0:
                    POVButton = 8;
                    break;

                case 45:
                    POVButton = 9;
                    break;

                case 90:
                    POVButton = 6;
                    break;

                case 135:
                    POVButton = 3;
                    break;

                case 180:
                    POVButton = 2;
                    break;

                case 225:
                    POVButton = 1;
                    break;

                case 270:
                    POVButton = 4;
                    break;

                case 315:
                    POVButton = 7;
                    break;

                case 360:
                    POVButton = 8;
                    break;
                default:
                    POVButton = 0;
            }
            return POVButton;
        } else {
            return 0;
        }
    }
}
