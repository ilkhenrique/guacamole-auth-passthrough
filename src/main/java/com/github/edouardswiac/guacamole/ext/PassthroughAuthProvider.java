package com.github.edouardswiac.guacamole.ext;

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.GuacamoleServerException;
import org.apache.guacamole.net.auth.Credentials;
import org.apache.guacamole.net.auth.simple.SimpleAuthenticationProvider;
import org.apache.guacamole.protocol.GuacamoleConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public final class PassthroughAuthProvider extends SimpleAuthenticationProvider {

  private final static Logger LOG = LoggerFactory.getLogger(PassthroughAuthProvider.class);

  @Override
  public String getIdentifier() {
    return "ext.auth.passthrough";
  }

  @Override
  public Map<String, GuacamoleConfiguration> getAuthorizedConfigurations(Credentials credentials) throws GuacamoleException {
    HttpServletRequest req = credentials.getRequest();
    if (req == null)
	    return null;
    GuacamoleConfiguration conf = new GuacamoleConfiguration();

    if (null == req.getParameter("protocol") ||
        null == req.getParameter("hostname") ) {
      LOG.error("protocol/hostname are required");
      throw new GuacamoleServerException("protocol/hostname are required");
    }

    conf.setProtocol(req.getParameter("protocol"));
    conf.setParameter("hostname", req.getParameter("hostname"));
    Map<String, String[]> params = new HashMap<>(req.getParameterMap());
    params.remove("protocol");
    params.remove("hostname");

    for(String p: params.keySet()) {
      conf.setParameter(p, req.getParameter(p));
    }

    LOG.debug("passthrough config is {}", conf.getParameters());
    final Map<String, GuacamoleConfiguration> configMap = new HashMap<>();
    configMap.put("DEFAULT", conf);
    return configMap;
  }
}
