package frc.robot.hardware.phoenix6.gyro;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.Pigeon2Configuration;
import com.ctre.phoenix6.hardware.Pigeon2;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.hardware.phoenix6.Phoenix6DeviceID;
import frc.robot.hardware.phoenix6.Phoenix6Util;

import static edu.wpi.first.units.Units.Degrees;

public class Pigeon2Wrapper extends Pigeon2 {

	//@formatter:off
    /*THANKS SO MUCH TO TEAM 4481
    Hier woont een duif
                     ░░░░
              ░░░░░░░░▒▒░░░░░░
          ░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░
        ░░░░▒▒▒▒▒▒▒▒▒▒░░░░▒▒▒▒▒▒░░
      ░░░░▒▒▒▒▒▒▒▒▒▒░░▒▒▒▒░░▒▒▒▒▒▒
      ░░░░▒▒▒▒▒▒▒▒▒▒░░  ▓▓▒▒░░▒▒▒▒▒▒
      ▒▒░░░░▒▒▒▒▒▒▒▒░░▓▓▓▓▓▓░░▒▒▒▒▒▒▒▒
    ░░░░░░░░░░░░▒▒▒▒░░▒▒▓▓▓▓░░▒▒▒▒▒▒▒▒
  ░░░░▒▒▓▓░░░░░░▒▒▒▒▒▒░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒
  ░░▒▒▒▒▓▓▒▒░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒
  ▒▒░░▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░
  ░░░░▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒
  ▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓
░░░░▒▒▓▓▓▓▓▓████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░
░░▒▒▓▓████████▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓
  ▒▒          ▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓
              ▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒
              ░░▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓
                ▓▓▒▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓
                ▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒
                ▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░
                ▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒
              ░░▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒
              ░░▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒
              ▒▒▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒
              ▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒
              ▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░▒▒▒▒▒▒▓▓▓▓▓▓▓▓░░
              ▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒░░░░░░░░
            ▒▒▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▒▒▒▒▓▓▒▒▒▒▓▓▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░░░
            ▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▓▓▓▓▒▒░░░░░░░░░░░░░░░░░░░░░░░░▒▒░░
          ▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▒▒▒▒▓▓▒▒▓▓▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▓▓▒▒░░
          ▓▓▓▓▓▓▒▒▒▒▒▒▓▓▒▒▓▓▒▒▒▒▒▒▓▓▒▒▒▒▒▒▓▓▓▓▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓▓▓▓▓▒▒░░                                                        ░░
        ░░▓▓▓▓▒▒▓▓▒▒▓▓▓▓▒▒▓▓▒▒▒▒▒▒▓▓▒▒▒▒▒▒▓▓▓▓▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓▓▓▓▓▓▓░░░░░░░░░░                                      ▒▒██▓▓▓▓▓▓
        ▓▓▓▓▓▓▒▒▓▓▒▒▓▓▓▓▓▓▓▓▒▒▓▓▒▒▓▓▒▒▒▒▒▒▓▓▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▓▓▓▓░░░░░░░░░░░░▓▓▒▒░░░░                  ░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓
        ▓▓▓▓▒▒▓▓▓▓▒▒▓▓▓▓▓▓▓▓▒▒▓▓▒▒▓▓▒▒▓▓▒▒▓▓▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░    ░░░░░░▒▒▒▒▓▓▓▓▓▓▒▒░░░░░░░░░░░░▒▒▓▓▓▓░░░░░░░░░░░░  ▒▒▓▓▓▓▓▓▓▓▓▓▓▓▒▒▓▓▒▒
        ▓▓▓▓▒▒▓▓▓▓▓▓▓▓▒▒▓▓▓▓▓▓▒▒▒▒▓▓▒▒▒▒▓▓▓▓▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▓▓▓▓░░░░░░░░  ░░░░  ░░▓▓▒▒░░░░░░░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓▒▒▓▓██░░
      ░░▓▓▒▒▒▒▓▓▒▒▓▓▓▓▒▒▓▓▒▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░░░░  ░░░░░░░░░░░░░░░░░░░░░░▒▒▓▓▒▒░░░░░░░░░░░░░░▒▒▓▓░░░░░░░░░░░░▒▒▓▓▓▓▓▓▓▓▒▒▓▓▓▓▓▓
      ░░▓▓▒▒▒▒▓▓▒▒▓▓▒▒▒▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░  ▓▓▒▒░░░░░░░░░░░░▒▒▓▓▓▓▒▒▒▒░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓
        ░░▒▒▒▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▓▓▓▓░░░░░░░░░░░░░░▒▒▓▓▓▓▓▓░░▒▒██▓▓▓▓▓▓▓▓▓▓██▓▓████▓▓▓▓▒▒
          ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░░░░░▒▒▓▓▓▓░░░░░░▓▓▓▓▓▓████▓▓▓▓▓▓▓▓▓▓▒▒
          ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▒▒▒▒▓▓▓▓▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░  ░░▓▓▓▓▓▓▓▓▓▓░░░░░░░░▒▒▓▓▓▓░░░░░░▓▓▓▓██▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒
          ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▒▒▒▒▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▓▓▒▒▒▒░░░░░░░░░░░░▓▓▓▓▓▓▓▓▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒
          ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒  ░░░░░░░░  ░░░░░░░░░░░░░░░░▒▒▓▓▓▓▓▓▓▓▓▓░░░░░░░░░░░░░░▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒
            ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒░░░░▒▒░░░░░░░░░░░░░░░░░░░░░░░░▒▒▓▓▓▓░░░░░░░░░░░░░░░░░░▒▒▓▓▓▓██▓▓▓▓▓▓▓▓██▒▒
            ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▓▓▒▒▒▒▒▒▓▓▓▓░░  ░░░░░░░░░░░░░░░░░░▒▒░░░░░░░░▓▓▓▓▓▓▓▓░░░░░░░░░░░░▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒
            ░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▒▒░░░░▒▒░░░░░░░░░░░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓▒▒░░░░░░░░▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░
              ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▒▒░░░░░░▒▒░░░░░░░░░░░░░░▓▓▓▓▒▒░░░░░░▒▒▓▓████▓▓▓▓████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒░░
              ░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓░░░░░░▓▓▓▓▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░▒▒▓▓▓▓▓▓████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░
                ░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒░░░░▓▓▓▓▓▓▒▒▒▒░░░░░░░░░░░░░░░░▒▒██▓▓▓▓▓▓▓▓▓▓██▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▓▓▒▒
                  ░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒░░░░░░░░░░░░▒▒▒▒▓▓██▓▓██▓▓▓▓▓▓▓▓▓▓▒▒▓▓██▓▓██▓▓▓▓▒▒░░░░
                    ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▓▓██
                      ░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓  ░░▒▒▓▓▓▓▓▓▒▒▓▓▓▓▓▓▓▓██
                          ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▓▓▓▓▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░              ░░░░▒▒
                              ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒
                                  ▒▒▓▓▒▒▒▒▒▒▒▒▒▒▓▓▒▒▓▓▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒
                                        ░░▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒
                                                      ▒▒▒▒▒▒▒▒▒▒▒▒      ▒▒▒▒▒▒▒▒▒▒▒▒
                                                      ░░▒▒▒▒▒▒▒▒▒▒      ░░▒▒░░▒▒▒▒▒▒
                                                        ░░▒▒▒▒▒▒▒▒        ░░░░▒▒▒▒▒▒
                                                        ░░▒▒▒▒▒▒          ░░░░▒▒▒▒▒▒
                                                          ▒▒▒▒▒▒            ░░▒▒▒▒▒▒
                                                          ▒▒▓▓              ▒▒▒▒▒▒░░
                                                          ▒▒░░              ▒▒▒▒▒▒
                                                        ░░▒▒                ▒▒▒▒
                                                      ░░▒▒                  ▒▒░░
                                        ▒▒▒▒░░░░░░░░▒▒▒▒▒▒▒▒▓▓▓▓          ▒▒▒▒
                                      ▒▒▒▒▒▒▒▒▒▒░░▒▒▒▒▒▒▒▒▒▒░░            ░░▒▒░░
                                          ░░░░▒▒▒▒▒▒░░▒▒                ▒▒▒▒▒▒▒▒▒▒██▒▒
                                        ▒▒▒▒▒▒░░  ░░▒▒░░      ░░▒▒░░▒▒▒▒░░▒▒▒▒▒▒░░
                                      ▓▓▓▓░░      ░░▒▒░░  ░░▒▒▒▒▒▒▒▒░░▒▒▒▒░░▒▒▒▒
                                      ░░          ▒▒▓▓    ▓▓    ░░▒▒▒▒░░░░▒▒░░▒▒
                                                  ░░▒▒        ▒▒▒▒          ▒▒▒▒░░
                                                  ░░        ██▒▒              ▒▒▒▒
                                                            ▒▒

     */
    //@formatter:on

