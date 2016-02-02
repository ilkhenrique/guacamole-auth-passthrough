package com.github.edouardswiac.guacamole.ext;


import org.glyptodon.guacamole.GuacamoleException;
import org.glyptodon.guacamole.GuacamoleServerException;
import org.glyptodon.guacamole.net.auth.Credentials;
import org.glyptodon.guacamole.protocol.GuacamoleConfiguration;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PassthroughAuthProviderTest {

  PassthroughAuthProvider provider;
  HttpServletRequest request;
  Credentials credentials = new Credentials();

  @Before
  public void setUp() {
    request = mock(HttpServletRequest.class);
    credentials.setRequest(request);
    provider = new PassthroughAuthProvider();

  }
  @Test
  public void testParseEmptyParams() {
    when(request.getParameter("protocol")).thenReturn(null);
    when(request.getParameter("username")).thenReturn("hello");
    assertThatThrownBy(() -> provider.getAuthorizedConfigurations(credentials)).isInstanceOf(GuacamoleServerException.class);
  }

  @Test
  public void testParseParams() throws GuacamoleException {
    when(request.getParameter("protocol")).thenReturn("rdp");
    when(request.getParameter("username")).thenReturn("alice");
    when(request.getParameter("password")).thenReturn("ultr4S3cuRe");
    Map<String, String[]> paramMap = new HashMap<>();
    String[] p1 = { "1024x768" };
    paramMap.put("param1", p1 );
    when(request.getParameterMap()).thenReturn(paramMap);
    when(request.getParameter("param1")).thenReturn(p1[0]);

    GuacamoleConfiguration config = provider.getAuthorizedConfigurations(credentials).get("DEFAULT");
    assertThat(config.getProtocol()).isEqualTo("rdp");
    assertThat(config.getParameter("username")).isEqualTo("alice");
    assertThat(config.getParameter("password")).isEqualTo("ultr4S3cuRe");
    assertThat(config.getParameter("param1")).isEqualTo("1024x768");
  }
}
