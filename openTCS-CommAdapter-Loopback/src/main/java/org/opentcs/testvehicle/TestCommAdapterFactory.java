package org.opentcs.testvehicle;

import static java.util.Objects.requireNonNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import org.opentcs.data.model.Vehicle;
import org.opentcs.drivers.vehicle.VehicleCommAdapter;
import org.opentcs.drivers.vehicle.VehicleCommAdapterDescription;
import org.opentcs.drivers.vehicle.VehicleCommAdapterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author zjw
 */
public class TestCommAdapterFactory implements VehicleCommAdapterFactory {
  private static final Logger LOG = LoggerFactory.getLogger(TestCommAdapterFactory.class);
  private TestAdapterComponentsFactory componentsFactory;
  private boolean initialized;

  @Inject
  public TestCommAdapterFactory(TestAdapterComponentsFactory componentsFactory) {
    this.componentsFactory = requireNonNull(componentsFactory, "componentsFactory");
  }

  @Override
  public VehicleCommAdapterDescription getDescription() {
    return new TestDescription();
  }

  @Override
  public boolean providesAdapterFor(@Nonnull Vehicle vehicle) {
    requireNonNull(vehicle, "vehicle");
    return true;
  }

  @Nullable
  @Override
  public VehicleCommAdapter getAdapterFor(@Nonnull Vehicle vehicle) {
    requireNonNull(vehicle, "vehicle");
    return componentsFactory.createCommAdapter(vehicle);
  }

  @Override
  public void initialize() {
    if (initialized) {
      LOG.debug("Already initialized.");
      return;
    }
    initialized = true;
  }

  @Override
  public boolean isInitialized() {
    return initialized;
  }

  @Override
  public void terminate() {
    if (!initialized) {
      LOG.debug("Not initialized.");
      return;
    }
    initialized = false;
  }

}


