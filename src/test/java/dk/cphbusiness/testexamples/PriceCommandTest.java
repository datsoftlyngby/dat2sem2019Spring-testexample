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

import org.mockito.stubbing.Answer;

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
    when(request.getParameter("code")).thenReturn("XYZ11");
    //doNothing().when(request).setAttribute(any(String.class), any(Double.class));
    doAnswer( 
      invocation -> {
          String key = invocation.getArgument(0);
          double price = invocation.getArgument(1);
          assertThat(price, is(47.11));
          return null;
          }
      ).when(request).setAttribute(any(String.class), any(Double.class));
    when(logic.getPrice("XYZ11")).thenReturn(47.11);
    Command command = new PriceCommand();
    String target = command.execute(request, logic);
    assertThat(target, is("pricepage.jsp"));
    }
  
  }
