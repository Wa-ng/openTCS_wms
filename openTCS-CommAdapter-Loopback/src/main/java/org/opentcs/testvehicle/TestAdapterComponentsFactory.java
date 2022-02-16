package org.opentcs.testvehicle;

import org.opentcs.data.model.Vehicle;

public interface TestAdapterComponentsFactory {
  TestCommAdapter createCommAdapter(Vehicle vehicle);
}
