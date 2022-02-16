/**
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.guing;

import com.google.inject.TypeLiteral;
import java.io.File;
import java.util.List;
import java.util.Locale;
import javax.inject.Singleton;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.opentcs.access.KernelServicePortal;
import org.opentcs.access.SslParameterSet;
import org.opentcs.access.rmi.KernelServicePortalBuilder;
import org.opentcs.access.rmi.factories.NullSocketFactoryProvider;
import org.opentcs.access.rmi.factories.SecureSocketFactoryProvider;
import org.opentcs.access.rmi.factories.SocketFactoryProvider;
import org.opentcs.common.GuestUserCredentials;
import org.opentcs.components.plantoverview.LocationTheme;
import org.opentcs.components.plantoverview.VehicleTheme;
import org.opentcs.customizations.ApplicationHome;
import org.opentcs.customizations.plantoverview.PlantOverviewInjectionModule;
import org.opentcs.drivers.LowLevelCommunicationEvent;
import org.opentcs.guing.application.ApplicationInjectionModule;
import org.opentcs.guing.components.ComponentsInjectionModule;
import org.opentcs.guing.exchange.ExchangeInjectionModule;
import org.opentcs.guing.exchange.SslConfiguration;
import org.opentcs.guing.model.ModelInjectionModule;
import org.opentcs.guing.peripherals.jobs.PeripheralJobInjectionModule;
import org.opentcs.guing.persistence.DefaultPersistenceInjectionModule;
import org.opentcs.guing.transport.TransportInjectionModule;
import org.opentcs.guing.util.PlantOverviewApplicationConfiguration;
import org.opentcs.guing.util.PlantOverviewOperatingApplicationConfiguration;
import org.opentcs.guing.util.UtilInjectionModule;
import org.opentcs.util.ClassMatcher;
import org.opentcs.util.gui.dialog.ConnectionParamSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Guice module for the openTCS plant overview application.
 *
 * @author Stefan Walter (Fraunhofer IML)
 */
public class DefaultPlantOverviewInjectionModule
    extends PlantOverviewInjectionModule {

  /**
   * This class's logger.
   */
  private static final Logger LOG
      = LoggerFactory.getLogger(DefaultPlantOverviewInjectionModule.class);

  @Override
  protected void configure() {
    File applicationHome = new File(System.getProperty("opentcs.home", "."));
    bind(File.class)
        .annotatedWith(ApplicationHome.class)
        .toInstance(applicationHome);

    configurePlantOverviewDependencies();
    install(new ApplicationInjectionModule());
    install(new ComponentsInjectionModule());
    install(new ExchangeInjectionModule());
    install(new ModelInjectionModule());
    install(new DefaultPersistenceInjectionModule());
    install(new TransportInjectionModule());
    install(new PeripheralJobInjectionModule());
    install(new UtilInjectionModule());

    // Ensure there is at least an empty binder for pluggable panels.
    pluggablePanelFactoryBinder();
    // Ensure there is at least an empty binder for history entry formatters.
    objectHistoryEntryFormatterBinder();
  }

  private void configurePlantOverviewDependencies() {
    PlantOverviewApplicationConfiguration baseConfiguration
        = getConfigBindingProvider().get(PlantOverviewApplicationConfiguration.PREFIX,
                                         PlantOverviewApplicationConfiguration.class);
    PlantOverviewOperatingApplicationConfiguration configuration
        = getConfigBindingProvider().get(PlantOverviewApplicationConfiguration.PREFIX,
                                         PlantOverviewOperatingApplicationConfiguration.class);
    bind(PlantOverviewApplicationConfiguration.class)
        .toInstance(baseConfiguration);
    bind(PlantOverviewOperatingApplicationConfiguration.class)
        .toInstance(configuration);
    configurePlantOverview(configuration);
    configureThemes(configuration);
    configureSocketConnections();

    bind(new TypeLiteral<List<ConnectionParamSet>>() {
    })
        .toInstance(baseConfiguration.connectionBookmarks());
  }

  private void configureSocketConnections() {
    SslConfiguration sslConfiguration = getConfigBindingProvider().get(SslConfiguration.PREFIX,
                                                                       SslConfiguration.class);

    //Create the data object for the ssl configuration
    SslParameterSet sslParamSet = new SslParameterSet(SslParameterSet.DEFAULT_KEYSTORE_TYPE,
                                                      null,
                                                      null,
                                                      new File(sslConfiguration.truststoreFile()),
                                                      sslConfiguration.truststorePassword());
    bind(SslParameterSet.class).toInstance(sslParamSet);

    SocketFactoryProvider socketFactoryProvider;
    if (sslConfiguration.enable()) {
      socketFactoryProvider = new SecureSocketFactoryProvider(sslParamSet);
    }
    else {
      LOG.warn("SSL encryption disabled, connections will not be secured!");
      socketFactoryProvider = new NullSocketFactoryProvider();
    }

    //Bind socket provider to the kernel portal
    bind(KernelServicePortal.class)
        .toInstance(new KernelServicePortalBuilder(GuestUserCredentials.USER,
                                                   GuestUserCredentials.PASSWORD)
            .setSocketFactoryProvider(socketFactoryProvider)
            .setEventFilter(new ClassMatcher(LowLevelCommunicationEvent.class).negate())
            .build());
  }

  private void configureThemes(PlantOverviewOperatingApplicationConfiguration configuration) {
    bind(LocationTheme.class)
        .to(configuration.locationThemeClass())
        .in(Singleton.class);
    bind(VehicleTheme.class)
        .to(configuration.vehicleThemeClass())
        .in(Singleton.class);
  }

  private void configurePlantOverview(PlantOverviewOperatingApplicationConfiguration configuration) {
    Locale.setDefault(Locale.forLanguageTag(configuration.locale()));

    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (ClassNotFoundException | InstantiationException | IllegalAccessException
               | UnsupportedLookAndFeelException ex) {
      LOG.warn("Could not set look-and-feel", ex);
    }
    // Show tooltips for 30 seconds (Default: 4 sec)
    ToolTipManager.sharedInstance().setDismissDelay(30 * 1000);
  }
}
