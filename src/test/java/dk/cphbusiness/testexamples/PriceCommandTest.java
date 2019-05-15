package dk.cphbusiness.testexamples;

import javax.servlet.http.HttpServletRequest;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;

import org.mockito.MockitoAnnotations;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;


public class PriceCommandTest {
  @Mock
  private LogicFacade logic;
  
  @Mock
  private HttpServletRequest request;
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    }
  
  @Test
  public void testPriceCommand() {
    // Set up
    doReturn("XYZ11").when(request).getParameter("code");
    doAnswer( 
      invocation -> {
          String key = invocation.getArgument(0);
          double price = invocation.getArgument(1);
          assertThat(price, is(47.11));
          return null;
          }
      ).when(request).setAttribute(any(String.class), anyDouble());
    when(logic.getPrice("XYZ11")).thenReturn(47.11); // Alternative syntax
    
    // Execute
    Command command = new PriceCommand();
    String target = command.execute(request, logic);
    
    // Analyse
    assertThat(target, is("pricepage.jsp"));
    verify(logic, atLeastOnce()).getPrice(anyString());

    }
  
  }