	private static final int DEFAULT_CONFIG_NUMBER_OF_TRIES = 1;


	private double rollOffSetDegrees;

	private double pitchOffSetDegrees;

	public Pigeon2Wrapper(int id) {
		this(new Phoenix6DeviceID(id));
	}

	public Pigeon2Wrapper(Phoenix6DeviceID deviceID) {
		super(deviceID.id(), deviceID.busChain().getChainName());
		this.rollOffSetDegrees = 0;
		this.pitchOffSetDegrees = 0;
	}

	public StatusCode applyConfiguration(Pigeon2Configuration configuration, int numberOfTries) {
		return Phoenix6Util.checkWithRetry(() -> getConfigurator().apply(configuration), numberOfTries);
	}

	public StatusCode applyConfiguration(Pigeon2Configuration configuration) {
		return applyConfiguration(configuration, DEFAULT_CONFIG_NUMBER_OF_TRIES);
	}

	public StatusCode setYaw(Rotation2d newYaw) {
		return super.setYaw(newYaw.getDegrees());
	}

	public Rotation2d getRotation2dYaw() {
		return Rotation2d.fromDegrees(getYaw().getValue().in(Degrees));
	}

	/**
	 * This function only set pitch for the function "getAdjustedRoll". If you want to use the pitch signal you need to use "getRoll" but note to
	 * yourself that it won't be affected by "setRoll". If for some reason you want to get the needed offset to make the returned value affected
	 * by resets use "getRollOffset".
	 *
	 * @param newRoll - the wanted roll of the gyro to have
	 */
	public void setRoll(Rotation2d newRoll) {
		rollOffSetDegrees = calculateOffset(newRoll.getDegrees(), getRoll().getValue().in(Degrees));
	}

