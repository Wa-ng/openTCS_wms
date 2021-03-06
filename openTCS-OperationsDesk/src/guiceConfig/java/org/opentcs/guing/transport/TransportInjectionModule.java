/**
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.guing.transport;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import javax.inject.Singleton;
import org.opentcs.guing.transport.orders.TransportOrdersContainer;
import org.opentcs.guing.transport.orders.TransportViewFactory;
import org.opentcs.guing.transport.sequences.OrderSequencesContainer;

/**
 * A Guice module for this package.
 *
 * @author Stefan Walter (Fraunhofer IML)
 */
public class TransportInjectionModule
    extends AbstractModule {

  @Override
  protected void configure() {
    install(new FactoryModuleBuilder().build(TransportViewFactory.class));
    
    bind(TransportOrdersContainer.class)
        .in(Singleton.class);
    
    bind(OrderSequencesContainer.class)
        .in(Singleton.class);
  }
}
