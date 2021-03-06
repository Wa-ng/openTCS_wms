/**
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.guing.components.drawing.figures;

import org.jhotdraw.draw.AttributeKey;
import org.opentcs.guing.components.drawing.course.Origin;
import org.opentcs.guing.model.ModelComponent;

/**
 * Constants that are relevant to figures.
 *
 * @author Sebastian Naumann (ifak e.V. Magdeburg)
 */
public interface FigureConstants {

  /**
   * Key for figures to access their models.
   */
  AttributeKey<ModelComponent> MODEL = new AttributeKey<>("Model", ModelComponent.class);
  /**
   * Key for figures to access the origin.
   */
  AttributeKey<Origin> ORIGIN = new AttributeKey<>("Origin", Origin.class);
}
