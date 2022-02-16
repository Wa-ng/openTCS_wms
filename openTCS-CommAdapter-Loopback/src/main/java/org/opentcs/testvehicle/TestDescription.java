package org.opentcs.testvehicle;

import org.opentcs.drivers.vehicle.VehicleCommAdapterDescription;

import java.util.ResourceBundle;

import static org.opentcs.virtualvehicle.I18nLoopbackCommAdapter.BUNDLE_PATH1;

public class TestDescription extends VehicleCommAdapterDescription {
  @Override
  public String getDescription() {
    return ResourceBundle.getBundle(BUNDLE_PATH1)
        .getString("AdapterFactoryDescription");
  }

  @Override
  public boolean isSimVehicleCommAdapter() {
    return true;
  }
}
