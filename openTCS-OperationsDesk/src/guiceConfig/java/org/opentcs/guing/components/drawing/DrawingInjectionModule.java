/**
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.guing.components.drawing;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import javax.inject.Singleton;
import org.jhotdraw.draw.DrawingEditor;
import org.opentcs.guing.components.drawing.figures.VehicleFigureFactory;
import org.opentcs.guing.util.CourseObjectFactory;
import org.opentcs.guing.util.VehicleCourseObjectFactory;
import org.opentcs.thirdparty.components.drawing.OpenTCSDrawingViewOperating;

/**
 * A Guice module for this package.
 *
 * @author Stefan Walter (Fraunhofer IML)
 */
public class DrawingInjectionModule
    extends AbstractModule {

  @Override
  protected void configure() {
    install(new FactoryModuleBuilder().build(VehicleFigureFactory.class));
    bind(CourseObjectFactory.class).to(VehicleCourseObjectFactory.class);

    bind(OpenTCSDrawingEditorOperating.class).in(Singleton.class);
    bind(OpenTCSDrawingEditor.class).to(OpenTCSDrawingEditorOperating.class);
    bind(DrawingEditor.class).to(OpenTCSDrawingEditorOperating.class);

    bind(OpenTCSDrawingView.class).to(OpenTCSDrawingViewOperating.class);

    bind(DrawingOptions.class).in(Singleton.class);
  }
}
