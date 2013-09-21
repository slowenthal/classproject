package tracker.testhelpers;

import javax.servlet.*;
import javax.servlet.descriptor.JspConfigDescriptor;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: stevelowenthal
 * Date: 9/20/13
 * Time: 3:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class MockServletContext implements ServletContext {

  private final Map<String, Object> attributes = new HashMap<String, Object>();

  public String getContextPath() {
    return null;  
  }

  public ServletContext getContext(String s) {
    return null;  
  }

  public int getMajorVersion() {
    return 0;  
  }

  public int getMinorVersion() {
    return 0;  
  }

  public int getEffectiveMajorVersion() {
    return 0;  
  }

  public int getEffectiveMinorVersion() {
    return 0;  
  }

  public String getMimeType(String s) {
    return null;  
  }

  public Set<String> getResourcePaths(String s) {
    return null;  
  }

  public URL getResource(String s) throws MalformedURLException {
    return null;  
  }

  public InputStream getResourceAsStream(String s) {
    return null;  
  }

  public RequestDispatcher getRequestDispatcher(String s) {
    return null;  
  }

  public RequestDispatcher getNamedDispatcher(String s) {
    return null;  
  }

  public Servlet getServlet(String s) throws ServletException {
    return null;  
  }

  public Enumeration<Servlet> getServlets() {
    return null;  
  }

  public Enumeration<String> getServletNames() {
    return null;  
  }

  public void log(String s) {
    
  }

  public void log(Exception e, String s) {
    
  }

  public void log(String s, Throwable throwable) {
    
  }

  public String getRealPath(String s) {
    return null;  
  }

  public String getServerInfo() {
    return null;  
  }

  public String getInitParameter(String s) {
    return null;  
  }

  public Enumeration<String> getInitParameterNames() {
    return null;  
  }

  public boolean setInitParameter(String s, String s2) {
    return false;  
  }

  public Object getAttribute(String s) {
      return attributes.get(s);
   }

  public Enumeration<String> getAttributeNames() {
    return null;  
  }

  public void setAttribute(String s, Object o) {
       attributes.put(s,o);
  }

  public void removeAttribute(String s) {
    
  }

  public String getServletContextName() {
    return null;  
  }

  public ServletRegistration.Dynamic addServlet(String s, String s2) {
    return null;  
  }

  public ServletRegistration.Dynamic addServlet(String s, Servlet servlet) {
    return null;  
  }

  public ServletRegistration.Dynamic addServlet(String s, Class<? extends Servlet> aClass) {
    return null;  
  }

  public <T extends Servlet> T createServlet(Class<T> tClass) throws ServletException {
    return null;  
  }

  public ServletRegistration getServletRegistration(String s) {
    return null;  
  }

  public Map<String, ? extends ServletRegistration> getServletRegistrations() {
    return null;  
  }

  public FilterRegistration.Dynamic addFilter(String s, String s2) {
    return null;  
  }

  public FilterRegistration.Dynamic addFilter(String s, Filter filter) {
    return null;  
  }

  public FilterRegistration.Dynamic addFilter(String s, Class<? extends Filter> aClass) {
    return null;  
  }

  public <T extends Filter> T createFilter(Class<T> tClass) throws ServletException {
    return null;  
  }

  public FilterRegistration getFilterRegistration(String s) {
    return null;  
  }

  public Map<String, ? extends FilterRegistration> getFilterRegistrations() {
    return null;  
  }

  public SessionCookieConfig getSessionCookieConfig() {
    return null;  
  }

  public void setSessionTrackingModes(Set<SessionTrackingMode> sessionTrackingModes) {
    
  }

  public Set<SessionTrackingMode> getDefaultSessionTrackingModes() {
    return null;  
  }

  public Set<SessionTrackingMode> getEffectiveSessionTrackingModes() {
    return null;  
  }

  public void addListener(String s) {
    
  }

  public <T extends EventListener> void addListener(T t) {
    
  }

  public void addListener(Class<? extends EventListener> aClass) {
    
  }

  public <T extends EventListener> T createListener(Class<T> tClass) throws ServletException {
    return null;  
  }

  public JspConfigDescriptor getJspConfigDescriptor() {
    return null;  
  }

  public ClassLoader getClassLoader() {
    return null;  
  }

  public void declareRoles(String... strings) {

  }
}