	/**
	 * This function only set pitch for the function "getAdjustedPitch". If you want to use the pitch signal you need to use "getPitch" but note
	 * to yourself that it won't be affected by "setPitch". If for some reason you want to get the needed offset to make the returned value
	 * affected by resets use "getPitchOffset".
	 *
	 * @param newPitch - the wanted pitch of the gyro to have
	 */
	public void setPitch(Rotation2d newPitch) {
		pitchOffSetDegrees = calculateOffset(newPitch.getDegrees(), getPitch().getValue().in(Degrees));
	}

	public Rotation2d getRollOffSet() {
		return Rotation2d.fromDegrees(rollOffSetDegrees);
	}

	public Rotation2d getPitchOffSet() {
		return Rotation2d.fromDegrees(pitchOffSetDegrees);
	}

	public Rotation2d getAdjustedRoll() {
		return Rotation2d.fromDegrees(getRoll().getValue().in(Degrees) + rollOffSetDegrees);
	}

	public Rotation2d getAdjustedPitch() {
		return Rotation2d.fromDegrees(getPitch().getValue().in(Degrees) + pitchOffSetDegrees);
	}

	/**
	 * @return - the yaw value ranged between -180 , 180
	 */
	public Rotation2d getRangedYaw() {
		return rangeAngle(Rotation2d.fromDegrees(getYaw().getValue().in(Degrees)));
	}

	/**
	 * @return - the unadjusted roll value ranged between -180 , 180
	 */
	public Rotation2d getRangedRoll() {
		return rangeAngle(Rotation2d.fromDegrees(getRoll().getValue().in(Degrees)));
	}

	/**
	 * @return - the unadjusted pitch value ranged between -180 , 180
	 */
	public Rotation2d getRangedPitch() {
		return rangeAngle(Rotation2d.fromDegrees(getPitch().getValue().in(Degrees)));
	}

	/**
	 * @return - the adjusted roll value ranged between -180 , 180
	 */
	public Rotation2d getAdjustedRangedRoll() {
		return rangeAngle(getAdjustedRoll());
	}

	/**
	 * @return - the adjusted pitch value ranged between -180 , 180
	 */
	public Rotation2d getAdjustedRangedPitch() {
		return rangeAngle(getAdjustedPitch());
	}

	/**
	 * @param newAngleDegrees     - the wanted angle in degrees
	 * @param currentAngleDegrees - the current angle in degrees
	 * @return the offset
	 */
	private double calculateOffset(double newAngleDegrees, double currentAngleDegrees) {
		return newAngleDegrees - currentAngleDegrees;
	}

	/**
	 * Range angle between -180, 180
	 *
	 * @param angle - the angle to range
	 * @return the ranged angle
	 */
	private Rotation2d rangeAngle(Rotation2d angle) {
		return Rotation2d.fromRadians(MathUtil.angleModulus(angle.getRadians()));
	}

}
