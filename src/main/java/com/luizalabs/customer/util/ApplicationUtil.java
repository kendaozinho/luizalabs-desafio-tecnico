package com.luizalabs.customer.util;

import com.luizalabs.customer.StartApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ApplicationUtil {
  private static Logger logger = LoggerFactory.getLogger(ApplicationUtil.class);

  private ApplicationUtil() {
  }

  public static String getVersion() {
    try {
      return StartApplication.class.getPackage().getImplementationVersion();
    } catch (Throwable t) {
      ApplicationUtil.logger.error("Unable to get application version: " + t.toString());
    }

    return null;
  }
}
