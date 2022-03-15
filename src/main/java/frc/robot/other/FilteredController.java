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
     * Returns if the right trigger is active or not within a deadzone
     * 
     * @param deadzone
     * @return boolean
     */
    public boolean getRightTriggerActive(double deadzone) {
        if (controller.getRightTriggerAxis() > deadzone) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Returns if the left trigger is active or not within a deadzone
     * 
     * @param deadzone
     * @return boolean
     */
    public boolean getLeftTriggerActive(double deadzone) {
        if (controller.getLeftTriggerAxis() > deadzone) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Returns whether or not the right trigger is pressed or not with a hard-coded deadzone
     * 
     * @return boolean
     */
    public boolean getRightTriggerActive() {
        if (controller.getRightTriggerAxis() > .2) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Returns whether or not the left trigger is pressed or not with a hard-coded deadzone
     * 
     * @return boolean
     */
    public boolean getLeftTriggerActive() {
        if (controller.getLeftTriggerAxis() > .2) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Returns if the A button is pressed or not
     * 
     * @return boolean
     */
    public boolean getAButton(){
        if (controller.getAButton()) {
            return true;
        } else {
            return false;
        }
    }
    
    /** 
     * Returns if any POVButton is pressed or not
     * 
     * @return boolean
     */
    public boolean getPOVPressed() {
        return controller.getPOV() != -1;
    }

    /** 
     * Returns depending on which POVButton is pressed
     * 
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

    /**
     * Returns whether the left bumper is pressed or not
     * 
     * @return boolean
     */
    public boolean getLeftBumper() {
        return controller.getLeftBumper();
    }

    /**
     * Returns whether the right bumper is pressed or not
     * @return boolean
     */
    public boolean getRightBumper() {
        return controller.getRightBumper();
    }
}
