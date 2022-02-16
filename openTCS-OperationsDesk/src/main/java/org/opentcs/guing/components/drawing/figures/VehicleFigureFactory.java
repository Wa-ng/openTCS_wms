/**
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.guing.components.drawing.figures;

import org.opentcs.guing.model.elements.VehicleModel;

/**
 *
 * @author Stefan Walter (Fraunhofer IML)
 */
public interface VehicleFigureFactory
    extends FigureFactory {

  VehicleFigure createVehicleFigure(VehicleModel model);

  NamedVehicleFigure createNamedVehicleFigure(VehicleModel model);
}
